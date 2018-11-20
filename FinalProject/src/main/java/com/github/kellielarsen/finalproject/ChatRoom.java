package com.github.kellielarsen.finalproject;

import java.io.*;

/* @author kellie */
public class ChatRoom {
    
    public static void main(String args[]) throws IOException {
        int port = 5000;
        Server server = new Server(port);
        server.start();
    }
}
