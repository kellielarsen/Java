package com.github.kellielarsen.databaseproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/* @author kellie */
public class DatabaseTest {
    
    public DatabaseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    /* Test of insert method, of class Database. */
    /* CREATE */
    @Test
    public void testInsert() {
        System.out.println("insert");
        String name = "Guest 1";
        int roomNumber = 100;
        int numGuests = 2;
        int numNights = 1;
        String roomType = "1 King";
        Database instance = new Database();
        instance.createNewDatabase();
        instance.createNewTable();
        instance.insert(name, roomNumber, numGuests, numNights, roomType);
        assertEquals("Guest 1", instance.stringResult(instance.sql("select name from Guests where Name = ?", name)));
    }

    /* Test of readRecord method, of class Database. */
    /* READ */
    @Test
    public void testReadRecord() {
        System.out.println("readRecord");
        String name = "Guest 1";
        Database instance = new Database();
        instance.createNewDatabase();
        instance.createNewTable();
        assertEquals(instance.readRecord(name), "Name = Guest 1, RoomNumber = 100, NumGuests = 2, NumNights = 1, RoomType = 1 King");
    }

    /* Test of update method, of class Database. */
    /* UPDATE */
    @Test
    public void testUpdate() {
        System.out.println("update");
        String name = "Guest 1";
        int roomNumber = 300;
        int numGuests = 2;
        int numNights = 1;
        String roomType = "1 King";
        Database instance = new Database();
        instance.createNewDatabase();
        instance.createNewTable();
        instance.update(name, roomNumber, numGuests, numNights, roomType);
        assertEquals(instance.stringResult(instance.sql("SELECT RoomNumber FROM Guests WHERE Name = ?", name)), Integer.toString(roomNumber));
    }

    /* Test of delete method, of class Database. */
    /* DELETE */
    @Test
    public void testDelete() throws SQLException {
        System.out.println("delete");
        String name = "Guest 1";
        Database instance = new Database();
        instance.createNewDatabase();
        instance.createNewTable();
        instance.delete(name);
        ResultSet r = instance.sql("select * from Guests where name = ?", name);
        assertTrue(!r.next());
    }
    
}
