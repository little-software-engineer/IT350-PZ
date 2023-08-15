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
public class Adresa {

    private int id_adresa;
    private int id_opstina;
    private int broj_zgrade;
    private String ime_ulice;

    public Adresa() {
    }

    public Adresa(int id_adresa, int id_opstina, int broj_zgrade, String ime_ulice) {
        this.id_adresa = id_adresa;
        this.id_opstina = id_opstina;
        this.broj_zgrade = broj_zgrade;
        this.ime_ulice = ime_ulice;
    }

    public Adresa(int id_opstina, int broj_zgrade, String ime_ulice) {
        this.id_opstina = id_opstina;
        this.broj_zgrade = broj_zgrade;
        this.ime_ulice = ime_ulice;
    }

    public int getId_opstina() {
        return id_opstina;
    }

    public void setId_opstina(int id_opstina) {
        this.id_opstina = id_opstina;
    }

    public int getId_adresa() {
        return id_adresa;
    }

    public void setId_adresa(int id_adresa) {
        this.id_adresa = id_adresa;
    }

    public int getBroj_zgrade() {
        return broj_zgrade;
    }

    public void setBroj_zgrade(int broj_zgrade) {
        this.broj_zgrade = broj_zgrade;
    }

    public String getIme_ulice() {
        return ime_ulice;
    }

    public void setIme_ulice(String ime_ulice) {
        this.ime_ulice = ime_ulice;
    }

    @Override
    public String toString() {
        return "Adresa{" + "id_adresa=" + id_adresa + ", broj_zgrade=" + broj_zgrade + ", ime_ulice=" + ime_ulice + '}';
    }

}
