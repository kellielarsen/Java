package com.github.kellielarsen.finalproject;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/* @author kellie */
public class ChatRoom {
    
    Socket sock;
    DataOutputStream out;
    DataInputStream in;
    static Set<User> users = new HashSet<>();
    static int i = 0;
    
    public void start() {
        try (ServerSocket listen = new ServerSocket(5000)) {
            while (true) {
                try {
                    boolean validUsername = false;
                    System.out.println("Waiting for connection. . .");
                    sock = listen.accept();
                    System.out.println("Connection from client");
                    out = new DataOutputStream(sock.getOutputStream());
                    in = new DataInputStream(sock.getInputStream());
                    String username = "";
                    while (!validUsername) {
                        username = in.readUTF();
                        if (username.length() > 1) {
                            if (users.isEmpty()) {
                                validUsername = true;
                            }
                            for (User user : users) {
                                if (user.username.equals(username)) {
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
                    User u = new User(username, i, sock, out, in);
                    users.add(u);
                    Thread t = new Thread(u);
                    t.start();
                    i++;
                }
                catch (IOException err) {
                    System.out.println("Error: " + err.getMessage());
                }
            }
        }
        catch (IOException err) {
            System.out.println("Error: " + err.getMessage());
        }
    }
    
    public static void main(String args[]) throws IOException {
        ChatRoom c = new ChatRoom();
        c.start();
    }
}

class User implements Runnable {
    String username;
    int clientNum;
    DataOutputStream out;
    DataInputStream in;
    Socket sock;
    String name = "Chatroom: Client";
    JFrame chatFrame = new JFrame(name);
    JFrame loginFrame = new JFrame("Join Chat");
    JButton sendMsg;
    JButton joinChat;
    JTextField msgBox;
    JTextField userName;
    JTextArea chat;
    boolean validUsername;
    
    public void display() {
        chatFrame.setSize(500, 300);
        JPanel main = new JPanel();
        JPanel bottom = new JPanel();
        main.setLayout(new BorderLayout());
        bottom.setLayout(new GridBagLayout());
        msgBox = new JTextField(30);
        sendMsg = new JButton(new AbstractAction("Send") {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (msgBox.getText().length() < 1) {
                    // do nothing
                } else if (msgBox.getText().equals("cls")) {
                    chat.setText("");
                    msgBox.setText("");
                } else {
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
        chatFrame.add(main);
        chatFrame.setVisible(true);
    }
        
    public User(String user, int i, Socket s, DataOutputStream _out, DataInputStream _in) {
        this.username = user;
        this.clientNum = i;
        this.out = _out;
        this.in = _in;
        this.sock = s;
    }

    @Override
    public void run() {
        try {
            display();
            while (true) {
                String msg = in.readUTF();
                for (User user : ChatRoom.users) {
                    if (!user.username.equals(this.username))
                        chat.append(this.username + ": " + msg + "\n");
                }
            }
        }
        catch (IOException err) {
            System.out.println("Error: " + err.getMessage());
        }
    }
}
