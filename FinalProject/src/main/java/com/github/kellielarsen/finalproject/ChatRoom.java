package com.github.kellielarsen.finalproject;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/* @author kellie */
public class ChatRoom implements ActionListener {
    
    String name = "Chatroom: Server";
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
        sendMsg.addActionListener(this);
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
        try (ServerSocket listen = new ServerSocket(5000)) {
            while (true) {
                try {
                    System.out.println("Waiting for connection. . .");
                    sock = listen.accept();
                    System.out.println("Connection from client");
                    out = new DataOutputStream(sock.getOutputStream());
                    in = new DataInputStream(sock.getInputStream());
                    while (true) {
                        //receive messages
                        String msg = in.readUTF();
                        chat.append("User: " + msg + "\n");
                    }
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
    
    public static void main(String args[]) throws IOException {
        ChatRoom c = new ChatRoom();
        c.display();
        c.start();
    }
}
