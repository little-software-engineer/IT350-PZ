/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guimeni;

import baza.DBKontroler;
import entiteti.Biblioteka;
import entiteti.Korisnik;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author PC
 */
public class PrikazBiblioteka {

    public PrikazBiblioteka(Korisnik prijvaljeniKor) throws ClassNotFoundException, SQLException {
        Stage primaryStage = new Stage();

        HBox main = new HBox(20);

        VBox left = new VBox(35);

        DBKontroler tblLok = new DBKontroler();

        HBox leftUp = new HBox(5);
        leftUp.setAlignment(Pos.CENTER);

        Label tekst2 = new Label("Naziv: ");
        TextField nazivTF = new TextField();

        Label tekst3 = new Label("Id adresa:");
        TextField adresa = new TextField();

        Label tekst4 = new Label("Broj zgrade:");
        TextField zgrada = new TextField();

        Label tekst5 = new Label("Id opstina:");
        TextField opstina = new TextField();

        Label tekst8 = new Label("Id Region:");
        TextField region = new TextField();

        Label tekst7 = new Label("Kapacitet");
        TextField kapacitet = new TextField();

        Label tekst6 = new Label("Broj izdanja:");
        TextField brojIzdanja = new TextField();

        VBox tekst = new VBox(15);
        tekst.getChildren().addAll(tekst2, tekst3, tekst4, tekst5, tekst8, tekst7, tekst6);
        VBox polja = new VBox(6.5);
        polja.getChildren().addAll(nazivTF, adresa, zgrada, opstina, region, kapacitet, brojIzdanja);

        leftUp.getChildren().addAll(tekst, polja);

        HBox leftMiddle = new HBox(20);
        Button iznajmi = new Button("Iznajmi");
        Button filtriraj = new Button("Filtriraj");
        Button dodaj = new Button("Dodaj");
        Button izmeni = new Button("Izmeni");
        Button obrisi = new Button("Obrisi");

        leftMiddle.getChildren().addAll(filtriraj, iznajmi, dodaj, izmeni, obrisi);
        leftMiddle.setAlignment(Pos.CENTER);

        left.getChildren().addAll(leftUp, leftMiddle);

        TableView tv = new TableView();
        tblLok.BibliotekaTabel(tv);

        obrisi.setDisable(true);
        izmeni.setDisable(true);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        alert.setTitle("Program!");

        iznajmi.setOnAction(e -> {
            try {
                if (!tv.getSelectionModel().isEmpty()) {
                    int id = tblLok.listaBiblioteka().get(tv.getSelectionModel().getSelectedIndex()).getId_biblioteka();
                    String naziv = tblLok.listaBiblioteka().get(tv.getSelectionModel().getSelectedIndex()).getIme();
                    int adresa2 = tblLok.listaBiblioteka().get(tv.getSelectionModel().getSelectedIndex()).getId_adresa();
                    int brZgrade = tblLok.listaBiblioteka().get(tv.getSelectionModel().getSelectedIndex()).getBroj_zgrade();
                    int idOpstina = tblLok.listaBiblioteka().get(tv.getSelectionModel().getSelectedIndex()).getId_opstina();
                    int idRegion = tblLok.listaBiblioteka().get(tv.getSelectionModel().getSelectedIndex()).getId_region();
                    int kapacitetBiblioteke = tblLok.listaBiblioteka().get(tv.getSelectionModel().getSelectedIndex()).getKapacitet();
                    int brIzdanja = tblLok.listaBiblioteka().get(tv.getSelectionModel().getSelectedIndex()).getBroj_izdanja();
                    Biblioteka b = new Biblioteka(id, naziv, adresa2, brZgrade, idOpstina, idRegion, kapacitetBiblioteke, brIzdanja);
                    IznajmljivanjeGUI iznajmljivanje = new IznajmljivanjeGUI(prijvaljeniKor, b);
                    primaryStage.close();
                } else if (tv.getSelectionModel().isEmpty()) {
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Program");
                    alert1.setContentText("Niste izabrali red iz tabele.");
                    alert1.showAndWait();
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(PrikazKorisnika.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        filtriraj.setOnAction(e -> {
            try {
                alert.setContentText("Da li zelite da filtrirate tekuce zapise?");
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    tv.getItems().clear();
                    tv.getItems().addAll(tblLok.filtriraj(nazivTF.getText(), Integer.parseInt(brojIzdanja.getText()), Integer.parseInt(kapacitet.getText())));
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(PrikazKorisnika.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        obrisi.setOnAction(e -> {
            try {
                alert.setContentText("Da li zelite da obrisete tekuci zapis?");
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES && !tv.getSelectionModel().isEmpty()) {
                    tblLok.obrisiBiblioteku(nazivTF.getText());
                    tv.getItems().clear();
                    tv.getItems().addAll(tblLok.listaBiblioteka());
                    nazivTF.clear();

                } else if (tv.getSelectionModel().isEmpty()) {
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Program!");
                    alert1.setContentText("Niste izabrali red iz tabele.");
                    alert1.showAndWait();
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(PrikazKorisnika.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        dodaj.setOnAction(e -> {
            try {
                alert.setContentText("Da li zelite da dodate tekuci zapis?");
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {

                    Biblioteka k = new Biblioteka(nazivTF.getText(), Integer.parseInt(adresa.getText()), Integer.parseInt(zgrada.getText()), Integer.parseInt(opstina.getText()), Integer.parseInt(region.getText()), Integer.parseInt(kapacitet.getText()), Integer.parseInt(brojIzdanja.getText()));
                    tblLok.dodajBiblioteku(k);
                    tv.getItems().clear();
                    tv.getItems().addAll(tblLok.listaBiblioteka());

                    tv.requestFocus();
                    tv.getSelectionModel().selectLast();
                    tv.getFocusModel().focusBelowCell();

                    obrisi.setDisable(false);
                    izmeni.setDisable(false);

                } else {
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Program!");
                    alert1.setContentText("Zeljeni podaci se vec koriste, izaberite druge.");
                    alert1.showAndWait();
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(PrikazKorisnika.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        izmeni.setOnAction(e -> {
            try {
                alert.setContentText("Da li zelite da izmenite tekuci zapis?");
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES && !tv.getSelectionModel().isEmpty()) {
                    int selectedIx = tv.getSelectionModel().getSelectedIndex();

                    Biblioteka k = new Biblioteka(nazivTF.getText(), Integer.parseInt(adresa.getText()), Integer.parseInt(zgrada.getText()), Integer.parseInt(opstina.getText()), Integer.parseInt(region.getText()), Integer.parseInt(kapacitet.getText()), Integer.parseInt(brojIzdanja.getText()));
                    tblLok.izmeniBiblioteku(k, tblLok.listaBiblioteka().get(tv.getSelectionModel().getSelectedIndex()).getIme());
                    tv.getItems().clear();
                    tv.getItems().addAll(tblLok.listaBiblioteka());
                    tv.requestFocus();
                    tv.getSelectionModel().select(selectedIx);
                    tv.getFocusModel().focus(selectedIx);

                } else if (tv.getSelectionModel().isEmpty()) {
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Program!");
                    alert1.setContentText("Niste izabrali red iz tabele.");
                    alert1.showAndWait();
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(PrikazKorisnika.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        tv.setOnMouseClicked(e -> {
            if (tv.getSelectionModel().getSelectedItem() != null) {
                try {
                    obrisi.setDisable(false);
                    izmeni.setDisable(false);

                    nazivTF.setText(tblLok.listaBiblioteka().get(tv.getSelectionModel().getSelectedIndex()).getIme());

                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(PrikazKorisnika.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        main.getChildren().addAll(left, tv);

        Scene scene = new Scene(main, 800, 650);

        primaryStage.setTitle("Biblioteke");
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
    }
}
