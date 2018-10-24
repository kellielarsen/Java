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
public class DBTest {
    
    public DBTest() {
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

    /* Test of getConnection method, of class DB. */
    @Test
    public void testGetConnection() throws SQLException {
        System.out.println("getConnection");
        DB instance = new DB();
        Connection result = instance.getConnection();
        assertEquals(result.isValid(5), true);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    /* Test of getRoomNumber method, of class DB. */
    @Test
    public void testGetRoomNumber() {
        System.out.println("getRoomNumber");
        String name = "Guest 1";
        DB instance = new DB();
        String expResult = instance.stringResult(instance.sql("select roomNumber from Guests where Name = ?", name));
        String result = instance.getRoomNumber(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /* Test of run method, of class DB. */
    @Test
    public void testRun() {
        System.out.println("run");
        DB instance = new DB();
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /* Test of getGuest method, of class DB. */
    @Test
    public void testGetGuest() {
        System.out.println("getGuest");
        String name = "Guest 1";
        DB instance = new DB();
        String expResult = instance.stringResult(instance.sql("select * from Guests where Name = ?", name));
        String result = instance.getGuest(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /* Test of changeRoom method, of class DB. */
    @Test
    public void testChangeRoom() {
        System.out.println("changeRoom");
        String name = "Guest 1";
        int roomNumber = 100;
        DB instance = new DB();
        App app = new App();
        app.insert(name, 0, 0, "N/A", 1);
        instance.changeRoom(name, roomNumber);
        assertEquals(instance.stringResult(instance.sql("select roomNumber from Guests where name = ?", name)), roomNumber);
    }

    /* Test of deleteGuest method, of class DB. */
    @Test
    public void testDeleteGuest() {
        System.out.println("deleteGuest");
        String name = "";
        DB instance = new DB();
        instance.deleteGuest(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sql method, of class DB.
     */
    @Test
    public void testSql() {
        System.out.println("sql");
        String sql = "";
        Object[] objects = null;
        DB instance = new DB();
        ResultSet expResult = null;
        ResultSet result = instance.sql(sql, objects);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
