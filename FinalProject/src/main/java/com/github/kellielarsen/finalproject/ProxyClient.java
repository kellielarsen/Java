package com.github.kellielarsen.finalproject;

import java.net.*;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

/* @author kellie */
public class ProxyClient extends Thread {
    private Socket sock;
    public DataInputStream sIn;
    public DataOutputStream sOut;
    public String outMsg;
    public String username;
    public boolean hasMsg;
    private final Server server;
    public Set<ProxyClient> clients = new HashSet<>();
    
    ProxyClient(Server _server, Socket _sock) {
        server = _server;
        sock = _sock;
        try {
            sIn = new DataInputStream(sock.getInputStream());
            sOut =  new DataOutputStream(sock.getOutputStream());
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
            userTry = sIn.readUTF();
            if (userTry.length() > 1) {
                if (clients.isEmpty()) {
                    validUsername = true;
                }
                for (ProxyClient client : clients) {
                    if (client.username.equals(username)) {
                        sOut.writeUTF("taken");
                    }
                    else {
                        validUsername = true;
                    }
                }
            }
            else {
                sOut.writeUTF("short");
            }
        }
        sOut.writeUTF("valid");
        username = userTry;
        clients.add(this);
        //notify other online clients of logon
        for (ProxyClient client : clients) {
            if (!client.username.equals(username))
                client.send(username + " is online.\n");
        }
        handleChat();
    }
    
    public void handleChat() {
        clients = server.getClients();
        while (true) {
            try {
                outMsg = sIn.readUTF();
                for (ProxyClient client : clients) {
                    if (!username.equals(client.username))
                        client.sOut.writeUTF(username + ": " + outMsg);
                }
            }
            catch(IOException err) {
                System.out.println("Error: " + err.getMessage());
            }   
        }
    }
    
    private void send(String msg) {
        try {
            sOut.writeUTF(msg);
        }
        catch(IOException err) {
            System.out.println("Error: " + err.getMessage());
        }
    }
    
    @Override
    public void run() {
        try {
            handleLogin();
            //handleChat();
        }
        catch(IOException err) {
             System.out.println("Error: " + err.getMessage());
        }
    }
}
