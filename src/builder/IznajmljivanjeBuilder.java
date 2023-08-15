/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import entiteti.Iznajmljivanje;
import entiteti.Korisnik;
import java.sql.Date;

/**
 *
 * @author PC
 */
public class IznajmljivanjeBuilder {

    private Iznajmljivanje iznajmljivanje;

    public IznajmljivanjeBuilder() {
        this.iznajmljivanje = new Iznajmljivanje();
    }

    public IznajmljivanjeBuilder iznajmljivanje_id(int iznajmljivanje_id) {
        this.iznajmljivanje.setId_iznajmljivanja(iznajmljivanje_id);
        return this;
    }

    public IznajmljivanjeBuilder vracanje(String vracanje) {
        this.iznajmljivanje.setRok_vracanja(vracanje);
        return this;
    }

    public IznajmljivanjeBuilder brojIznKnjiga(int brojKnjiga) {
        this.iznajmljivanje.setBroj_izn_knjiga(brojKnjiga);
        return this;
    }

    public IznajmljivanjeBuilder idBiblioteka(int biblioteka) {
        this.iznajmljivanje.setId_biblioteka(biblioteka);
        return this;
    }

    public IznajmljivanjeBuilder Korisnik(Korisnik korisnik) {
        this.iznajmljivanje.setKorisnik(korisnik);
        return this;
    }

    public IznajmljivanjeBuilder idKnjiga(int knjigaID) {
        this.iznajmljivanje.setId_knjiga(knjigaID);
        return this;
    }

    public Iznajmljivanje build() {
        return this.iznajmljivanje;
    }
}
