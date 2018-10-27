package com.github.kellielarsen.databaseproject;

/* @author kellie */
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
        try (Connection conn = connect()) {
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
        
        try (Connection conn = connect();
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void insert(String name, int roomNumber, int numGuests, int numNights, String roomType) {
        String sql = "INSERT INTO Guests(Name, RoomNumber, NumGuests, NumNights, RoomType) VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
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