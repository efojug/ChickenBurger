package com.mihoyo.game;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

public class LoginGUI extends JFrame{
    public static boolean isSuccess = false;
    private static int value = 0;

        private JPanel p;
        private JLabel lblName, lblPwd, lblFailed;
        private JTextField txtName;
        private JPasswordField txtPwd;
        private JButton btnOk;
        private JMenuItem miAbout, miExit;
        private JMenu menu;
        private JMenuBar menuBar;
        private JProgressBar progressBar;

        public void launch() {
            this.setTitle("Login");
            p = new JPanel();
            p.setLayout(null);
            progressBar = new JProgressBar();
            menuBar = new JMenuBar();
            menu = new JMenu("Operation");
            miAbout = new JMenuItem("About");
            miExit = new JMenuItem("Exit");
            lblFailed = new JLabel("IDLE...");
            lblName = new JLabel("Username");
            lblPwd = new JLabel("Password");
            txtName = new JTextField(20);
            txtPwd = new JPasswordField(20);
            txtPwd.setEchoChar('*');
            btnOk = new JButton("Login");
            progressBar.setBounds(10, 5, 260, 15);
            lblName.setBounds(30, 30, 60, 25);
            txtName.setBounds(95, 30, 120, 25);
            lblPwd.setBounds(30, 60, 60, 25);
            txtPwd.setBounds(95, 60, 120, 25);
            btnOk.setBounds(115, 90, 70, 25);
            lblFailed.setBounds(10 , 100 , 120 , 30);
            p.add(progressBar);
            menu.add(miAbout);
            menu.add(miExit);
            p.add(lblFailed);
            p.add(lblName);
            p.add(txtName);
            p.add(lblPwd);
            p.add(txtPwd);
            p.add(btnOk);
            menuBar.add(menu);
            this.setJMenuBar(menuBar);
            this.add(p);
            this.setSize(300, 200);
            this.setLocation(300, 300);
            this.setResizable(false);
            this.setVisible(true);
            this.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {}
                @Override
                public void windowClosing(WindowEvent e) {
                    int opt = JOptionPane.showConfirmDialog(null , "Closing this window will close the game. If you want to continue the game, please enter the correct user name and password. Are you sure you want to close the game?", "Warning!", JOptionPane.YES_NO_OPTION);
                    if (opt == JOptionPane.YES_OPTION) {
                        try {
                            Runtime.getRuntime().exec("taskkill /f /t /im java.exe");
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }
                @Override
                public void windowClosed(WindowEvent e) {}

                @Override
                public void windowIconified(WindowEvent e) {}

                @Override
                public void windowDeiconified(WindowEvent e) {}

                @Override
                public void windowActivated(WindowEvent e) {}

                @Override
                public void windowDeactivated(WindowEvent e) {}});
            this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            miAbout.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Copyright 2021 java.com ALL Rights Reserved", "Important tips", JOptionPane.WARNING_MESSAGE);
            });
            miExit.addActionListener(e -> {
                try {
                    Runtime.getRuntime().exec("taskkill /f /t /im java.exe");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
            btnOk.addActionListener(e -> {
                String strName = txtName.getText();
                String strPwd = new String(txtPwd.getPassword());
                if (strName.equals("admin") && strPwd.equals("123456")) {
                    new Thread(() -> {
                        while (value <= 100) {
                            try {
                                Thread.sleep(2);
                                progressBar.setValue(value);
                                value++;
                            } catch (InterruptedException interruptedException) {
                                interruptedException.printStackTrace();
                            }
                        }
                        lblFailed.setText("Welcome!");
                        try {
                            Thread.sleep(500);
                            isSuccess = true;
                            this.setVisible(false);
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    }).start();
                    progressBar.setValue(0);
                    value = 0;
                } else new Thread(() -> {
                    while (value <= 100) {
                        try {
                            Thread.sleep(2);
                            progressBar.setValue(value);
                            value++;
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    }
                    progressBar.setValue(0);
                    value = 0;
                    lblFailed.setText("Login Failed.");
                    try {
                        Thread.sleep(2000);
                        lblFailed.setText("IDLE...");
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }).start();
            });
        }
    }
