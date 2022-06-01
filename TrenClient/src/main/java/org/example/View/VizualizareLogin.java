package org.example.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Locale;

public class VizualizareLogin extends JFrame {
    public JLabel usernameL = new JLabel ("Username");
    public JLabel passL = new JLabel ("Password");
    public JLabel rolL = new JLabel ("Rol");
    public JLabel rezultat = new JLabel ("");

    public JTextField usernameT = new JTextField(35);
    public JTextField passT = new JTextField(50);
    public JTextField rolT = new JTextField(50);


    public JButton b1 = new JButton("Login");
    public Locale locale_en_EN = new Locale("en","EN");
    public Locale locale_ro_RO = new Locale("ro","RO");
    public Locale locale_fr_FR = new Locale("fr","FR");
    public Locale locale_it_IT = new Locale("it","IT");
    public JComboBox<Locale> selBox = new JComboBox<Locale>();

    private void initComponent() {
        getContentPane().setLayout(null);
        b1.setBounds(20, 200, 100, 20);
        getContentPane().add(b1);


        usernameT.setBounds(20, 40, 150, 20);
        getContentPane().add(usernameT);
        passT.setBounds(20, 110, 150, 20);
        getContentPane().add(passT);
        rolT.setBounds(20, 170, 150, 20);
        getContentPane().add(rolT);


        usernameL.setBounds(20, 10, 80, 20);
        getContentPane().add(usernameL);
        passL.setBounds(20, 80, 120, 20);
        getContentPane().add(passL);
        rolL.setBounds(20, 140, 80, 20);
        getContentPane().add(rolL);
        rezultat.setBounds(150, 200, 250, 20);
        getContentPane().add(rezultat);

        selBox.setBounds(400,200,80,20);
        selBox.addItem(locale_en_EN);
        selBox.addItem(locale_ro_RO);
        selBox.addItem(locale_fr_FR);
        selBox.addItem(locale_it_IT);
        getContentPane().add(selBox);
        selBox.setSelectedIndex(1);
    }
    public VizualizareLogin(){
        setTitle("Login");
        setSize(500, 300);
        setLocation(new Point(800, 400));
        setResizable(false);
        initComponent();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void addActionListener(ActionListener a){
        b1.addActionListener(a);
        selBox.addActionListener(a);
    }
}