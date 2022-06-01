package org.example.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Locale;

public class VizualizareCalator extends JFrame {
    public JLabel numarL = new JLabel ("Nr. Tren");
    public JLabel plecareL = new JLabel ("Plecare");
    public JLabel destL = new JLabel ("Destinatie");
    public JLabel rezL = new JLabel ("");

    public JTextField numarT = new JTextField(35);
    public JTextField plecareT = new JTextField(50);
    public JTextField destT = new JTextField(50);

    public JButton b1 = new JButton("Cauta");
    public JButton b2 = new JButton("Cauta");
    public JButton b3 = new JButton("Lista trenuri");

    public Locale locale_en_EN = new Locale("en","EN");
    public Locale locale_ro_RO = new Locale("ro","RO");
    public Locale locale_fr_FR = new Locale("fr","FR");
    public Locale locale_it_IT = new Locale("it","IT");
    public JComboBox<Locale> selBox = new JComboBox<Locale>();

    private void initComponent() {
        getContentPane().setLayout(null);
        b1.setBounds(20, 70, 130, 20);
        getContentPane().add(b1);
        b2.setBounds(20, 220, 130, 20);
        getContentPane().add(b2);
        b3.setBounds(350, 70, 130, 20);
        getContentPane().add(b3);

        numarT.setBounds(20, 40, 150, 20);
        getContentPane().add(numarT);
        plecareT.setBounds(20, 130, 300, 20);
        getContentPane().add(plecareT);
        destT.setBounds(20, 190, 150, 20);
        getContentPane().add(destT);

        numarL.setBounds(20, 10, 120, 20);
        getContentPane().add(numarL);
        plecareL.setBounds(20, 100, 80, 20);
        getContentPane().add(plecareL);
        destL.setBounds(20, 160, 80, 20);
        getContentPane().add(destL);
        rezL.setBounds(20, 250, 600, 50);
        getContentPane().add(rezL);

        selBox.setBounds(400,200,80,20);
        selBox.addItem(locale_en_EN);
        selBox.addItem(locale_ro_RO);
        selBox.addItem(locale_fr_FR);
        selBox.addItem(locale_it_IT);
        getContentPane().add(selBox);
        selBox.setSelectedIndex(1);
    }
    public VizualizareCalator(){
        setTitle("Trenuri");
        setSize(700, 400);
        setLocation(new Point(100, 400));
        setResizable(false);

        initComponent();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void addActionListener(ActionListener b){
        b1.addActionListener(b);
        b2.addActionListener(b);
        b3.addActionListener(b);
        selBox.addActionListener(b);
    }
}