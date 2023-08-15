/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it350.pz.bojanastajic4596;

import baza.DBKontroler;
import entiteti.Korisnik;
import guimeni.GUIMeni;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author PC
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox loginForma = new VBox(10);
        HBox dugmici = new HBox(20);

        Label tekst1 = new Label("Username:");
        TextField username = new TextField();
        username.setPromptText("Unesite Vas username");

        Label tekst2 = new Label("Password:");
        PasswordField sifra = new PasswordField();
        sifra.setPromptText("Unesite password:");

        Button prijava = new Button("Log in");
        prijava.setOnAction(e -> {
            DBKontroler user = new DBKontroler();
            try {
                if (user.prijavljeniKorisnik(username.getText(), sifra.getText()).isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Program!");
                    alert.setContentText("Username ili sifra nisu ispravni. Pokusajte ponovo.");
                    alert.showAndWait();
                    username.clear();
                    sifra.clear();
                } else {
                    user.prijavljeniKorisnik(username.getText(), sifra.getText()).forEach(action -> {
                        try {
                            Korisnik k = user.prijavljeniKorisnik(username.getText(), sifra.getText()).get(0);
                            GUIMeni rsm = new GUIMeni(k);
                            username.clear();
                            sifra.clear();
                        } catch (ClassNotFoundException | SQLException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });

                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Button odustajem = new Button("Cancel");
        odustajem.setOnAction(e -> {
            primaryStage.close();
        });

        Button registrujSe = new Button("Registrujte se.");
        registrujSe.setOnAction(e -> {
            PodKlasa registar = new PodKlasa();
        });
        registrujSe.setId("registruj");

        sifra.setId("fieldLogin");
        username.setId("fieldLogin");
        prijava.setId("dugmeLogin");
        odustajem.setId("dugmeLogin");
        tekst1.setId("tekstLogin");
        tekst2.setId("tekstLogin");

        dugmici.getChildren().addAll(prijava, odustajem);
        loginForma.getChildren().addAll(tekst1, username, tekst2, sifra, dugmici, registrujSe);

        dugmici.setAlignment(Pos.CENTER);
        loginForma.setAlignment(Pos.CENTER);

        Scene scene = new Scene(loginForma, 400, 360);

        primaryStage.setTitle("projekat!");
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
