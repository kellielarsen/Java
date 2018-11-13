package com.github.kellielarsen.finalproject;

import java.io.*;
import java.net.*;
import java.awt.*;
import javax.swing.*;

/* @author kellie */
public class Client {
    
    String name = "Chatroom: Client";
    JFrame newFrame = new JFrame(name);
    JButton sendMsg;
    JTextField msgBox;
    JTextArea chat;
    Socket sock;
    DataOutputStream out;
    DataInputStream in;
    
    public void display() {
        newFrame.setSize(500, 300);
        JPanel main = new JPanel();
        JPanel bottom = new JPanel();
        main.setLayout(new BorderLayout());
        bottom.setLayout(new GridBagLayout());
        msgBox = new JTextField(30);
        sendMsg = new JButton("Send");
        chat = new JTextArea();
        chat.setEditable(false);
        chat.setFont(new Font("Serif", Font.PLAIN, 15));
        chat.setLineWrap(true);
        main.add(new JScrollPane(chat), BorderLayout.CENTER);
        GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.LINE_START;
        left.fill = GridBagConstraints.HORIZONTAL;
        left.weightx = 512.0D;
        left.weighty = 1.0D;
        GridBagConstraints right = new GridBagConstraints();
        right.insets = new Insets(0, 10, 0, 0);
        right.anchor = GridBagConstraints.LINE_END;
        right.fill = GridBagConstraints.NONE;
        right.weightx = 1.0D;
        right.weighty = 1.0D;
        bottom.add(msgBox, left);
        bottom.add(sendMsg, right);
        main.add(BorderLayout.SOUTH, bottom);
        newFrame.add(main);
        newFrame.setVisible(true);
    }
    
    public void start() {
        try {
            sock = new Socket("127.0.0.1", 5000);
            out = new DataOutputStream(sock.getOutputStream());
            in = new DataInputStream(sock.getInputStream());
            while (true) {
                //receive messages
            }
        }
        catch (IOException err) {
            System.out.println("Error: " + err.getMessage());
        }
    }
    
    public static void main(String args[]) {
        Client c = new Client();
        c.display();
        c.start();
    }
}
