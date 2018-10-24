package com.github.kellielarsen.databaseproject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/* @author kellie */
public class AppTest {
    
    public AppTest() {
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

    /* Test of createNewDatabase method, of class App. */
    @Test
    public void testCreateNewDatabase() {
        System.out.println("createNewDatabase");
        App instance = new App();
        instance.createNewDatabase();
    }

    /* Test of createNewTable method, of class App. */
    @Test
    public void testCreateNewTable() {
        System.out.println("createNewTable");
        App instance = new App();
        instance.createNewTable();
    }

    /* Test of insert method, of class App. */
    @Test
    public void testInsert() {
        System.out.println("insert");
        String name = "Guest";
        int nights = 2;
        int numGuests = 2;
        String roomType = "1 King";
        int roomNumber = 105;
        App instance = new App();
        DB db = new DB();
        instance.insert(name, nights, numGuests, roomType, roomNumber);
        assertEquals(db.stringResult(db.sql("select Name from Guests where Name = ?", name)), name);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /* Test of main method, of class App. */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        App.main(args);
    }

    /* Test of run method, of class App. */
    @Test
    public void testRun() {
        System.out.println("run");
        App instance = new App();
        instance.run();
    }
}
