package com.github.kellielarsen.sqlite;

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

public class db {
    
    public static String DEFAULT_DB = "guests.db";
    public static String DEFAULT_URL = "jdbc:sqlite:" + DEFAULT_DB;

    public final String url;
    db() { this(DEFAULT_URL); }
    db(String url) { this.url = url; }

    //thread-safe version of lazy instantiation
    private Connection connection = null; //doesn't create connection if getConnection isn't called
    public Connection getConnection() {
        if (connection == null) { //if connection exists, return existing connection
            synchronized (this) { //synchronize all threads
                if (connection == null) { //check again after synchronization
                    try {
                        connection = DriverManager.getConnection(url); //no connection exists, so create connection
                    } catch (SQLException ex) {
                        Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
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
                        Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    Long longResult(ResultSet resultSet) {
        try {
            if (resultSet != null && resultSet.next()) return resultSet.getLong(1);
        } catch (SQLException ex) {
            Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    String stringResult(ResultSet resultSet) {
        try {
            if (resultSet != null && resultSet.next()) return resultSet.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
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
                        Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void reset() {
        sql("drop table if exists person");
        sql("create table person (id integer primary key, name string)");
    }

    String getGuest(int roomNumber) {
        return stringResult(sql("select Name from Guests where RoomNumber = ?", roomNumber));
    }

    void run() {
        reset();
        System.out.println("Guest in room 105 = " + getGuest(105));
    }

}