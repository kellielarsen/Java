package com.github.kellielarsen.finalproject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/* @author kellie */
public class ProxyClientTest {
    
    public ProxyClientTest() {
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
     * Test of getUsername method, of class ProxyClient.
     */
    @org.junit.Test
    public void testGetUsername() {
        System.out.println("getUsername");
        ProxyClient instance = null;
        String expResult = "";
        String result = instance.getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of handleLogin method, of class ProxyClient.
     */
    @org.junit.Test
    public void testHandleLogin() throws Exception {
        System.out.println("handleLogin");
        ProxyClient instance = null;
        instance.handleLogin();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of handleChat method, of class ProxyClient.
     */
    @org.junit.Test
    public void testHandleChat() {
        System.out.println("handleChat");
        ProxyClient instance = null;
        instance.handleChat();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of run method, of class ProxyClient.
     */
    @org.junit.Test
    public void testRun() {
        System.out.println("run");
        ProxyClient instance = null;
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
