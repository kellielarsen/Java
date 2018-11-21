package com.github.kellielarsen.finalproject;

import java.io.IOException;
import java.net.Socket;
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

    /* Test of getClients method, of class Server. */
    @org.junit.Test
    public void testGetClients() throws IOException {
        System.out.println("getClients");
        Server instance = new Server(5000);
        instance.start();
        Socket socket = new Socket("127.0.0.1", 5000);
        ProxyClient proxyClient1 = new ProxyClient(instance, socket);
        ProxyClient proxyClient2 = new ProxyClient(instance, socket);
        instance.clients.add(proxyClient1);
        instance.clients.add(proxyClient2);
        Set<ProxyClient> result = instance.getClients();
        //test getClients
        assertTrue(result.contains(proxyClient1));
        assertTrue(result.contains(proxyClient2));
    }

    /* Test of removeClient method, of class Server. */
    @org.junit.Test
    public void testRemoveClient() throws IOException {
        System.out.println("removeClient");
        Server instance = new Server(5000);
        instance.start();
        Socket socket = new Socket("127.0.0.1", 5000);
        ProxyClient proxyClient = new ProxyClient(instance, socket);
        instance.clients.add(proxyClient);
        //Clients is not empty
        assertFalse(instance.clients.isEmpty());
        instance.removeClient(proxyClient);
        //Clients is empty after removeClient
        assertTrue(instance.clients.isEmpty());
    }

    /* Test of run method, of class Server. */
    @org.junit.Test
    public void testRun() {
        System.out.println("run");
        Server instance = new Server(5000);
        instance.start();
        //thread is alive
        assertTrue(instance.isAlive());
    }
    
}
