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
public class Knjiga {

    private int id_knjiga;
    private int id_pisac;
    private int id_biblioteka;
    private int id_iznajmljivanja;
    private String isbn;
    private String naziv;
    private int godina_izdavanja;

    public Knjiga() {
    }

    public Knjiga(int id_knjiga, String isbn, String naziv, int godina_izdavanja) {
        this.id_knjiga = id_knjiga;
        this.isbn = isbn;
        this.naziv = naziv;
        this.godina_izdavanja = godina_izdavanja;
    }

    public Knjiga(int id_biblioteka, int id_iznajmljivanja, String isbn, String naziv, int godina_izdavanja, int id_pisac) {
        this.id_biblioteka = id_biblioteka;
        this.id_iznajmljivanja = id_iznajmljivanja;
        this.isbn = isbn;
        this.naziv = naziv;
        this.godina_izdavanja = godina_izdavanja;
        this.id_pisac = id_pisac;

    }

    public Knjiga(int id_knjiga, int id_biblioteka, int id_iznajmljivanja, String isbn, String naziv, int godina_izdavanja, int id_pisac) {
        this.id_knjiga = id_knjiga;
        this.id_biblioteka = id_biblioteka;
        this.id_iznajmljivanja = id_iznajmljivanja;
        this.isbn = isbn;
        this.naziv = naziv;
        this.godina_izdavanja = godina_izdavanja;
        this.id_pisac = id_pisac;

    }

    public int getId_pisac() {
        return id_pisac;
    }

    public void setId_pisac(int id_pisac) {
        this.id_pisac = id_pisac;
    }

    public int getId_biblioteka() {
        return id_biblioteka;
    }

    public void setId_biblioteka(int id_biblioteka) {
        this.id_biblioteka = id_biblioteka;
    }

    public int getId_iznajmljivanja() {
        return id_iznajmljivanja;
    }

    public void setId_iznajmljivanja(int id_iznajmljivanja) {
        this.id_iznajmljivanja = id_iznajmljivanja;
    }

    public int getId_knjiga() {
        return id_knjiga;
    }

    public void setId_knjiga(int id_knjiga) {
        this.id_knjiga = id_knjiga;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getGodina_izdavanja() {
        return godina_izdavanja;
    }

    public void setGodina_izdavanja(int godina_izdavanja) {
        this.godina_izdavanja = godina_izdavanja;
    }

    @Override
    public String toString() {
        return "Knjiga{" + "id_knjiga=" + id_knjiga + ", isbn=" + isbn + ", naziv=" + naziv + ", godina_izdavanja=" + godina_izdavanja + '}';
    }

}
