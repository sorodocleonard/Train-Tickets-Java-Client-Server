package org.example.Controller;

import org.example.Command.Client;
import org.example.Model.Administrator;
import org.example.Model.Angajat;
import org.example.Model.Tren;
import org.example.View.VizualizareAdmin;
import org.example.View.VizualizareAngajat;
import org.example.View.VizualizareCalator;
import org.example.View.VizualizareLogin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controller {
    public VizualizareLogin cv;
    public VizualizareCalator vizCal;
    public VizualizareAdmin vAdmin;
    public VizualizareAngajat vAng;
    public Client client;
    public Controller(){
        cv = new VizualizareLogin();
        vizCal = new VizualizareCalator();
        vAdmin = new VizualizareAdmin();
        vAng = new VizualizareAngajat();
        cv.addActionListener(new Listener());
        vizCal.addActionListener(new Listener());
        this.client = new Client();

    }
    class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==cv.b1){
                if (cv.rolT.getText().equals("administrator")) {
                    List<Administrator> admins = new ArrayList<Administrator>();
                    try {
                        admins = (List<Administrator>) client.requestHandler("user admin all");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }


                    for (Administrator a : admins) {
                        if (a.getUsername().equals(cv.usernameT.getText())) {
                            if (a.getPass().equals(cv.passT.getText())) {
                                cv.rezultat.setText("Autentificare reusita");
                                vAdmin = new VizualizareAdmin();
                                vAdmin.addActionListener(new Listener());
                                vAdmin.setVisible(true);
                                cv.usernameT.setText("");
                                cv.passT.setText("");
                            } else {
                                cv.rezultat.setText("Parola gresita");
                            }
                        } else {
                            cv.rezultat.setText("Username gresit");
                        }
                    }

                } else if (cv.rolT.getText().equals("angajat")) {
                    List<Angajat> angajati = new ArrayList<Angajat>();
                    try {
                        angajati = (List<Angajat>) client.requestHandler("user angajat all");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }

                        for (Angajat a : angajati) {
                            if (a.getUsername().equals(cv.usernameT.getText())) {
                                if (a.getPass().equals(cv.passT.getText())) {
                                    cv.rezultat.setText("Autentificare reusita");
                                    vAng = new VizualizareAngajat();
                                    vAng.addActionListener(new Listener());
                                    vAng.setVisible(true);
                                    cv.usernameT.setText("");
                                    cv.passT.setText("");
                                } else {
                                    cv.rezultat.setText("Parola gresita");
                                }
                            } else {
                                cv.rezultat.setText("Username gresit");
                            }
                        }
                    }
            }
            if(e.getSource()==vAdmin.b1){
                try {
                    client.requestHandler("user" + " " + "add" + " " +vAdmin.userT.getText() + " " +
                            vAdmin.passT.getText() + " " +
                            vAdmin.rolT.getText() + " " +
                            vAdmin.telT.getText() + " " +
                            vAdmin.emailT.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                vAdmin.userT.setText("");
                vAdmin.passT.setText("");
                vAdmin.rolT.setText("");
                vAdmin.telT.setText("");
                vAdmin.emailT.setText("");
            }

            if(e.getSource()==vAdmin.b2){
                try {
                    client.requestHandler("user" + " " + "del" + " " +
                            vAdmin.rolT.getText() + " " +
                            vAdmin.userT.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                vAdmin.userT.setText("");
                vAdmin.passT.setText("");
                vAdmin.rolT.setText("");
                vAdmin.telT.setText("");
                vAdmin.emailT.setText("");
            }

            if(e.getSource()==vAdmin.b3){
                List<Angajat> ang = new ArrayList<Angajat>();
                try {
                    ang= (List<Angajat>) client.requestHandler("user angajat all");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                JFrame frame=new JFrame();
                frame.pack();
                frame.setSize(400,300);
                frame.setTitle("Angajati");
                frame.setLocation(new Point(600, 100));
                String[] columnNames = {"Username",
                        "Password",
                        "Rol"};
                DefaultTableModel model = new DefaultTableModel();

                JTable table = new JTable();
                table.setModel(model);
                model.setColumnIdentifiers(columnNames);

                for (Angajat s : ang) {
                    Object[] o = new Object[3];
                    o[0] = s.getUsername();
                    o[1] = s.getPass();
                    o[2] = s.getRol();
                    model.addRow(o);
                }
                JScrollPane scrollPane = new JScrollPane(table);
                table.setFillsViewportHeight(true);
                frame.setLayout(new BorderLayout());
                frame.add(table.getTableHeader(), BorderLayout.PAGE_START);
                frame.add(new JScrollPane(table), BorderLayout.CENTER);
                table.validate();
                frame.setVisible(true);
            }
            if(e.getSource()==vAdmin.b4){
                List<Administrator> admins = new ArrayList<Administrator>();
                try {
                    admins = (List<Administrator>) client.requestHandler("user admin all");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                JFrame frame=new JFrame();
                frame.pack();
                frame.setSize(400,300);
                frame.setTitle("Administratori");
                frame.setLocation(new Point(100, 100));
                String[] columnNames = {"Username",
                        "Password",
                        "Rol"};
                DefaultTableModel model = new DefaultTableModel();

                JTable table = new JTable();
                table.setModel(model);
                model.setColumnIdentifiers(columnNames);

                for (Administrator s : admins) {
                    Object[] o = new Object[3];
                    o[0] = s.getUsername();
                    o[1] = s.getPass();
                    o[2] = s.getRol();
                    model.addRow(o);
                }
                JScrollPane scrollPane = new JScrollPane(table);
                table.setFillsViewportHeight(true);
                frame.setLayout(new BorderLayout());
                frame.add(table.getTableHeader(), BorderLayout.PAGE_START);
                frame.add(new JScrollPane(table), BorderLayout.CENTER);
                table.validate();
                frame.setVisible(true);
            }
            if(e.getSource()==vAng.b1){
                try {
                    client.requestHandler("tren" + " " + "add" + " " +
                            vAng.numarT.getText() + " " +
                            vAng.plecareT.getText() + " " +
                            vAng.destT.getText() + " " +
                            vAng.locuriT.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                vAng.numarT.setText("");
                vAng.plecareT.setText("");
                vAng.destT.setText("");
                vAng.locuriT.setText("");
            }
            if(e.getSource()==vAng.b2){
                try {
                    client.requestHandler("tren" + " " + "del" + " " +
                            vAng.numarT.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                vAng.numarT.setText("");
                vAng.plecareT.setText("");
                vAng.destT.setText("");
                vAng.locuriT.setText("");
            }
            if(e.getSource()==vAng.b3){
                try {
                    client.requestHandler("tren" + " " + "sell" + " " +
                            vAng.numarT.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                vAng.numarT.setText("");
                vAng.plecareT.setText("");
                vAng.destT.setText("");
                vAng.locuriT.setText("");
            }
            if(e.getSource()==vAng.b4){
                try {
                    client.requestHandler("tren" + " " + "raport" + " " + "csv");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if(e.getSource()==vAng.b5){
                try {
                    client.requestHandler("tren" + " " + "raport" + " " + "json");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

            }
            if(e.getSource()==vizCal.b1){
                String txt;
                int ok=0;
                txt=vizCal.numarT.getText();
                List<Tren> trenuri = new ArrayList<Tren>();
                try {
                    trenuri = (List<Tren>) client.requestHandler("tren" + " " + "all");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                for (Tren t: trenuri) {
                    if(t.getNumarTren()==Integer.parseInt(txt)){
                        vizCal.rezL.setText(t.toString());
                        ok=1;
                        break;
                    }
                }
                if(ok==0){
                    vizCal.rezL.setText("Nu s-au gasit rezultate");
                }
            }
            if(e.getSource()==vizCal.b2){
                String txt,txt1;
                int ok=0;
                txt=vizCal.plecareT.getText();
                txt1=vizCal.destT.getText();
                List<Tren> trenuri = new ArrayList<Tren>();
                try {
                    trenuri = (List<Tren>) client.requestHandler("tren" + " " + "all");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                for (Tren t: trenuri) {
                    if(t.getStatii().get(0).equals(txt) && t.getStatii().get(t.getStatii().size()-1).equals(txt1)){
                        vizCal.rezL.setText(t.toString());
                        ok=1;
                        break;
                    }
                }
                if(ok==0){
                    vizCal.rezL.setText("Nu s-au gasit rezultate");
                }
            }
            if(e.getSource()==vizCal.b3) {
                JFrame frame = new JFrame();
                frame.pack();
                frame.setSize(400, 300);
                frame.setTitle("Lista trenuri");
                frame.setLocation(new Point(100, 100));
                String[] columnNames = {"Nr. Tren",
                        "Plecare",
                        "Destinatie",
                        "Locuri libere"};

                List<Tren> trenuri = new ArrayList<Tren>();
                try {
                    trenuri = (List<Tren>) client.requestHandler("tren" + " " + "all");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                DefaultTableModel model = new DefaultTableModel();

                JTable table = new JTable();
                table.setModel(model);
                model.setColumnIdentifiers(columnNames);

                for (Tren s : trenuri) {
                    Object[] o = new Object[4];
                    o[0] = s.getNumarTren();
                    o[1] = s.getStatii().get(0);
                    o[2] = s.getStatii().get(s.getStatii().size() - 1);
                    o[3] = s.getLocuriLibere();
                    model.addRow(o);
                }
                JScrollPane scrollPane = new JScrollPane(table);
                table.setFillsViewportHeight(true);
                frame.setLayout(new BorderLayout());
                frame.add(table.getTableHeader(), BorderLayout.PAGE_START);
                frame.add(new JScrollPane(table), BorderLayout.CENTER);
                table.validate();
                frame.setVisible(true);
            }
            if(e.getSource()==cv.selBox){
                ResourceBundle resBundle = ResourceBundle.getBundle("org.example.Resources.bundle",(Locale)cv.selBox.getSelectedItem());
                cv.usernameL.setText(resBundle.getString("name"));
                cv.passL.setText(resBundle.getString("password"));
                cv.rolL.setText(resBundle.getString("roll"));
                cv.b1.setText(resBundle.getString("login"));
                vAdmin.userL.setText(resBundle.getString("name"));
                vAdmin.passL.setText(resBundle.getString("password"));
                vAdmin.rolL.setText(resBundle.getString("roll"));
                vAdmin.b1.setText(resBundle.getString("add"));
                vAdmin.b2.setText(resBundle.getString("del"));
                vAdmin.b3.setText(resBundle.getString("listA"));
                vAdmin.b4.setText(resBundle.getString("listAng"));
                vAng.numarL.setText(resBundle.getString("nr"));
                vAng.plecareL.setText(resBundle.getString("start"));
                vAng.destL.setText(resBundle.getString("stop"));
                vAng.locuriL.setText(resBundle.getString("loc"));
                vAng.b1.setText(resBundle.getString("add"));
                vAng.b2.setText(resBundle.getString("del"));
                vAng.b3.setText(resBundle.getString("sell"));
                vAng.b4.setText(resBundle.getString("rapJs"));
                vAng.b5.setText(resBundle.getString("rapCsv"));
            }

            if(e.getSource()==vizCal.selBox){
                ResourceBundle resBundle = ResourceBundle.getBundle("org.example.Resources.bundle",(Locale)vizCal.selBox.getSelectedItem());
                vizCal.numarL.setText(resBundle.getString("nr"));
                vizCal.plecareL.setText(resBundle.getString("start"));
                vizCal.destL.setText(resBundle.getString("stop"));
                vizCal.b1.setText(resBundle.getString("src"));
                vizCal.b2.setText(resBundle.getString("src"));
                vizCal.b3.setText(resBundle.getString("listT"));

            }



        }
    }
}