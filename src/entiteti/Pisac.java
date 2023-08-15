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
public class Pisac  {

    private int id_pisac;
    private String opis;
    private String ime;
    private int brojPisaca;

    public Pisac() {
    }

    public Pisac(int id_pisac, String opis, String ime, int brojPisaca) {
        this.id_pisac = id_pisac;
        this.opis = opis;
        this.ime = ime;
        this.brojPisaca = brojPisaca;
    }

    public Pisac(String opis, String ime, int brojPisaca) {
        this.opis = opis;
        this.ime = ime;
        this.brojPisaca = brojPisaca;
    }

    public int getId_pisac() {
        return id_pisac;
    }

    public void setId_pisac(int id_pisac) {
        this.id_pisac = id_pisac;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getBrojPisaca() {
        return brojPisaca;
    }

    public void setBrojPisaca(int brojPisaca) {
        this.brojPisaca = brojPisaca;
    }

    @Override
    public String toString() {
        return "Pisac{" + "id_pisac=" + id_pisac + ", opis=" + opis + ", ime=" + ime + ", brojPisaca=" + brojPisaca + '}';
    }

}
