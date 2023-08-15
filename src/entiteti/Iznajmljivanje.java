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
public class Iznajmljivanje extends Knjiga {

    private int id_iznajmljivanja;
    private Korisnik korisnik;
    private String rok_vracanja;
    private int broj_izn_knjiga;
    private int id_knjiga;
    private int id_biblioteka;
    private int id_korisnik;

    public Iznajmljivanje() {
    }

    public Iznajmljivanje(int id_iznajmljivanja, Korisnik korisnik, String rok_vracanja, int broj_izn_knjiga) {
        this.id_iznajmljivanja = id_iznajmljivanja;
        this.korisnik = korisnik;
        this.rok_vracanja = rok_vracanja;
        this.broj_izn_knjiga = broj_izn_knjiga;
    }

    public Iznajmljivanje(int id_iznajmljivanja, Korisnik korisnik, String rok_vracanja, int broj_izn_knjiga, int id_knjiga, String isbn, String naziv, int godina_izdavanja) {
        super(id_knjiga, isbn, naziv, godina_izdavanja);
        this.id_iznajmljivanja = id_iznajmljivanja;
        this.korisnik = korisnik;
        this.rok_vracanja = rok_vracanja;
        this.broj_izn_knjiga = broj_izn_knjiga;
    }

    public Iznajmljivanje(int id_korisnik, String rok_vracanja, int id_knjiga, int broj_izn_knjiga, int id_biblioteka) {
        this.id_korisnik = id_korisnik;
        this.rok_vracanja = rok_vracanja;
        this.id_knjiga = id_knjiga;
        this.broj_izn_knjiga = broj_izn_knjiga;

        this.id_biblioteka = id_biblioteka;

    }

    public Iznajmljivanje(int id_iznajmljivanja, int id_korisnik, String rok_vracanja, int id_knjiga, int broj_izn_knjiga, int id_biblioteka) {
        this.id_iznajmljivanja = id_iznajmljivanja;
        this.id_korisnik = id_korisnik;
        this.rok_vracanja = rok_vracanja;
        this.id_knjiga = id_knjiga;
        this.broj_izn_knjiga = broj_izn_knjiga;
        this.id_biblioteka = id_biblioteka;

    }

    public int getId_korisnik() {
        return id_korisnik;
    }

    public void setId_korisnik(int id_korisnik) {
        this.id_korisnik = id_korisnik;
    }

    public Iznajmljivanje(int id_iznajmljivanja, Korisnik korisnik, String rok_vracanja, int broj_izn_knjiga, int id_knjiga, int id_biblioteka) {
        this.id_iznajmljivanja = id_iznajmljivanja;
        this.korisnik = korisnik;
        this.rok_vracanja = rok_vracanja;
        this.broj_izn_knjiga = broj_izn_knjiga;
        this.id_knjiga = id_knjiga;
        this.id_biblioteka = id_biblioteka;
    }

    public Iznajmljivanje(Korisnik korisnik, String rok_vracanja, int broj_izn_knjiga, int id_knjiga, int id_biblioteka) {
        this.korisnik = korisnik;
        this.rok_vracanja = rok_vracanja;
        this.broj_izn_knjiga = broj_izn_knjiga;
        this.id_knjiga = id_knjiga;
        this.id_biblioteka = id_biblioteka;
    }

    public int getId_biblioteka() {
        return id_biblioteka;
    }

    public void setId_biblioteka(int id_biblioteka) {
        this.id_biblioteka = id_biblioteka;
    }

    public Iznajmljivanje(int id_iznajmljivanja, Korisnik korisnik, String rok_vracanja, int broj_izn_knjiga, int id_knjiga) {
        this.id_iznajmljivanja = id_iznajmljivanja;
        this.korisnik = korisnik;
        this.rok_vracanja = rok_vracanja;
        this.broj_izn_knjiga = broj_izn_knjiga;
        this.id_knjiga = id_knjiga;
    }

    public int getId_knjiga() {
        return id_knjiga;
    }

    public void setId_knjiga(int id_knjiga) {
        this.id_knjiga = id_knjiga;
    }

    public int getBroj_izn_knjiga() {
        return broj_izn_knjiga;
    }

    public void setBroj_izn_knjiga(int broj_izn_knjiga) {
        this.broj_izn_knjiga = broj_izn_knjiga;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public int getId_iznajmljivanja() {
        return id_iznajmljivanja;
    }

    public void setId_iznajmljivanja(int id_iznajmljivanja) {
        this.id_iznajmljivanja = id_iznajmljivanja;
    }

    public String getRok_vracanja() {
        return rok_vracanja;
    }

    public void setRok_vracanja(String rok_vracanja) {
        this.rok_vracanja = rok_vracanja;
    }

    @Override
    public String toString() {
        return "Iznajmljivanje{" + "id_iznajmljivanja=" + id_iznajmljivanja + ", korisnik=" + korisnik + ", rok_vracanja=" + rok_vracanja + ", broj_izn_knjiga=" + broj_izn_knjiga + '}';
    }

}
