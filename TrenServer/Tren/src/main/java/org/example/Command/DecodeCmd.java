package org.example.Command;

import org.example.Model.Administrator;
import org.example.Model.Angajat;
import org.example.Model.Persistenta.PersistentaAdmin;
import org.example.Model.Persistenta.PersistentaAngajat;
import org.example.Model.Persistenta.PersistentaTren;
import org.example.Model.Tren;
import org.example.Res.EmailSender;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DecodeCmd {
    public PersistentaAdmin persAdm;
    public PersistentaAngajat persAng;
    public PersistentaTren persTrn;
    public EmailSender eml;
    public DecodeCmd() {
        this.persAng = new PersistentaAngajat();
        this.persAdm = new PersistentaAdmin();
        this.persTrn = new PersistentaTren();
        this.eml = new EmailSender();
    }
// user add name pss rol tel email / //// add
    // user del rol name //////del
    // tren all
    // tren add nr plec dest locuri
    // tren del nr
    // tren raport tip
    // tren sell nr
    public Object decode(String cmd) throws IOException {
        String split[] = cmd.split(" ", 7);
        List<Administrator> admins = null;
        List<Angajat> angajati = null;
        List<Tren>  trenuri = null;
        if (split[0].equals("user")) {
            if (split[1].equals("admin")) {
                if (split[2].equals("all")) {
                    admins = persAdm.getAll();
                    return admins;
                }
            }else if(split[1].equals("angajat")){
                if (split[2].equals("all")) {
                    angajati = persAng.getAll();
                    return angajati;
                }
            }else if(split[1].equals("add")){
                if(split[4].equals("administrator")){
                    Administrator a = new Administrator();
                    a.setUsername(split[2]);
                    a.setPass(split[3]);
                    a.setRol(split[4]);
                    a.setTel(split[5]);
                    a.setEmail(split[6]);
                    persAdm.insert(a);
                    eml.SendMail(a.getEmail(),"You have created a new admin account! Welcome to Bilete Tren");
                }else if(split[4].equals("angajat")){
                    Angajat a = new Angajat();
                    a.setUsername(split[2]);
                    a.setPass(split[3]);
                    a.setRol(split[4]);
                    a.setTel(split[5]);
                    a.setEmail(split[6]);
                    persAng.insert(a);
                    eml.SendMail(a.getEmail(),"You have created a new employee account! Welcome to Bilete Tren");
                }
            }else if(split[1].equals("del")){
                if(split[2].equals("administrator")){
                    for (Administrator a: persAdm.getAll()) {
                        if(a.getUsername().equals(split[3])){
                            eml.SendMail(a.getEmail(),"Your admin account at Bilete Tren has been deleted! Have a nice day!");
                        }
                    }
                    persAdm.delete(split[3]);
                }
                if(split[2].equals("angajat")){
                    for (Angajat a: persAng.getAll()) {
                        if(a.getUsername().equals(split[3])){
                            eml.SendMail(a.getEmail(),"Your admin account at Bilete Tren has been deleted! Have a nice day!");
                        }
                    }
                    persAng.delete(split[3]);
                }
            }


        }else if(split[0].equals("tren")){
            if(split[1].equals("all")){
                trenuri = persTrn.getAll();
                return trenuri;
                // tren add nr plec dest locuri
            }else if(split[1].equals("add")){
                Tren t = new Tren();
                t.statii= new ArrayList<String>();
                t.setNumarTren(Integer.parseInt(split[2]));
                t.statii.add(split[3]);
                t.statii.add(split[4]);
                t.setLocuriLibere(Integer.parseInt(split[5]));
                persTrn.insert(t);
                // tren del nr
            }else if(split[1].equals("del")){
                persTrn.delete(Integer.parseInt(split[2]));
                // tren raport tip
            }else if (split[1].equals("raport")){
                if(split[2].equals("csv")){
                    persTrn.raportCsv();
                }else{
                    persTrn.raportJson();
                }
            }else if(split[1].equals("sell")){
                persTrn.vinde(Integer.parseInt(split[2]));
            }
        }
        return null;


    }
}