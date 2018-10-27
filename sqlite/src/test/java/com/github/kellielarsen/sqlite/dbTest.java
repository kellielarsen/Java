/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kellielarsen.sqlite;

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
public class dbTest {
    
    public dbTest() {
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

    /**
     * Test of getConnection method, of class db.
     */
    @Test
    public void testGetConnection() {
        System.out.println("getConnection");
        db instance = new db();
        Connection expResult = null;
        Connection result = instance.getConnection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPreparedStatement method, of class db.
     */
    @Test
    public void testGetPreparedStatement() {
        System.out.println("getPreparedStatement");
        String sql = "";
        db instance = new db();
        PreparedStatement expResult = null;
        PreparedStatement result = instance.getPreparedStatement(sql);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sql method, of class db.
     */
    @Test
    public void testSql_String_ObjectArr() {
        System.out.println("sql");
        String sql = "";
        Object[] objects = null;
        db instance = new db();
        ResultSet expResult = null;
        ResultSet result = instance.sql(sql, objects);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of longResult method, of class db.
     */
    @Test
    public void testLongResult() {
        System.out.println("longResult");
        ResultSet resultSet = null;
        db instance = new db();
        Long expResult = null;
        Long result = instance.longResult(resultSet);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stringResult method, of class db.
     */
    @Test
    public void testStringResult() {
        System.out.println("stringResult");
        ResultSet resultSet = null;
        db instance = new db();
        String expResult = "";
        String result = instance.stringResult(resultSet);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStatement method, of class db.
     */
    @Test
    public void testGetStatement() {
        System.out.println("getStatement");
        db instance = new db();
        Statement expResult = null;
        Statement result = instance.getStatement();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sql method, of class db.
     */
    @Test
    public void testSql_String() {
        System.out.println("sql");
        String command = "";
        db instance = new db();
        instance.sql(command);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reset method, of class db.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        db instance = new db();
        instance.reset();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGuest method, of class db.
     */
    @Test
    public void testGetGuest() {
        System.out.println("getGuest");
        int roomNumber = 0;
        db instance = new db();
        String expResult = "";
        String result = instance.getGuest(roomNumber);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of run method, of class db.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        db instance = new db();
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
