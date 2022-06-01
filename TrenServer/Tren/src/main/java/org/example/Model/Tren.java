package org.example.Model;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;



@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Tren implements Serializable {

    @XmlElementWrapper(name = "listaStatii")
    @XmlElement(name = "statie")
    public List<String> statii;

    @XmlElement(name= "numarTren")
    public int numarTren;

    @XmlElement(name= "locuriLibere")
    public int locuriLibere;

    public Tren() {
    }

    public Tren(List<String> statii, int numarTren, int locuriLibere) {
        this.statii = statii;
        this.numarTren = numarTren;
        this.locuriLibere = locuriLibere;
    }

    public Tren(Tren tren){
    }

    public List<String> getStatii() {
        return statii;
    }

    public void setStatii(List<String> statii) {
        this.statii = statii;
    }

    public int getNumarTren() {
        return numarTren;
    }

    public void setNumarTren(int numarTren) {
        this.numarTren = numarTren;
    }

    public int getLocuriLibere() {
        return locuriLibere;
    }

    public void setLocuriLibere(int locuriLibere) {
        this.locuriLibere = locuriLibere;
    }


    @Override
    public String toString() {
        return "Trenul " + numarTren + " cu statiile " + statii + " are " + locuriLibere + " locuri disponibile";
    }
}