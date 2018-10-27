/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kellielarsen.sqlite;

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
public class appTest {
    
    public appTest() {
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
     * Test of createNewDatabase method, of class app.
     */
    @Test
    public void testCreateNewDatabase() {
        System.out.println("createNewDatabase");
        app instance = new app();
        instance.createNewDatabase();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createNewTable method, of class app.
     */
    @Test
    public void testCreateNewTable() {
        System.out.println("createNewTable");
        app instance = new app();
        instance.createNewTable();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insert method, of class app.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        String name = "";
        int roomNumber = 0;
        int numGuests = 0;
        int numNights = 0;
        String roomType = "";
        app instance = new app();
        instance.insert(name, roomNumber, numGuests, numNights, roomType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class app.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        app.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of run method, of class app.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        app instance = new app();
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
