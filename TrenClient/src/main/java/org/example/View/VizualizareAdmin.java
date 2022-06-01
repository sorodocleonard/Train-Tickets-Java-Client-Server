package org.example.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class VizualizareAdmin extends JFrame {
    public JLabel userL = new JLabel ("Username");
    public JLabel passL = new JLabel ("Password");
    public JLabel rolL = new JLabel ("Rol");
    public JLabel telL = new JLabel ("Numar telefon");
    public JLabel emailL = new JLabel ("Email");


    public JTextField userT = new JTextField(35);
    public JTextField passT = new JTextField(50);
    public JTextField rolT = new JTextField(50);
    public JTextField telT = new JTextField(50);
    public JTextField emailT = new JTextField(50);



    public JButton b1 = new JButton("Adauga");
    public JButton b2 = new JButton("Sterge");
    public JButton b4 = new JButton("Lista admini");
    public JButton b3 = new JButton("Lista angajati");

    private void initComponent() {
        getContentPane().setLayout(null);
        b1.setBounds(200, 10, 100, 20);
        getContentPane().add(b1);
        b2.setBounds(200, 40, 100, 20);
        getContentPane().add(b2);
        b3.setBounds(200, 70, 200, 20);
        getContentPane().add(b3);
        b4.setBounds(200, 100, 200, 20);
        getContentPane().add(b4);

        userT.setBounds(20, 40, 150, 20);
        getContentPane().add(userT);
        passT.setBounds(20, 100, 150, 20);
        getContentPane().add(passT);
        rolT.setBounds(20, 160, 150, 20);
        getContentPane().add(rolT);
        telT.setBounds(20, 220, 150, 20);
        getContentPane().add(telT);
        emailT.setBounds(20, 280, 150, 20);
        getContentPane().add(emailT);


        userL.setBounds(20, 10, 80, 20);
        getContentPane().add(userL);
        passL.setBounds(20, 70, 120, 20);
        getContentPane().add(passL);
        rolL.setBounds(20, 130, 80, 20);
        getContentPane().add(rolL);
        telL.setBounds(20, 190, 80, 20);
        getContentPane().add(telL);
        emailL.setBounds(20, 250, 80, 20);
        getContentPane().add(emailL);

    }
    public VizualizareAdmin(){
        setTitle("Administrator");
        setSize(500, 400);
        setLocation(new Point(800, 400));
        setResizable(false);
        initComponent();
    }
    public void addActionListener(ActionListener c){
        b1.addActionListener(c);
        b2.addActionListener(c);
        b3.addActionListener(c);
        b4.addActionListener(c);
    }
}