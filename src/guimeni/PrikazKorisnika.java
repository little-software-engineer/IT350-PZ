/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guimeni;

import baza.DBKontroler;
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
import javafx.scene.control.PasswordField;
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
public class PrikazKorisnika {
    
    public PrikazKorisnika(Korisnik prijvaljeniKor) throws ClassNotFoundException, SQLException {
        Stage primaryStage = new Stage();
        
        HBox main = new HBox(20);
        
        VBox left = new VBox(35);
        
        DBKontroler tblKor = new DBKontroler();
        
        HBox leftUp = new HBox(5);
        leftUp.setAlignment(Pos.CENTER);
        
        Label tekst1 = new Label("Korisnicko ime: ");
        TextField username = new TextField();
        
        Label tekst2 = new Label("Sifra: ");
        PasswordField password = new PasswordField();
        
        Label tekst3 = new Label("Id biblioteke: ");
        TextField id = new TextField();
        
        VBox tekst = new VBox(15);
        tekst.getChildren().addAll(tekst1, tekst2, tekst3);
        VBox polja = new VBox(6.5);
        polja.getChildren().addAll(username, password, id);
        
        leftUp.getChildren().addAll(tekst, polja);
        
        HBox leftMiddle = new HBox(20);
        Button dodaj = new Button("Dodaj");
        Button obrisi = new Button("Obrisi");
        Button izmeni = new Button("Izmeni");
        leftMiddle.getChildren().addAll(dodaj, obrisi, izmeni);
        leftMiddle.setAlignment(Pos.CENTER);
        
        left.getChildren().addAll(leftUp, leftMiddle);
        
        TableView tv = new TableView();
        tblKor.KorisnikTabel(tv);
        
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
                    tblKor.obrisiKorisnika(username.getText());
                    tv.getItems().clear();
                    tv.getItems().addAll(tblKor.listaKorisnika());
                    username.clear();
                    password.clear();
                    
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
                    if (tblKor.usernameProvera(username.getText().replaceAll("\\s", "")) == false) {
                        
                        Korisnik k = new Korisnik(username.getText(), password.getText(), Integer.parseInt(id.getText()));
                        tblKor.dodajKorisnika(k);
                        tv.getItems().clear();
                        tv.getItems().addAll(tblKor.listaKorisnika());
                        
                        tv.requestFocus();
                        tv.getSelectionModel().selectLast();
                        tv.getFocusModel().focusBelowCell();
                        
                        obrisi.setDisable(false);
                        izmeni.setDisable(false);
                    } else {
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Program!");
                        alert1.setContentText("Zeljeni username se vec koristi, izaberite drugi.");
                        alert1.showAndWait();
                    }
                    
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
                    if (tblKor.usernameProvera(username.getText().replaceAll("\\s", "")) == false) {
                        Korisnik k = new Korisnik(username.getText(), password.getText());
                        tblKor.izmeniKorisnika(k, tblKor.listaKorisnika().get(tv.getSelectionModel().getSelectedIndex()).getKorisnicko_ime());
                        tv.getItems().clear();
                        tv.getItems().addAll(tblKor.listaKorisnika());
                        tv.requestFocus();
                        tv.getSelectionModel().select(selectedIx);
                        tv.getFocusModel().focus(selectedIx);
                    } else {
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Program!");
                        alert1.setContentText("Zeljeni username se vec koristi, izaberite drugi.");
                        alert1.showAndWait();
                    }
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
                    
                    username.setText(tblKor.listaKorisnika().get(tv.getSelectionModel().getSelectedIndex()).getKorisnicko_ime());
                    password.setText(tblKor.listaKorisnika().get(tv.getSelectionModel().getSelectedIndex()).getSifra());
                    
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(PrikazKorisnika.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        main.getChildren().addAll(left, tv);
        
        Scene scene = new Scene(main, 950, 650);
        
        primaryStage.setTitle("Korisnik");
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
    }
}
