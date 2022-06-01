package org.example.Model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Bilet implements Serializable {

    @XmlElement(name= "plecare")
    public String plecare;

    @XmlElement(name="destinatie")
    public String destinatie;

    @XmlElement(name="numarTren")
    public int numarTren;

    @XmlElement(name="pret")
    public float pret;


    public Bilet() {
    }

    public Bilet(String plecare, String destinatie, int numarTren, float pret) {
        this.plecare = plecare;
        this.destinatie = destinatie;
        this.numarTren = numarTren;
        this.pret = pret;
    }

    public Bilet(Bilet bilet) {
    }

    //@XmlElement(name= "plecare")
    public String getPlecare() {
        return plecare;
    }


    public void setPlecare(String plecare) {
        this.plecare = plecare;
    }

    //@XmlElement(name="destinatie")
    public String getDestinatie() {
        return destinatie;
    }


    public void setDestinatie(String destinatie) {
        this.destinatie = destinatie;
    }

    //@XmlElement(name="numarTren")
    public int getNumarTren() {
        return numarTren;
    }


    public void setNumarTren(int numarTren) {
        this.numarTren = numarTren;
    }

    //@XmlElement(name="pret")
    public float getPret() {
        return pret;
    }


    public void setPret(float pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Bilet{" +
                "plecare='" + plecare + '\'' +
                ", destinatie='" + destinatie + '\'' +
                ", numarTren=" + numarTren +
                ", pret=" + pret +
                '}';
    }
}