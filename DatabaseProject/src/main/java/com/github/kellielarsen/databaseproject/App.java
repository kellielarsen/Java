package com.github.kellielarsen.databaseproject;

/* @author kellie */
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB.DEFAULT_URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
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
                + "	Name text NOT NULL,\n"
                + "	NumNights real NOT NULL,\n"
                + "     NumGuests real NOT NULL,\n"
                + "     RoomType text NOT NULL,\n"
                + "     RoomNumber real NOT NULL,\n"
                + ");";
        
        try (Connection conn = connect();
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void insert(String name, int nights, int numGuests, String roomType, int roomNumber) {
        String sql = "INSERT INTO Guests(Name, NumNights, NumGuests, RoomType, RoomNumber) VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, nights);
            pstmt.setInt(3, numGuests);
            pstmt.setString(4, roomType);
            pstmt.setInt(5, roomNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 
    public static void main(String[] args) {
        new App().run();
    }
    
    void run() {
        createNewDatabase();
        createNewTable();
        insert("Guest 1", 3, 4, "2 Doubles", 105);
        insert("Guest 2", 2, 2, "1 King", 223);
        insert("Guest 3", 1, 3, "2 Doubles", 114);
    }
}

//item potent - an action that if repeated causes no harm
     //ex. creating a new database each time does not destroy existing database
