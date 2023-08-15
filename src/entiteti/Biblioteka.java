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
public class Biblioteka {

    private int id_biblioteka;
    private String ime;
    private int id_adresa;
    private int broj_zgrade;
    private int id_opstina;
    private int id_region;
    private int kapacitet;
    private int broj_izdanja;

    public Biblioteka() {
    }

    public Biblioteka(int id_biblioteka, String ime, int id_adresa, int broj_zgrade, int id_opstina, int id_region, int kapacitet, int broj_izdanja) {
        this.id_biblioteka = id_biblioteka;
        this.ime = ime;
        this.id_adresa = id_adresa;
        this.broj_zgrade = broj_zgrade;
        this.id_opstina = id_opstina;
        this.id_region = id_region;
        this.kapacitet = kapacitet;
        this.broj_izdanja = broj_izdanja;
    }

    public Biblioteka(String ime, int id_adresa, int broj_zgrade, int id_opstina, int id_region, int kapacitet, int broj_izdanja) {
        this.ime = ime;
        this.id_adresa = id_adresa;
        this.broj_zgrade = broj_zgrade;
        this.id_opstina = id_opstina;
        this.id_region = id_region;
        this.kapacitet = kapacitet;
        this.broj_izdanja = broj_izdanja;
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

    public int getId_opstina() {
        return id_opstina;
    }

    public void setId_opstina(int id_opstina) {
        this.id_opstina = id_opstina;
    }

    public int getId_region() {
        return id_region;
    }

    public void setId_region(int id_region) {
        this.id_region = id_region;
    }

    public int getKapacitet() {
        return kapacitet;
    }

    public void setKapacitet(int kapacitet) {
        this.kapacitet = kapacitet;
    }

    public int getBroj_izdanja() {
        return broj_izdanja;
    }

    public void setBroj_izdanja(int broj_izdanja) {
        this.broj_izdanja = broj_izdanja;
    }

    public Biblioteka(int id_biblioteka) {
        this.id_biblioteka = id_biblioteka;
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

    @Override
    public String toString() {
        return "Biblioteka{" + "id_biblioteka=" + id_biblioteka + ", ime=" + ime + ", id_adresa=" + id_adresa + ", broj_zgrade=" + broj_zgrade + ", id_opstina=" + id_opstina + ", id_region=" + id_region + ", kapacitet=" + kapacitet + ", broj_izdanja=" + broj_izdanja + '}';
    }

}
