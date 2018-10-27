package com.github.kellielarsen.databaseproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kelli
 */
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

    /* Test of connect method, of class Database. */
    @Test
    public void testConnect() {
        System.out.println("connect");
        Database instance = new Database();
        Connection expResult = null;
        Connection result = instance.connect();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /* Test of createNewDatabase method, of class Database. */
    @Test
    public void testCreateNewDatabase() {
        System.out.println("createNewDatabase");
        Database instance = new Database();
        instance.createNewDatabase();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /* Test of createNewTable method, of class Database. */
    @Test
    public void testCreateNewTable() {
        System.out.println("createNewTable");
        Database instance = new Database();
        instance.createNewTable();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /* Test of insert method, of class Database. */
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

    /* Test of main method, of class Database. */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Database.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /* Test of run method, of class Database. */
    @Test
    public void testRun() {
        System.out.println("run");
        Database instance = new Database();
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /* Test of getPreparedStatement method, of class Database. */
    @Test
    public void testGetPreparedStatement() {
        System.out.println("getPreparedStatement");
        String sql = "";
        Database instance = new Database();
        PreparedStatement expResult = null;
        PreparedStatement result = instance.getPreparedStatement(sql);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /* Test of sql method, of class Database. */
    @Test
    public void testSql_String_ObjectArr() {
        System.out.println("sql");
        String sql = "";
        Object[] objects = null;
        Database instance = new Database();
        ResultSet expResult = null;
        ResultSet result = instance.sql(sql, objects);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /* Test of longResult method, of class Database. */
    @Test
    public void testLongResult() {
        System.out.println("longResult");
        ResultSet resultSet = null;
        Database instance = new Database();
        Long expResult = null;
        Long result = instance.longResult(resultSet);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /* Test of stringResult method, of class Database. */
    @Test
    public void testStringResult() {
        System.out.println("stringResult");
        ResultSet resultSet = null;
        Database instance = new Database();
        String expResult = "";
        String result = instance.stringResult(resultSet);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /* Test of getStatement method, of class Database. */
    @Test
    public void testGetStatement() {
        System.out.println("getStatement");
        Database instance = new Database();
        Statement expResult = null;
        Statement result = instance.getStatement();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /* Test of sql method, of class Database. */
    @Test
    public void testSql_String() {
        System.out.println("sql");
        String command = "";
        Database instance = new Database();
        instance.sql(command);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
