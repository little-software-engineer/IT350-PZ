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
public class Opstina {

    private int id_opstina;
    private int id_region;
    private String nazivOpstina;

    public Opstina() {
    }

    public Opstina(int id_region, String nazivOpstina) {
        this.id_region = id_region;
        this.nazivOpstina = nazivOpstina;
    }

    public Opstina(int id_opstina, int id_region, String nazivOpstina) {

        this.id_opstina = id_opstina;
        this.id_region = id_region;
        this.nazivOpstina = nazivOpstina;
    }

    public int getId_opstina() {
        return id_opstina;
    }

    public void setId_opstina(int id_opstina) {
        this.id_opstina = id_opstina;
    }

    public String getNazivOpstina() {
        return nazivOpstina;
    }

    public void setNazivOpstina(String nazivOpstina) {
        this.nazivOpstina = nazivOpstina;
    }

    public int getId_region() {
        return id_region;
    }

    public void setId_region(int id_region) {
        this.id_region = id_region;
    }

    @Override
    public String toString() {
        return "Opstina{" + "id_opstina=" + id_opstina + ", nazivOpstina=" + nazivOpstina + '}';
    }

}
