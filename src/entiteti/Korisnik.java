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
public class Korisnik extends Biblioteka {

    private int id_korisnik;
    private String korisnicko_ime;
    private String sifra;
    private int admin;

    public Korisnik() {
    }

    public Korisnik(int id_korisnik, String korisnicko_ime, String sifra) {
        this.id_korisnik = id_korisnik;
        this.korisnicko_ime = korisnicko_ime;
        this.sifra = sifra;
    }

    public Korisnik(String korisnicko_ime, String sifra, int id_biblioteka) {
        super(id_biblioteka);
        this.korisnicko_ime = korisnicko_ime;
        this.sifra = sifra;
    }

    public Korisnik(String korisnicko_ime, String sifra) {
        this.korisnicko_ime = korisnicko_ime;
        this.sifra = sifra;
    }

 

    public int getId_korisnik() {
        return id_korisnik;
    }

    public void setId_korisnik(int id_korisnik) {
        this.id_korisnik = id_korisnik;
    }

    public String getKorisnicko_ime() {
        return korisnicko_ime;
    }

    public void setKorisnicko_ime(String korisnicko_ime) {
        this.korisnicko_ime = korisnicko_ime;
    }

    public String getSifra() {
        return sifra;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    @Override
    public String toString() {
        return "Korisnik{" + "id_korisnik=" + id_korisnik + ", korisnicko_ime=" + korisnicko_ime + ", sifra=" + sifra + ", admin=" + admin + '}';
    }

}
