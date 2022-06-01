package org.example.Model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Utilizator implements Serializable {

    @XmlElement(name= "username")
    public String username;

    @XmlElement(name= "pass")
    public String pass;
    public String tel;
    public String email;

    @XmlElement(name= "rol")
    public String rol;

    public Utilizator(String username, String pass, String rol, String tel, String email) {
        this.username = username;
        this.pass = pass;
        this.rol = rol;
        this.email=email;
        this.tel=tel;
    }

    public Utilizator() {
    }

    public Utilizator(Utilizator utilizator){

    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Utilizator{" +
                "username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}