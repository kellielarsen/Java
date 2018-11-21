package com.github.kellielarsen.finalproject;

import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/* @author kellie */
public class ServerTest {
    
    public ServerTest() {
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
     * Test of getClients method, of class Server.
     */
    @org.junit.Test
    public void testGetClients() {
        System.out.println("getClients");
        Server instance = null;
        Set<ProxyClient> expResult = null;
        Set<ProxyClient> result = instance.getClients();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeClient method, of class Server.
     */
    @org.junit.Test
    public void testRemoveClient() {
        System.out.println("removeClient");
        ProxyClient client = null;
        Server instance = null;
        instance.removeClient(client);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of run method, of class Server.
     */
    @org.junit.Test
    public void testRun() {
        System.out.println("run");
        Server instance = null;
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
