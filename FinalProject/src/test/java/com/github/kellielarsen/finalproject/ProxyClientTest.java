package com.github.kellielarsen.finalproject;

import java.io.IOException;
import java.net.Socket;
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

    /* Test of getUsername method, of class ProxyClient. */
    @org.junit.Test
    public void testGetUsername() throws IOException {
        System.out.println("getUsername");
        Server server = new Server(5000);
        server.start();
        Socket socket = new Socket("127.0.0.1", 5000);
        ProxyClient instance = new ProxyClient(server, socket);
        instance.username = "Test";
        String expResult = "Test";
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /* No test needed for handleLogin method, of class ProxyClient. */

    /* No test needed for handleChat method, of class ProxyClient. */

    /* Test of run method, of class ProxyClient. */
    @org.junit.Test
    public void testRun() throws IOException {
        System.out.println("run");
        Server server = new Server(5000);
        server.start();
        Socket socket = new Socket("127.0.0.1", 5000);
        ProxyClient instance = new ProxyClient(server, socket);
        instance.start();
        assertTrue(instance.isAlive());
    }
    
}
