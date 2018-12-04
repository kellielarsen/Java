package com.github.kellielarsen.finalproject;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/* @author kellie */
public class Client {
    private static String host;
    private static int port;
    public Socket sock;
    JFrame loginFrame = new JFrame("Join Chat");
    JFrame chatFrame;
    JTextField usernameField;
    JButton joinChat;
    JButton sendMsg;
    JTextField msgBox;
    JTextArea chat;
    String username;
    DataInputStream in;
    DataOutputStream out;
    boolean loggedIn;
    
    public Client(String _host, int _port) {
        host = _host;
        port = _port;
    }
    
    private void login() {
        loginFrame.setSize(500, 300);
        JPanel main = new JPanel();
        JPanel bottom = new JPanel();
        main.setLayout(new BorderLayout());
        bottom.setLayout(new GridBagLayout());
        usernameField = new JTextField(30);
        JTextArea err = new JTextArea("Please choose a username:\n");
        err.setEditable(false);
        err.setFont(new Font("Arial", Font.PLAIN, 15));
        err.setLineWrap(true);
        joinChat = new JButton(new AbstractAction("Join chat") {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    out.writeUTF(usernameField.getText());
                    switch (in.readUTF()) {
                        case "valid":
                            username = usernameField.getText();
                            loggedIn = true;
                            loginFrame.dispose();
                            display();
                            break;
                        case "short":
                            err.setText("Username is too short. Try again\n");
                            break;
                        case "taken":
                            err.setText("Username is taken. Try again\n");
                            break;
                        default:
                            err.setText("Error\n");
                    }
                }
                catch (IOException err) {
                    System.out.println("Error: " + err);
                }
            }
        });
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
        bottom.add(usernameField, left);
        bottom.add(joinChat, right);
        main.add(err);
        main.add(BorderLayout.SOUTH, bottom);
        loginFrame.add(main);
        loginFrame.setVisible(true);
    }
    
    public void display() {
        chatFrame = new JFrame("Logged in as: " + this.username);
        chatFrame.setSize(500, 300);
        chatFrame.setBackground(Color.pink);
        JPanel main = new JPanel();
        JPanel bottom = new JPanel();
        main.setLayout(new BorderLayout());
        bottom.setLayout(new GridBagLayout());
        msgBox = new JTextField(30);
        sendMsg = new JButton(new AbstractAction("Send") {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (msgBox.getText().length() < 1) {
                    //do nothing
                }
                else if (msgBox.getText().equals("cls")) {
                    chat.setText("");
                    msgBox.setText("");
                }
                else if (msgBox.getText().equals("logout")) {
                    try {
                        out.writeUTF(msgBox.getText());
                        sock.close();
                        System.exit(0);
                    }
                    catch (IOException err) {
                        System.out.println("Error: " + err.getMessage());
                    }
                    chatFrame.dispose();
                }
                else {
                    chat.append("Me: " + msgBox.getText() + "\n");
                    try {
                        out.writeUTF(msgBox.getText());
                    }
                    catch (IOException err) {
                        System.out.println("Error: " + err.getMessage());
                    }
                    msgBox.setText("");
                }
                msgBox.requestFocusInWindow();
            }
        });
        chat = new JTextArea();
        chat.setEditable(false);
        chat.setFont(new Font("Arial", Font.PLAIN, 15));
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
        chatFrame.add(main);
        chatFrame.setVisible(true);
        startReading();
    }
    
    public void connect() {
        try {
            sock = new Socket(host, port);
            in = new DataInputStream(sock.getInputStream());
            out = new DataOutputStream(sock.getOutputStream());
        } catch (IOException err) {
            System.out.println("Error: " + err.getMessage());
        }
        login();
    }
    
    private void startReading() {
        Thread t = new Thread() {
            @Override
            public void run() {
                readMsgs();
            }
        };
        t.start();
    }
    
    private void readMsgs() {
        String inMsg;
        while (true) {
            try {
                inMsg = in.readUTF();
                chat.append(inMsg + "\n");
            }
            catch (IOException err) {
                System.out.println("Error: " + err.getMessage());
            }
        }
    }
    
    public static void main(String args[]) {
        Client c = new Client("127.0.0.1", 5000);
        c.connect();
    }
}
