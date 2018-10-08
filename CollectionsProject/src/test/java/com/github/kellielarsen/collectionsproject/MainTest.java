package com.github.kellielarsen.collectionsproject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/* @author kellie */
public class MainTest {
    
    public MainTest() {
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

    /* Test of main method, of class Main. */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        Main.main(args);

        fail("The test case is a prototype.");
    }

    /* Test of run method, of class Main. */
    @Test
    public void testRun() {
        System.out.println("run");
        Main instance = new Main();
        instance.run();
        
        fail("The test case is a prototype.");
    }
    
}
