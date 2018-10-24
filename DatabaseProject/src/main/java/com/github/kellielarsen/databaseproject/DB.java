package com.github.kellielarsen.databaseproject;

/* @author kellie */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB {
    
    public static String DEFAULT_DB = "test.db";
    public static String DEFAULT_URL = "jdbc:sqlite:" + DEFAULT_DB;

    public final String url;
    DB() { this(DEFAULT_URL); }
    DB(String url) { this.url = url; }

    //thread-safe version of lazy instantiation
    private Connection connection = null; //doesn't create connection if getConnection isn't called
    public Connection getConnection() {
        if (connection == null) { //if connection exists, return existing connection
            synchronized (this) { //synchronize all threads
                if (connection == null) { //check again after synchronization
                    try {
                        connection = DriverManager.getConnection(url); //no connection exists, so create connection
                    } catch (SQLException ex) {
                        Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return connection;
    }

    private HashMap< String, PreparedStatement> preparedStatementCache = null;
    public static final int SQL_STATEMENT_TIMEOUT_SECONDS = 10;
    public PreparedStatement getPreparedStatement(String sql) { //fill-in-the-blank statement, template
        //remembers prepared statements already made
        if (preparedStatementCache == null) {
            synchronized (this) {
                if (preparedStatementCache == null) {
                    preparedStatementCache = new HashMap< String, PreparedStatement>(); //create a cache if there is none
                }
            }
        }

        PreparedStatement preparedStatement = preparedStatementCache.get(sql);
        if (preparedStatement == null) {
            synchronized (this) {
                preparedStatement = preparedStatementCache.get(sql);
                if (preparedStatement == null) {
                    try {
                        Connection connection = getConnection();
                        int keyMode = sql.startsWith("insert")
                                ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS;

                        preparedStatement
                                = connection.prepareStatement(sql, keyMode);
                        preparedStatement.setQueryTimeout(SQL_STATEMENT_TIMEOUT_SECONDS);
                        preparedStatementCache.put(sql, preparedStatement); //add prepared statement to cache
                    } catch (SQLException ex) {
                        Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }
        return preparedStatement;
    }

    ResultSet sql(String sql, Object... objects) { //whatever list of objects you want
        try {
            PreparedStatement preparedStatement = getPreparedStatement(sql);
            int index = 1;
            for (Object object : objects) { //loop through each object parameter
                if (object instanceof Boolean) {
                    preparedStatement.setBoolean(index, (Boolean) object);
                } else if (object instanceof Integer) {
                    preparedStatement.setInt(index, (Integer) object);
                } else if (object instanceof Long) {
                    preparedStatement.setLong(index, (Long) object);
                } else if (object instanceof String) {
                    preparedStatement.setString(index, (String) object);
                } else {
                    throw new IllegalStateException("can't set type " + object.getClass().getName());
                }
                ++index;
            }
            if (sql.startsWith("insert")) {
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                return resultSet;
            } else {
                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    Long longResult(ResultSet resultSet) {
        try {
            if (resultSet != null && resultSet.next()) return resultSet.getLong(1);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    String stringResult(ResultSet resultSet) {
        try {
            if (resultSet != null && resultSet.next()) return resultSet.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Statement statement = null;

    public Statement getStatement() {
        if (statement == null) {
            synchronized (this) {
                if (statement == null) {
                    try {
                        Connection connection = getConnection();
                        statement = connection.createStatement();
                        statement.setQueryTimeout(SQL_STATEMENT_TIMEOUT_SECONDS);
                    } catch (SQLException ex) {
                        Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return statement;
    }

    void sql(String command) {
        try {
            Statement statement = getStatement();
            statement.executeUpdate(command);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    String getRoomNumber(String name) {
        return stringResult(sql("select roomNumber from Guests where Name = ?", name));
    }
    
    String getGuest(String name) {
        return stringResult(sql("select * from Guests where Name = ?", name));
    }
    
    void changeRoom(String name, int roomNumber) {
        sql("update Guests set RoomNumber = ? where Name = ?", roomNumber, name);
    }
    
    void deleteGuest(String name) {
        sql("delete from Guests where Name = ?", name);
    }

    void run() {
        System.out.println(getGuest("Guest 1"));
        System.out.println("Guest 2's room number = " + getRoomNumber("Guest 2"));
        changeRoom("Guest 2", 111);
        System.out.println("Guest 2's room number changed to " + getRoomNumber("Guest 2"));
    }

}