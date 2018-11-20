package com.github.kellielarsen.finalproject;

import java.net.*;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

/* @author kellie */
public class ProxyClient extends Thread {
    private Socket sock;
    public DataInputStream in;
    public DataOutputStream out;
    public String outMsg;
    public String username;
    public boolean hasMsg;
    private final Server server;
    public Set<ProxyClient> clients = new HashSet<>();
    
    ProxyClient(Server _server, Socket _sock) {
        server = _server;
        sock = _sock;
        try {
            in = new DataInputStream(sock.getInputStream());
            out =  new DataOutputStream(sock.getOutputStream());
        } catch (IOException err) {
            System.out.println("Error: " + err.getMessage());
        }
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void handleLogin() throws IOException {
        clients = server.getClients();
        boolean validUsername = false;
        String userTry = "";
        while (!validUsername) {
            userTry = in.readUTF();
            if (userTry.length() > 1) {
                if (clients.isEmpty()) {
                    validUsername = true;
                }
                for (ProxyClient client : clients) {
                    if (client.username.equals(userTry)) {
                        out.writeUTF("taken");
                    }
                    else {
                        validUsername = true;
                    }
                }
            }
            else {
                out.writeUTF("short");
            }
        }
        out.writeUTF("valid");
        username = userTry;
        clients.add(this);
        //notify other online clients of logon
        clients.stream().filter((client) -> (!client.username.equals(username))).forEachOrdered((client) -> {
            client.send(username + " is online");
        });
        handleChat();
    }
    
    public void handleChat() {
        clients = server.getClients();
        while (true) {
            try {
                outMsg = in.readUTF();
                clients.stream().filter((client) -> (!username.equals(client.username))).forEachOrdered((client) -> {
                    client.send(username + ": " + outMsg);
                });
            }
            catch(IOException err) {
                System.out.println("Error: " + err.getMessage());
            }   
        }
    }
    
    private void send(String msg) {
        try {
            out.writeUTF(msg);
        }
        catch(IOException err) {
            System.out.println("Error: " + err.getMessage());
        }
    }
    
    @Override
    public void run() {
        try {
            handleLogin();
        }
        catch(IOException err) {
             System.out.println("Error: " + err.getMessage());
        }
    }
}
