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
public class Region {

    private int id_region;
    private String naziv;

    public Region() {
    }

    public Region(int id_region, String naziv) {
        this.id_region = id_region;
        this.naziv = naziv;
    }

    public Region(String naziv) {
        this.naziv = naziv;
    }

    public int getId_region() {
        return id_region;
    }

    public void setId_region(int id_region) {
        this.id_region = id_region;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return "Region{" + "id_region=" + id_region + ", naziv=" + naziv + '}';
    }

}
