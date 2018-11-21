package com.github.kellielarsen.finalproject;

import java.io.IOException;
import java.net.*;
import java.util.HashSet;
import java.util.Set;

/* @author kellie */
public class Server extends Thread {
    private final int port;
    public Set<ProxyClient> clients = new HashSet<>();
    
    public Server(int port) {
        this.port = port;
    }
    
    public Set<ProxyClient> getClients() {
        return clients;
    }
    
    public void removeClient(ProxyClient client) {
        clients.remove(client);
    }
    
    @Override
    public void run() {
        try (ServerSocket listen = new ServerSocket(port)) {
            while (true) {
                System.out.println("Waiting for connection. . .");
                Socket sock = listen.accept();
                System.out.println("Connection from " + sock);
                ProxyClient pc = new ProxyClient(this, sock);
                pc.start();
            }
        }
        catch (IOException err) {
            System.out.println("Error: " + err.getMessage());
        }
    }
}
