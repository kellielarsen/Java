package com.github.kellielarsen.databaseproject;

/* @author kellie */
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    
    public static String db = "guests.db";
    public static String url = "jdbc:sqlite:" + db;
    
    private Connection connection = null; //doesn't create connection if getConnection isn't called
    public Connection connect() {
        if (connection == null) { //if connection exists, return existing connection
            synchronized (this) { //synchronize all threads
                if (connection == null) { //check again after synchronization
                    try {
                        connection = DriverManager.getConnection(url); //no connection exists, so create connection
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        return connection;
    }
    
    public void createNewDatabase() {
        try {
            Connection conn = connect();
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void createNewTable() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Guests (\n"
                + "	Name text,\n"
                + "	RoomNumber integer,\n"
                + "	NumGuests integer,\n"
                + "     NumNights integer,\n"
                + "     RoomType text\n"
                + ");";
        
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void insert(String name, int roomNumber, int numGuests, int numNights, String roomType) {
        String sql = "INSERT INTO Guests(Name, RoomNumber, NumGuests, NumNights, RoomType) VALUES(?, ?, ?, ?, ?)";
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, roomNumber);
            pstmt.setInt(3, numGuests);
            pstmt.setInt(4, numNights);
            pstmt.setString(5, roomType);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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
                        Connection connection = connect();
                        int keyMode = sql.startsWith("insert")
                                ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS;

                        preparedStatement
                                = connection.prepareStatement(sql, keyMode);
                        preparedStatement.setQueryTimeout(SQL_STATEMENT_TIMEOUT_SECONDS);
                        preparedStatementCache.put(sql, preparedStatement); //add prepared statement to cache
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
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
                    throw new IllegalStateException("Can't set type " + object.getClass().getName());
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    Long longResult(ResultSet resultSet) {
        try {
            if (resultSet != null && resultSet.next()) return resultSet.getLong(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    String stringResult(ResultSet resultSet) {
        try {
            if (resultSet != null && resultSet.next()) return resultSet.getString(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private Statement statement = null;

    public Statement getStatement() {
        if (statement == null) {
            synchronized (this) {
                if (statement == null) {
                    try {
                        Connection connection = connect();
                        statement = connection.createStatement();
                        statement.setQueryTimeout(SQL_STATEMENT_TIMEOUT_SECONDS);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        return statement;
    }

    void sql(String command) {
        try {
            Statement stmt = getStatement();
            stmt.executeUpdate(command);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 
    public static void main(String[] args) {
        new Database().run();
    }
    
    void run() {
        createNewDatabase();
        createNewTable();
        insert("Guest 1", 100, 2, 1, "1 King");
        insert("Guest 2", 200, 4, 2, "2 Doubles");
        insert("Guest 3", 105, 1, 3, "1 Double");
    }
}