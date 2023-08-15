/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import builder.IznajmljivanjeBuilder;
import entiteti.Adresa;
import entiteti.Biblioteka;
import entiteti.Iznajmljivanje;
import entiteti.Knjiga;
import entiteti.Korisnik;
import entiteti.Opstina;
import entiteti.Pisac;
import entiteti.Region;
import entiteti.Zaposleni;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author PC
 */
public class DBKontroler {

    public DBKontroler() {

    }

    public ArrayList<Korisnik> listaKorisnika() throws ClassNotFoundException, SQLException {
        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();
        String upit = "SELECT * FROM `korisnik` ";
        PreparedStatement ps = kon.prepareStatement(upit);

        ResultSet rs = ps.executeQuery();
        ArrayList<Korisnik> lista = new ArrayList<>();
        while (rs.next()) {

            lista.add(new Korisnik(rs.getString(2), rs.getString(3)));
        }

        kon.close();
        return lista;
    }

    public void dodajKorisnika(Korisnik k) throws ClassNotFoundException, SQLException {
        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();

        String upit = "INSERT INTO korisnik (KORISNICKO_IME, SIFRA, ID_BIBLIOTEKA) VALUES (?, ?, ?)";
        PreparedStatement ps = kon.prepareStatement(upit);

        ps.setString(1, k.getKorisnicko_ime());
        ps.setString(2, k.getSifra());
        ps.setInt(3, k.getId_biblioteka());

        ps.executeUpdate();
        kon.close();

    }

    public void obrisiKorisnika(String username) throws ClassNotFoundException, SQLException {
        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();

        String upit = "Delete FROM korisnik where KORISNICKO_IME = ? ";
        PreparedStatement ps = kon.prepareStatement(upit);
        ArrayList<Korisnik> lista = listaKorisnika();

        ps.setString(1, username);
        ps.executeUpdate();
        kon.close();

    }

    public void izmeniKorisnika(Korisnik k, String korisnicko_ime) throws ClassNotFoundException, SQLException {

        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();

        String upit = "UPDATE korisnik SET KORISNICKO_IME = ?, SIFRA = ?, ID_BIBLIOTEKA = ? WHERE KORISNICKO_IME = '" + korisnicko_ime + "';";
        PreparedStatement ps = kon.prepareStatement(upit);

        ps.setString(1, k.getKorisnicko_ime());
        ps.setString(2, k.getSifra());
        ps.setInt(3, k.getId_biblioteka());

        ps.executeUpdate();
        kon.close();

    }

    public ArrayList<Korisnik> prijavljeniKorisnik(String korisnicko_ime, String sifra) throws ClassNotFoundException, SQLException {
        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();
        String upit = "SELECT * FROM `korisnik` WHERE KORISNICKO_IME = '" + korisnicko_ime + "' AND SIFRA = '" + sifra + "'";
        PreparedStatement ps = kon.prepareStatement(upit);

        ResultSet rs = ps.executeQuery();
        ArrayList<Korisnik> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(new Korisnik(rs.getString(2), rs.getString(3), rs.getInt(4)));
        }

