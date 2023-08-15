/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guimeni;

import entiteti.Korisnik;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author PC
 */
public class GUIMeni {

    public GUIMeni(Korisnik prijvaljeniKor) throws ClassNotFoundException, SQLException {

        Stage primaryStage = new Stage();

        Button listaKor = new Button("Lista korisnika");
        Button listaBiblioteka = new Button("Lista biblioteka");
        Button listaAdresa = new Button("Lista adresa");
        Button listaKnjiga = new Button("Lista knjiga");
        Button listaOpstina = new Button("Lista opstina");
        Button listaPisaca = new Button("Lista pisaca");
        Button listaRegiona = new Button("Lista regiona");
        Button listaZaposlenih = new Button("Lista zaposlenih");
        Button listaIznajmljivanja = new Button("Lista iznajmljivanja");
        Button odjava = new Button("Odjava, " + prijvaljeniKor.getKorisnicko_ime());
        VBox scena = new VBox(120);

        VBox main = new VBox(10);
        main.setAlignment(Pos.CENTER);

        HBox hbox1 = new HBox(15);

        hbox1.getChildren().addAll(listaBiblioteka, listaKor, listaAdresa, listaKnjiga, listaOpstina, listaPisaca, listaRegiona, listaZaposlenih, listaIznajmljivanja);

        HBox hbox2 = new HBox(15);

        HBox header = new HBox();
        header.setAlignment(Pos.TOP_RIGHT);
        header.getChildren().addAll(odjava);

        VBox sideMenu = new VBox();

        odjava.setOnAction(e -> {
            primaryStage.close();
        });

        listaKor.setOnAction(e -> {
            try {
                PrikazKorisnika prikazKor = new PrikazKorisnika(prijvaljeniKor);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(GUIMeni.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        listaBiblioteka.setOnAction(e -> {
            try {
                PrikazBiblioteka prikazBiblioteka = new PrikazBiblioteka(prijvaljeniKor);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(GUIMeni.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        listaAdresa.setOnAction(e -> {
            try {
                PrikazAdrese prikazAdresa = new PrikazAdrese(prijvaljeniKor);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(GUIMeni.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        listaKnjiga.setOnAction(e -> {
            try {
                PrikazKnjige prikazKnjiga = new PrikazKnjige(prijvaljeniKor);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(GUIMeni.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        listaOpstina.setOnAction(e -> {
            try {
                PrikazOpstine prikazOpstina = new PrikazOpstine(prijvaljeniKor);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(GUIMeni.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        listaPisaca.setOnAction(e -> {
            try {
                PrikazPisca prikazPisca = new PrikazPisca(prijvaljeniKor);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(GUIMeni.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        listaRegiona.setOnAction(e -> {
            try {
                PrikazRegiona prikazRegion = new PrikazRegiona(prijvaljeniKor);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(GUIMeni.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        listaZaposlenih.setOnAction(e -> {
            try {
                PrikazZaposlenog prikazZaposleni = new PrikazZaposlenog(prijvaljeniKor);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(GUIMeni.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        listaIznajmljivanja.setOnAction(e -> {
            try {
                PrikazIznajmljivanja prikazIznajmljivanje = new PrikazIznajmljivanja(prijvaljeniKor);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(GUIMeni.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        sideMenu.setId("sideMenu");

        main.getChildren().addAll(hbox1, hbox2);

        hbox1.setAlignment(Pos.CENTER);
        hbox2.setAlignment(Pos.CENTER);

        scena.getChildren().addAll(header, main);

        Scene scene = new Scene(scena, 1000, 900);

        primaryStage.setTitle("Program!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
