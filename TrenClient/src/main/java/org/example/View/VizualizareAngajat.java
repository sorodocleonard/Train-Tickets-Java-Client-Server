package org.example.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VizualizareAngajat extends JFrame {
    public JLabel numarL = new JLabel ("Nr. Tren");
    public JLabel plecareL = new JLabel ("Plecare");
    public JLabel destL = new JLabel ("Destinatie");
    public JLabel locuriL = new JLabel ("Locuri libere");

    public JTextField numarT = new JTextField(35);
    public JTextField plecareT = new JTextField(50);
    public JTextField destT = new JTextField(50);
    public JTextField locuriT = new JTextField(50);

    public JButton b1 = new JButton("Adauga");
    public JButton b2 = new JButton("Sterge");
    public JButton b3 = new JButton("Vinde bilet");
    public JButton b4 = new JButton("Raport Json");
    public JButton b5 = new JButton("Raport Csv");
    private void initComponent() {
        getContentPane().setLayout(null);
        b1.setBounds(200, 10, 100, 20);
        getContentPane().add(b1);
        b2.setBounds(200, 40, 100, 20);
        getContentPane().add(b2);
        b3.setBounds(200, 70, 150, 20);
        getContentPane().add(b3);
        b4.setBounds(200, 100, 150, 20);
        getContentPane().add(b4);
        b5.setBounds(200, 130, 150, 20);
        getContentPane().add(b5);

        numarT.setBounds(20, 40, 150, 20);
        getContentPane().add(numarT);
        plecareT.setBounds(20, 100, 150, 20);
        getContentPane().add(plecareT);
        destT.setBounds(20, 160, 150, 20);
        getContentPane().add(destT);
        locuriT.setBounds(20, 210, 150, 20);
        getContentPane().add(locuriT);

        numarL.setBounds(20, 10, 120, 20);
        getContentPane().add(numarL);
        plecareL.setBounds(20, 70, 80, 20);
        getContentPane().add(plecareL);
        destL.setBounds(20, 130, 80, 20);
        getContentPane().add(destL);
        locuriL.setBounds(20, 190, 150, 20);
        getContentPane().add(locuriL);
    }
    public VizualizareAngajat(){
        setTitle("Angajat");
        setSize(500, 400);
        setLocation(new Point(800, 400));
        setResizable(false);

        initComponent();
    }
    public void addActionListener(ActionListener d){
        b1.addActionListener(d);
        b2.addActionListener(d);
        b3.addActionListener(d);
        b4.addActionListener(d);
        b5.addActionListener(d);
    }
}