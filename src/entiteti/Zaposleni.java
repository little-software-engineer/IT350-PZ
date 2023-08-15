/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

/**
 *
 * @author PC
 */
public class Zaposleni {

    private int id_zaposleni;
    private int id_biblioteka;
    private String pozicija;
    private String ime;

    public Zaposleni() {
    }

    public Zaposleni(int id_zaposleni, int id_biblioteka, String pozicija, String ime) {
        this.id_zaposleni = id_zaposleni;
        this.id_biblioteka = id_biblioteka;
        this.pozicija = pozicija;
        this.ime = ime;
    }

    public Zaposleni(int id_biblioteka, String pozicija, String ime) {
        this.id_biblioteka = id_biblioteka;
        this.pozicija = pozicija;
        this.ime = ime;
    }

    public int getId_biblioteka() {
        return id_biblioteka;
    }

    public void setId_biblioteka(int id_biblioteka) {
        this.id_biblioteka = id_biblioteka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getId_zaposleni() {
        return id_zaposleni;
    }

    public void setId_zaposleni(int id_zaposleni) {
        this.id_zaposleni = id_zaposleni;
    }

    public String getPozicija() {
        return pozicija;
    }

    public void setPozicija(String pozicija) {
        this.pozicija = pozicija;
    }

    @Override
    public String toString() {
        return "Zaposleni{" + "id_zaposleni=" + id_zaposleni + ", pozicija=" + pozicija + '}';
    }

}
