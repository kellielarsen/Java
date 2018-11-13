package com.github.kellielarsen.finalproject;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/* @author kellie */
public class Client {
    final static int port = 5000;
    JFrame loginFrame = new JFrame("Join Chat");
    JTextField userName;
    boolean validUsername;
    JButton joinChat;
    
    public void login(Socket sock, DataInputStream in, DataOutputStream out) {
        loginFrame.setSize(500, 300);
        JPanel main = new JPanel();
        JPanel bottom = new JPanel();
        main.setLayout(new BorderLayout());
        bottom.setLayout(new GridBagLayout());
        userName = new JTextField(30);
        JTextArea err = new JTextArea("Please choose a username:\n");
        err.setEditable(false);
        err.setFont(new Font("Serif", Font.PLAIN, 15));
        err.setLineWrap(true);
        joinChat = new JButton(new AbstractAction("Join chat") {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    out.writeUTF(userName.getText());
                    switch (in.readUTF()) {
                        case "valid":
                            loginFrame.dispose();
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
        bottom.add(userName, left);
        bottom.add(joinChat, right);
        main.add(err);
        main.add(BorderLayout.SOUTH, bottom);
        loginFrame.add(main);
        loginFrame.setVisible(true);
    }
    
    public static void main(String args[]) throws IOException {
        Client c = new Client();
        Socket sock = new Socket("127.0.0.1", port);
        DataOutputStream out = new DataOutputStream(sock.getOutputStream());
        DataInputStream in = new DataInputStream(sock.getInputStream());
        c.login(sock, in, out);
    }
}