        kon.close();
        return lista;
    }

    public boolean usernameProvera(String korisnicko_ime) throws ClassNotFoundException, SQLException {
        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();
        String upit = "SELECT KORISNICKO_IME,SIFRA FROM `korisnik` WHERE KORISNICKO_IME = '" + korisnicko_ime + "'";
        PreparedStatement ps = kon.prepareStatement(upit);

        ResultSet rs = ps.executeQuery();
        ArrayList<Korisnik> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(new Korisnik(rs.getString(1), rs.getString(2))); //ovo moze pravti problem
        }
        boolean provera = false;
        for (int i = 0; i < lista.size(); i++) {
            if (korisnicko_ime.equals(lista.get(i).getKorisnicko_ime().replaceAll("\\s", ""))) {
                provera = true;
            }
        }

        kon.close();
        return provera;
    }

    public void KorisnikTabel(TableView tv) throws ClassNotFoundException, SQLException {

        ArrayList<Korisnik> p = listaKorisnika();

        TableColumn<Korisnik, String> column1 = new TableColumn("Korisnicko Ime");
        column1.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getKorisnicko_ime() + ""));

        TableColumn<Korisnik, String> column2 = new TableColumn("Sifra");
        column2.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getSifra() + ""));

        TableColumn<Korisnik, String> column3 = new TableColumn("Id Biblioteke");
        column3.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getId_biblioteka() + ""));

        tv.getColumns().add(column1);
        tv.getColumns().add(column2);
        tv.getColumns().add(column3);

        tv.getItems().addAll(p);
    }

    public ArrayList<Biblioteka> listaBiblioteka() throws ClassNotFoundException, SQLException {
        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();
        String upit = "SELECT * FROM `biblioteka` ";
        PreparedStatement ps = kon.prepareStatement(upit);

        ResultSet rs = ps.executeQuery();
        ArrayList<Biblioteka> lista = new ArrayList<>();
        while (rs.next()) {

            lista.add(new Biblioteka(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8)));
        }

        kon.close();
        return lista;
    }

    public void dodajBiblioteku(Biblioteka b) throws ClassNotFoundException, SQLException {
        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();

        String upit = "INSERT INTO biblioteka (IME, ID_ADRESA, BROJ_ZGRADE, ID_OPSTINA,ID_REGION,KAPACITET,BROJ_IZDANJA) VALUES (?, ?, ?, ?,?,?,?)";
        PreparedStatement ps = kon.prepareStatement(upit);

        ps.setString(1, b.getIme());
        ps.setInt(2, b.getId_adresa());
        ps.setInt(3, b.getBroj_zgrade());
        ps.setInt(4, b.getId_opstina());
        ps.setInt(5, b.getId_region());
        ps.setInt(6, b.getKapacitet());
        ps.setInt(7, b.getBroj_izdanja());

        ps.executeUpdate();
        kon.close();

    }

    public void izmeniBiblioteku(Biblioteka b, String ime) throws ClassNotFoundException, SQLException {

        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();

        String upit = "UPDATE biblioteka SET  IME = ?, ID_ADRESA = ?, BROJ_ZGRADE = ?, ID_OPSTINA = ?, ID_REGION = ?, KAPACITET = ?, BROJ_IZDANJA = ? WHERE IME = '" + ime + "';";
        PreparedStatement ps = kon.prepareStatement(upit);

        ps.setString(1, b.getIme());
        ps.setInt(2, b.getId_adresa());
        ps.setInt(3, b.getBroj_zgrade());
        ps.setInt(4, b.getId_opstina());
        ps.setInt(5, b.getId_region());
        ps.setInt(6, b.getKapacitet());
        ps.setInt(7, b.getBroj_izdanja());

        ps.executeUpdate();
        kon.close();

    }

    public void obrisiBiblioteku(String naziv) throws ClassNotFoundException, SQLException {
        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();

        String upit = "Delete FROM biblioteka where IME = ? ";
        PreparedStatement ps = kon.prepareStatement(upit);
        ArrayList<Biblioteka> lista = listaBiblioteka();

        ps.setString(1, naziv);
        ps.executeUpdate();
        kon.close();

    }

    public ArrayList<Biblioteka> filtriraj(String ime, int broj_izdanja, int kapacitet) throws ClassNotFoundException, SQLException {
        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();
        String upit = "SELECT * FROM `biblioteka` WHERE IME LIKE '" + ime + "%' AND BROJ_IZDANJA >= '" + broj_izdanja + "%' and KAPACITET >= '" + kapacitet + "'";
        PreparedStatement ps = kon.prepareStatement(upit);

        ResultSet rs = ps.executeQuery();
        ArrayList<Biblioteka> lista = new ArrayList<>();
        while (rs.next()) {

            lista.add(new Biblioteka(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8)));
        }

        kon.close();
        return lista;
    }

    public void BibliotekaTabel(TableView tv) throws ClassNotFoundException, SQLException {

        ArrayList<Biblioteka> p = listaBiblioteka();

        TableColumn<Biblioteka, String> column1 = new TableColumn("Ime");
        column1.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getIme() + ""));

        TableColumn<Biblioteka, String> column2 = new TableColumn("Id adresa");
        column2.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getId_adresa() + ""));

        TableColumn<Biblioteka, String> column3 = new TableColumn("Broj zgrade");
        column3.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getBroj_zgrade() + ""));

        TableColumn<Biblioteka, String> column4 = new TableColumn("Id opstine");
        column4.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getId_opstina() + ""));

        TableColumn<Biblioteka, String> column5 = new TableColumn("Id regiona");
        column5.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getId_region() + ""));

        TableColumn<Biblioteka, String> column6 = new TableColumn("Kapacitet");
        column6.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getKapacitet() + ""));

        TableColumn<Biblioteka, String> column7 = new TableColumn("Broj izdanja");
        column6.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getBroj_izdanja() + ""));

        tv.getColumns().add(column1);
        tv.getColumns().add(column2);
        tv.getColumns().add(column3);
        tv.getColumns().add(column4);
        tv.getColumns().add(column5);
        tv.getColumns().add(column6);
        tv.getColumns().add(column7);

        tv.getItems().addAll(p);
    }

    public void dodajIznajmljivanje(Iznajmljivanje i) throws ClassNotFoundException, SQLException {
        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();

        String upit = "INSERT INTO iznajmljivanje (ID_KORISNIK, ROK_VRACANJA, ID_KNJIGA, BROJ_IZN_KNJIGA,ID_BIBLIOTEKA) VALUES (?, ?, ?, ?,?)";
        PreparedStatement ps = kon.prepareStatement(upit);

        ps.setInt(1, i.getKorisnik().getId_korisnik());
        ps.setString(2, i.getRok_vracanja());
        ps.setInt(3, i.getId_knjiga());
        ps.setInt(4, i.getBroj_izn_knjiga());
        ps.setInt(5, i.getId_biblioteka());

        ps.executeUpdate();
        kon.close();

    }

    public void izmeniIznajmljivanje(Iznajmljivanje i, String rok) throws ClassNotFoundException, SQLException {

        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();

        String upit = "UPDATE iznajmljivanje SET  ROK_VRACANJA = ?, ID_KNJIGA = ?, BROJ_IZN_KNJIGA = ?, ID_BIBLIOTEKA = ? WHERE ROK_VRACANJA = '" + rok + "';";
        PreparedStatement ps = kon.prepareStatement(upit);

        ps.setString(1, i.getRok_vracanja());
        ps.setInt(2, i.getId_knjiga());
        ps.setInt(3, i.getBroj_izn_knjiga());
        ps.setInt(4, i.getId_biblioteka());

        ps.executeUpdate();
        kon.close();

    }

    public void obrisiIznajmljivanje(String rok) throws ClassNotFoundException, SQLException {
        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();

        String upit = "Delete FROM iznajmljivanje where ROK_VRACANJA = ? ";
        PreparedStatement ps = kon.prepareStatement(upit);
        ArrayList<Iznajmljivanje> lista = listaIznajmljivanje();

        ps.setString(1, rok);
        ps.executeUpdate();
        kon.close();

    }

    public ArrayList<Iznajmljivanje> listaIznajmljivanje(Korisnik kor) throws ClassNotFoundException, SQLException {
        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();
        String upit = "SELECT * FROM iznajmljivanje INNER JOIN korisnik ON iznajmljivanje.ID_KORISNIK = korisnik.ID_KORISNIK INNER JOIN biblioteka ON iznajmljivanje.ID_BIBLIOTEKA = biblioteka.ID_BIBLIOTEKA WHERE korisnik.ID_KORISNIK = '" + kor.getId_korisnik() + "' ORDER BY iznajmljivanje.ID_IZNAJMLJIVANJA ASC";
        PreparedStatement ps = kon.prepareStatement(upit);

        ResultSet rs = ps.executeQuery();
        ArrayList<Iznajmljivanje> lista = new ArrayList<>();
        while (rs.next()) {
            Biblioteka o = new Biblioteka(rs.getInt(10), rs.getString(11), rs.getInt(12), rs.getInt(13), rs.getInt(14), rs.getInt(15), rs.getInt(16), rs.getInt(17));
            Korisnik k = new Korisnik(rs.getInt(7), rs.getString(8), rs.getString(9));

            lista.add(new IznajmljivanjeBuilder()
                    .Korisnik(k)
                    .vracanje(rs.getString(3))
                    .idKnjiga(rs.getInt(4))
                    .brojIznKnjiga(rs.getInt(5))
                    .idBiblioteka(rs.getInt(6))
                    .build());
        }

        kon.close();
        return lista;
    }

    public void IznajmljivanjeTabel(TableView tv, Korisnik kor) throws ClassNotFoundException, SQLException {

        //TableView tv = new TableView();
        //uzimanje vrednosti iz baze 
        ArrayList<Iznajmljivanje> p = listaIznajmljivanje(kor);
        //KOLONE

        TableColumn<Iznajmljivanje, String> column1 = new TableColumn("id");
        column1.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getId_iznajmljivanja() + ""));

        TableColumn<Iznajmljivanje, String> column2 = new TableColumn("Korisnik");
        column2.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getId_korisnik() + ""));

        TableColumn<Iznajmljivanje, String> column3 = new TableColumn("Vracanje");
        column3.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getRok_vracanja() + ""));

        TableColumn<Iznajmljivanje, String> column4 = new TableColumn("Knjiga");
        column4.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getId_knjiga() + ""));

        TableColumn<Iznajmljivanje, String> column5 = new TableColumn("Broj izdatih knjiga");
        column5.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getBroj_izn_knjiga() + ""));

        TableColumn<Iznajmljivanje, String> column6 = new TableColumn("Biblioteka");
        column5.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getId_biblioteka() + ""));

        tv.getColumns().add(column1);
        tv.getColumns().add(column2);
        tv.getColumns().add(column3);
        tv.getColumns().add(column4);
        tv.getColumns().add(column5);
        tv.getColumns().add(column6);

        tv.getItems().addAll(p);
    }

    public ArrayList<Iznajmljivanje> listaIznajmljivanje() throws ClassNotFoundException, SQLException {
        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();
        String upit = "SELECT * FROM iznajmljivanje ";
        PreparedStatement ps = kon.prepareStatement(upit);

        ResultSet rs = ps.executeQuery();
        ArrayList<Iznajmljivanje> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(new Iznajmljivanje(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6)));
        }

        kon.close();
        return lista;
    }

    public void IznajmljivanjeTabel(TableView tv) throws ClassNotFoundException, SQLException {

        //TableView tv = new TableView();
        //uzimanje vrednosti iz baze 
        ArrayList<Iznajmljivanje> p = listaIznajmljivanje();
        //KOLONE

        TableColumn<Iznajmljivanje, String> column1 = new TableColumn("id");
        column1.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getId_iznajmljivanja() + ""));

        TableColumn<Iznajmljivanje, String> column2 = new TableColumn("Korisnik");
        column2.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getId_korisnik() + ""));

        TableColumn<Iznajmljivanje, String> column3 = new TableColumn("Vracanje");
        column3.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getRok_vracanja() + ""));

        TableColumn<Iznajmljivanje, String> column4 = new TableColumn("Knjiga");
        column4.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getId_knjiga() + ""));

        TableColumn<Iznajmljivanje, String> column5 = new TableColumn("Broj izdatih knjiga");
        column5.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getBroj_izn_knjiga() + ""));

        TableColumn<Iznajmljivanje, String> column6 = new TableColumn("Biblioteka");
        column5.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getId_biblioteka() + ""));

        tv.getColumns().add(column1);
        tv.getColumns().add(column2);
        tv.getColumns().add(column3);
        tv.getColumns().add(column4);
        tv.getColumns().add(column5);
        tv.getColumns().add(column6);

        tv.getItems().addAll(p);
    }

    public void dodajAdresu(Adresa a) throws ClassNotFoundException, SQLException {
        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();

        String upit = "INSERT INTO adresa (ID_OPSTINA, BROJ_ZGRADE, IME_ULICE) VALUES (?, ?, ?)";
        PreparedStatement ps = kon.prepareStatement(upit);

        ps.setInt(1, a.getId_opstina());
        ps.setInt(2, a.getBroj_zgrade());
        ps.setString(3, a.getIme_ulice());

        ps.executeUpdate();
        kon.close();

    }

    public void izmeniAdresu(Adresa a, String ime) throws ClassNotFoundException, SQLException {

        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();

        String upit = "UPDATE adresa SET  ID_OPSTINA = ?, BROJ_ZGRADE = ?, IME_ULICE = ? WHERE IME_ULICE = '" + ime + "';";
        PreparedStatement ps = kon.prepareStatement(upit);

        ps.setInt(1, a.getId_opstina());
        ps.setInt(2, a.getBroj_zgrade());
        ps.setString(3, a.getIme_ulice());

        ps.executeUpdate();
        kon.close();

    }

    public void obrisiAdresu(String imeUlice) throws ClassNotFoundException, SQLException {
        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();

        String upit = "Delete FROM adresa where IME_ULICE= ? ";
        PreparedStatement ps = kon.prepareStatement(upit);
        //ArrayList<Iznajmljivanje> lista = listaIznajmljivanje(kor);

        ps.setString(1, imeUlice);
        ps.executeUpdate();
        kon.close();

    }

    public ArrayList<Adresa> listaAdresa() throws ClassNotFoundException, SQLException {
        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();
        String upit = "SELECT * FROM `adresa` ";
        PreparedStatement ps = kon.prepareStatement(upit);

        ResultSet rs = ps.executeQuery();
        ArrayList<Adresa> lista = new ArrayList<>();
        while (rs.next()) {

            lista.add(new Adresa(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4)));
        }

        kon.close();
        return lista;
    }

    public void AdresaTabel(TableView tv) throws ClassNotFoundException, SQLException {

        ArrayList<Adresa> p = listaAdresa();

        TableColumn<Adresa, String> column1 = new TableColumn("Id opstina");
        column1.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getId_opstina() + ""));

        TableColumn<Adresa, String> column2 = new TableColumn("Broj zgrade");
        column2.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getBroj_zgrade() + ""));

        TableColumn<Adresa, String> column3 = new TableColumn("Ime ulice");
        column3.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getIme_ulice() + ""));

        tv.getColumns().add(column1);
        tv.getColumns().add(column2);
        tv.getColumns().add(column3);

        tv.getItems().addAll(p);
    }

    public void dodajPisca(Pisac p) throws ClassNotFoundException, SQLException {
        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();

        String upit = "INSERT INTO pisac (OPIS, IME, BROJ_PISACA) VALUES (?, ?, ?)";
        PreparedStatement ps = kon.prepareStatement(upit);

        ps.setString(1, p.getOpis());
        ps.setString(2, p.getIme());
        ps.setInt(3, p.getBrojPisaca());

        ps.executeUpdate();
        kon.close();

    }

    public void izmeniPisca(Pisac p, String ime) throws ClassNotFoundException, SQLException {

        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();

        String upit = "UPDATE pisac SET  OPIS = ?, IME = ?, BROJ_PISACA = ? WHERE IME = '" + ime + "';";
        PreparedStatement ps = kon.prepareStatement(upit);

        ps.setString(1, p.getOpis());
        ps.setString(2, p.getIme());
        ps.setInt(3, p.getBrojPisaca());

        ps.executeUpdate();
        kon.close();

    }

    public void obrisiPisca(String ime) throws ClassNotFoundException, SQLException {
        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();

        String upit = "Delete FROM pisac where IME= ? ";
        PreparedStatement ps = kon.prepareStatement(upit);
        //ArrayList<Iznajmljivanje> lista = listaIznajmljivanje(kor);

        ps.setString(1, ime);
        ps.executeUpdate();
        kon.close();

    }

    public ArrayList<Pisac> listaPisaca() throws ClassNotFoundException, SQLException {
        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();
        String upit = "SELECT * FROM `pisac` ";
        PreparedStatement ps = kon.prepareStatement(upit);

        ResultSet rs = ps.executeQuery();
        ArrayList<Pisac> lista = new ArrayList<>();
        while (rs.next()) {

            lista.add(new Pisac(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
        }

        kon.close();
        return lista;
    }

    public void PisacTabel(TableView tv) throws ClassNotFoundException, SQLException {

        ArrayList<Pisac> p = listaPisaca();

        TableColumn<Pisac, String> column1 = new TableColumn("Opis");
        column1.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getOpis() + ""));

        TableColumn<Pisac, String> column2 = new TableColumn("Ime");
        column2.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getIme() + ""));

        TableColumn<Pisac, String> column3 = new TableColumn("Broj pisaca");
        column3.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getBrojPisaca() + ""));

        tv.getColumns().add(column1);
        tv.getColumns().add(column2);
        tv.getColumns().add(column3);

        tv.getItems().addAll(p);
    }

    public void dodajKnjigu(Knjiga p) throws ClassNotFoundException, SQLException {
        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();

        String upit = "INSERT INTO knjiga (ID_BIBLIOTEKA, ID_IZNAJMLJIVANJA, ISBN, IME, GODINA_IZDAVANJA, ID_PISAC ) VALUES (?, ?, ?, ?, ? ,?)";
        PreparedStatement ps = kon.prepareStatement(upit);

        ps.setInt(1, p.getId_biblioteka());
        ps.setInt(2, p.getId_iznajmljivanja());
        ps.setString(3, p.getIsbn());
        ps.setString(4, p.getNaziv());
        ps.setInt(5, p.getGodina_izdavanja());
        ps.setInt(6, p.getId_pisac());

        ps.executeUpdate();
        kon.close();

    }

    public void izmeniKnjigu(Knjiga p, String ime) throws ClassNotFoundException, SQLException {

        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();

        String upit = "UPDATE knjiga SET  ID_BIBLIOTEKA = ?, ID_IZNAJMLJIVANJA = ?, ISBN = ?,IME = ?, GODINA_IZDAVANJA = ?, ID_PISAC = ? WHERE IME = '" + ime + "';";
        PreparedStatement ps = kon.prepareStatement(upit);

        ps.setInt(1, p.getId_biblioteka());
        ps.setInt(2, p.getId_iznajmljivanja());
        ps.setString(3, p.getIsbn());
        ps.setString(4, p.getNaziv());
        ps.setInt(5, p.getGodina_izdavanja());
        ps.setInt(6, p.getId_pisac());

        ps.executeUpdate();
        kon.close();

    }

    public void obrisiKnjigu(String ime) throws ClassNotFoundException, SQLException {
        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();

        String upit = "Delete FROM knjiga where IME= ? ";
        PreparedStatement ps = kon.prepareStatement(upit);
        //ArrayList<Iznajmljivanje> lista = listaIznajmljivanje(kor);

        ps.setString(1, ime);
        ps.executeUpdate();
        kon.close();

    }

    public ArrayList<Knjiga> listaKnjiga() throws ClassNotFoundException, SQLException {
        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();
        String upit = "SELECT * FROM `knjiga` ";
        PreparedStatement ps = kon.prepareStatement(upit);

        ResultSet rs = ps.executeQuery();
        ArrayList<Knjiga> lista = new ArrayList<>();
        while (rs.next()) {

            lista.add(new Knjiga(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7)));
        }

        kon.close();
        return lista;
    }

    public void KnjigaTabel(TableView tv) throws ClassNotFoundException, SQLException {

        //TableView tv = new TableView();
        //uzimanje vrednosti iz baze 
        ArrayList<Knjiga> p = listaKnjiga();
        //KOLONE

        TableColumn<Knjiga, String> column1 = new TableColumn("id biblioteka");
        column1.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getId_biblioteka() + ""));

        TableColumn<Knjiga, String> column2 = new TableColumn("Id iznajmljivanja");
        column2.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getId_iznajmljivanja() + ""));

        TableColumn<Knjiga, String> column3 = new TableColumn("ISBN");
        column3.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getIsbn() + ""));

        TableColumn<Knjiga, String> column4 = new TableColumn("Ime");
        column4.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNaziv() + ""));

        TableColumn<Knjiga, String> column5 = new TableColumn("Godina");
        column5.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getGodina_izdavanja() + ""));

        TableColumn<Knjiga, String> column6 = new TableColumn("id pisac");
        column5.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getId_pisac() + ""));

        tv.getColumns().add(column1);
        tv.getColumns().add(column2);
        tv.getColumns().add(column3);
        tv.getColumns().add(column4);
        tv.getColumns().add(column5);
        tv.getColumns().add(column6);

        tv.getItems().addAll(p);
    }

    public void dodajOpstinu(Opstina p) throws ClassNotFoundException, SQLException {
        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();

        String upit = "INSERT INTO opstina (ID_REGION, NAZIV) VALUES (?, ?)";
        PreparedStatement ps = kon.prepareStatement(upit);

        ps.setInt(1, p.getId_region());
        ps.setString(2, p.getNazivOpstina());

        ps.executeUpdate();
        kon.close();

    }

    public void izmeniOpstinu(Opstina p, String ime) throws ClassNotFoundException, SQLException {

        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();

        String upit = "UPDATE opstina SET  ID_REGION = ?, NAZIV = ? WHERE NAZIV = '" + ime + "';";
        PreparedStatement ps = kon.prepareStatement(upit);

        ps.setInt(1, p.getId_region());
        ps.setString(2, p.getNazivOpstina());

        ps.executeUpdate();
        kon.close();

    }

    public void obrisiOpstinu(String ime) throws ClassNotFoundException, SQLException {
        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();

        String upit = "Delete FROM opstina where NAZIV= ? ";
        PreparedStatement ps = kon.prepareStatement(upit);
        //ArrayList<Iznajmljivanje> lista = listaIznajmljivanje(kor);

        ps.setString(1, ime);
        ps.executeUpdate();
        kon.close();

    }

    public ArrayList<Opstina> listaOpstina() throws ClassNotFoundException, SQLException {
        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();
        String upit = "SELECT * FROM `opstina` ";
        PreparedStatement ps = kon.prepareStatement(upit);

        ResultSet rs = ps.executeQuery();
        ArrayList<Opstina> lista = new ArrayList<>();
        while (rs.next()) {

            lista.add(new Opstina(rs.getInt(1), rs.getInt(2), rs.getString(3)));
        }

        kon.close();
        return lista;
    }

    public void OpstinaTabel(TableView tv) throws ClassNotFoundException, SQLException {

        //TableView tv = new TableView();
        //uzimanje vrednosti iz baze 
        ArrayList<Opstina> p = listaOpstina();
        //KOLONE

        TableColumn<Opstina, String> column1 = new TableColumn("id opstina");
        column1.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getId_opstina() + ""));

        TableColumn<Opstina, String> column2 = new TableColumn("Id region");
        column2.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getId_region() + ""));

        TableColumn<Opstina, String> column3 = new TableColumn("Naziv");
        column3.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNazivOpstina() + ""));

        tv.getColumns().add(column1);
        tv.getColumns().add(column2);
        tv.getColumns().add(column3);

        tv.getItems().addAll(p);
    }

    public void dodajRegion(Region p) throws ClassNotFoundException, SQLException {
        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();

        String upit = "INSERT INTO region (NAZIV) VALUES (?)";
        PreparedStatement ps = kon.prepareStatement(upit);

        ps.setString(1, p.getNaziv());

        ps.executeUpdate();
        kon.close();

    }

    public void izmeniRegion(Region p, String ime) throws ClassNotFoundException, SQLException {

        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();

        String upit = "UPDATE region SET  NAZIV = ? WHERE NAZIV = '" + ime + "';";
        PreparedStatement ps = kon.prepareStatement(upit);

        ps.setString(1, p.getNaziv());

        ps.executeUpdate();
        kon.close();

    }

    public void obrisiRegion(String ime) throws ClassNotFoundException, SQLException {
        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();

        String upit = "Delete FROM region where NAZIV= ? ";
        PreparedStatement ps = kon.prepareStatement(upit);
        //ArrayList<Iznajmljivanje> lista = listaIznajmljivanje(kor);

        ps.setString(1, ime);
        ps.executeUpdate();
        kon.close();

    }

    public ArrayList<Region> listaRegiona() throws ClassNotFoundException, SQLException {
        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();
        String upit = "SELECT * FROM `region` ";
        PreparedStatement ps = kon.prepareStatement(upit);

        ResultSet rs = ps.executeQuery();
        ArrayList<Region> lista = new ArrayList<>();
        while (rs.next()) {

            lista.add(new Region(rs.getInt(1), rs.getString(2)));
        }

        kon.close();
        return lista;
    }

    public void RegionTabel(TableView tv) throws ClassNotFoundException, SQLException {

        //TableView tv = new TableView();
        //uzimanje vrednosti iz baze 
        ArrayList<Region> p = listaRegiona();
        //KOLONE

        TableColumn<Region, String> column1 = new TableColumn("id region");
        column1.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getId_region() + ""));

        TableColumn<Region, String> column2 = new TableColumn("naziv");
        column2.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNaziv() + ""));

        tv.getColumns().add(column1);
        tv.getColumns().add(column2);

        tv.getItems().addAll(p);
    }

    public void dodajZaposlenog(Zaposleni p) throws ClassNotFoundException, SQLException {
        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();

        String upit = "INSERT INTO zaposleni (ID_BIBLIOTEKA, POZICIJA,IME) VALUES (?,?,?)";
        PreparedStatement ps = kon.prepareStatement(upit);

        ps.setInt(1, p.getId_biblioteka());
        ps.setString(2, p.getPozicija());
        ps.setString(3, p.getIme());
        ps.executeUpdate();
        kon.close();

    }

    public void izmeniZaposlenog(Zaposleni p, String ime) throws ClassNotFoundException, SQLException {

        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();

        String upit = "UPDATE zaposleni SET  ID_BIBLIOTEKA = ?, POZICIJA = ?, IME = ? WHERE IME= '" + ime + "';";
        PreparedStatement ps = kon.prepareStatement(upit);

        ps.setInt(1, p.getId_biblioteka());
        ps.setString(2, p.getPozicija());
        ps.setString(3, p.getIme());

        ps.executeUpdate();
        kon.close();

    }

    public void obrisiZaposlenog(String ime) throws ClassNotFoundException, SQLException {
        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();

        String upit = "Delete FROM zaposleni where IME= ? ";
        PreparedStatement ps = kon.prepareStatement(upit);
        //ArrayList<Iznajmljivanje> lista = listaIznajmljivanje(kor);

        ps.setString(1, ime);
        ps.executeUpdate();
        kon.close();

    }

    public ArrayList<Zaposleni> listaZaposlenih() throws ClassNotFoundException, SQLException {
        DBKonekcija konekcija = new DBKonekcija();
        Connection kon = konekcija.zapocniKonekciju();
        String upit = "SELECT * FROM `zaposleni` ";
        PreparedStatement ps = kon.prepareStatement(upit);

        ResultSet rs = ps.executeQuery();
        ArrayList<Zaposleni> lista = new ArrayList<>();
        while (rs.next()) {

            lista.add(new Zaposleni(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
        }

        kon.close();
        return lista;
    }

    public void ZaposleniTabel(TableView tv) throws ClassNotFoundException, SQLException {

        //TableView tv = new TableView();
        //uzimanje vrednosti iz baze 
        ArrayList<Zaposleni> p = listaZaposlenih();
        //KOLONE

        TableColumn<Zaposleni, String> column1 = new TableColumn("id zaposleni");
        column1.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getId_zaposleni() + ""));

        TableColumn<Zaposleni, String> column2 = new TableColumn("id biblioteka");
        column2.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getId_biblioteka() + ""));

        TableColumn<Zaposleni, String> column3 = new TableColumn("pozicija");
        column3.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getPozicija() + ""));

        TableColumn<Zaposleni, String> column4 = new TableColumn("ime");
        column4.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getIme() + ""));

        tv.getColumns().add(column1);
        tv.getColumns().add(column2);
        tv.getColumns().add(column3);
        tv.getColumns().add(column4);

        tv.getItems().addAll(p);
    }
}
