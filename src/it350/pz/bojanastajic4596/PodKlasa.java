/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it350.pz.bojanastajic4596;

import baza.DBKontroler;
import entiteti.Korisnik;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author PC
 */
public class PodKlasa {

    public PodKlasa() {
        Stage stage = new Stage();

        VBox registar = new VBox(12);

        Label tekst1 = new Label("Unesite zeljeni username:");
        TextField username = new TextField();

        Label tekst2 = new Label("Unesite zeljenu sifru:");
        PasswordField sifra = new PasswordField();

        Label tekst3 = new Label("Unesite id biblioteke:");
        TextField id = new TextField();

        Button potvrda = new Button("Potvrdi");
        potvrda.setOnAction(e -> {
            DBKontroler unos = new DBKontroler();
            try {
                if (unos.usernameProvera(username.getText().replaceAll("\\s", "")) == false) {
                    String sifraProv = sifra.getText();
                    sifraProv = sifraProv.replaceAll("\\s", "");
                    Korisnik k = new Korisnik(username.getText(), sifraProv, Integer.parseInt(id.getText()));
                    unos.dodajKorisnika(k);
                    stage.close();
                } else {
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Program!");
                    alert1.setContentText("Zeljeni username se vec koristi, izaberite drugi.");
                    alert1.showAndWait();
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(PodKlasa.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        registar.getChildren().addAll(tekst1, username, tekst2, sifra, tekst3, id, potvrda);

        username.setId("fieldRegister");
        sifra.setId("fieldRegister");
        potvrda.setId("dugmeRegister");
        registar.setAlignment(Pos.CENTER);

        Scene scene = new Scene(registar, 400, 360);

        stage.setTitle("Program!");
        stage.setScene(scene);
        stage.show();

    }
}
