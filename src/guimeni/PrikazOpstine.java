/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guimeni;

import baza.DBKontroler;
import entiteti.Knjiga;
import entiteti.Korisnik;
import entiteti.Opstina;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
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
public class PrikazOpstine {

    public PrikazOpstine(Korisnik prijvaljeniKor) throws ClassNotFoundException, SQLException {
        Stage primaryStage = new Stage();

        HBox main = new HBox(20);

        VBox left = new VBox(35);

        DBKontroler tblLok = new DBKontroler();

        HBox leftUp = new HBox(5);
        leftUp.setAlignment(Pos.CENTER);

        Label tekst2 = new Label("Id region: ");
        TextField regionTF = new TextField();

        Label tekst3 = new Label("Naziv:");
        TextField naziv = new TextField();

        VBox tekst = new VBox(15);
        tekst.getChildren().addAll(tekst2, tekst3);
        VBox polja = new VBox(6.5);
        polja.getChildren().addAll(regionTF, naziv);

        leftUp.getChildren().addAll(tekst, polja);

        HBox leftMiddle = new HBox(20);
        Button dodaj = new Button("Dodaj");
        Button izmeni = new Button("Izmeni");
        Button obrisi = new Button("Obrisi");

        leftMiddle.getChildren().addAll(dodaj, izmeni, obrisi);
        leftMiddle.setAlignment(Pos.CENTER);

        left.getChildren().addAll(leftUp, leftMiddle);

        TableView tv = new TableView();
        tblLok.OpstinaTabel(tv);

        obrisi.setDisable(true);
        izmeni.setDisable(true);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        alert.setTitle("Program!");

        obrisi.setOnAction(e -> {
            try {
                alert.setContentText("Da li zelite da obrisete tekuci zapis?");
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES && !tv.getSelectionModel().isEmpty()) {
                    tblLok.obrisiOpstinu(naziv.getText());
                    tv.getItems().clear();
                    tv.getItems().addAll(tblLok.listaOpstina());
                    naziv.clear();

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

                    Opstina k = new Opstina(Integer.parseInt(regionTF.getText()), naziv.getText());
                    tblLok.dodajOpstinu(k);
                    tv.getItems().clear();
                    tv.getItems().addAll(tblLok.listaOpstina());

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

                    Opstina k = new Opstina(Integer.parseInt(regionTF.getText()), naziv.getText());
                    tblLok.izmeniOpstinu(k, tblLok.listaOpstina().get(tv.getSelectionModel().getSelectedIndex()).getNazivOpstina());
                    tv.getItems().clear();
                    tv.getItems().addAll(tblLok.listaOpstina());
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

                    naziv.setText(tblLok.listaOpstina().get(tv.getSelectionModel().getSelectedIndex()).getNazivOpstina());

                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(PrikazKorisnika.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        main.getChildren().addAll(left, tv);

        Scene scene = new Scene(main, 800, 650);

        primaryStage.setTitle("Opstina");
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
    }
}
