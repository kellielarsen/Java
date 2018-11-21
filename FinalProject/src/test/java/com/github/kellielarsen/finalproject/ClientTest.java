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
public class ClientTest {
    
    public ClientTest() {
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
    
    /* No test needed for login method, of class Client. */

    /* No test needed for display method, of class Client. */

    /* Test of connect method, of class Client. */
    @org.junit.Test
    public void testConnect() throws IOException {
        System.out.println("connect");
        Server server = new Server(5000);
        server.start();
        Socket socket = new Socket();
        ProxyClient proxyClient = new ProxyClient(server, socket);
        Client instance = new Client("127.0.0.1", 5000);
        instance.connect();
        assertTrue(instance.sock.isConnected());
    }

    /* No test needed for main method, of class Client. */
    
}
