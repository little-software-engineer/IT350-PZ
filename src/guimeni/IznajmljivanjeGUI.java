/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guimeni;

import baza.DBKontroler;
import builder.IznajmljivanjeBuilder;
import entiteti.Biblioteka;
import entiteti.Korisnik;
import java.sql.SQLException;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author PC
 */
public class IznajmljivanjeGUI {

    public IznajmljivanjeGUI(Korisnik prijvaljeniKor, Biblioteka b) throws ClassNotFoundException, SQLException {
        Stage primaryStage = new Stage();

        HBox main = new HBox(20);//glavni box u kojem su sa leve strane opcije a sa desne tabela

        VBox left = new VBox(35);//box sa leve strane u kojem su polja i opcije
        left.setAlignment(Pos.CENTER);

        DBKontroler tblRez = new DBKontroler();
        //kreiramo box u koji smestamo polja i tekstove
        HBox leftUp = new HBox(5);
        leftUp.setAlignment(Pos.CENTER);

        Label tekst1 = new Label("Rok vracanja: ");
        TextField vreme = new TextField();

        Label tekst2 = new Label("ID korisnik:");
        TextField korisnik = new TextField();
        Pattern pattern2 = Pattern.compile("\\d{0,2}|\\d{2}");
        TextFormatter formatter2 = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern2.matcher(change.getControlNewText()).matches() ? change : null;
        });
        korisnik.setTextFormatter(formatter2);

        Label tekst3 = new Label("ID Knjiga:");
        TextField knjiga = new TextField();
        Pattern pattern3 = Pattern.compile("\\d{0,2}|\\d{2}");
        TextFormatter formatter3 = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern3.matcher(change.getControlNewText()).matches() ? change : null;
        });
        knjiga.setTextFormatter(formatter3);

        Label tekst4 = new Label("Broj Knjiga:");
        TextField brknjiga = new TextField();
        Pattern pattern4 = Pattern.compile("\\d{0,2}|\\d{2}");
        TextFormatter formatter4 = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern4.matcher(change.getControlNewText()).matches() ? change : null;
        });
        knjiga.setTextFormatter(formatter4);

        Label tekst5 = new Label("ID Biblioteka:");
        TextField biblioteka = new TextField();
        Pattern pattern5 = Pattern.compile("\\d{0,2}|\\d{2}");
        TextFormatter formatter5 = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern5.matcher(change.getControlNewText()).matches() ? change : null;
        });
        knjiga.setTextFormatter(formatter5);

        //pravimo 2 box kako bi u jedan smestili tekstove a u drugi polja
        VBox tekst = new VBox(15);
        tekst.getChildren().addAll(tekst2, tekst1, tekst3, tekst4, tekst5);
        VBox polja = new VBox(6.5);
        polja.getChildren().addAll(korisnik, vreme, knjiga, brknjiga, biblioteka);

        leftUp.getChildren().addAll(tekst, polja);

        //box u koji smestamo opcije
        HBox leftMiddle = new HBox(20);
        Button iznajmi = new Button("Iznajmi");
        leftMiddle.getChildren().addAll(iznajmi);
        leftMiddle.setAlignment(Pos.CENTER);

        left.getChildren().addAll(leftUp, leftMiddle);

        //pravimo tabelu u kojoj cemo ispisivati podatke iz baze
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        alert.setTitle("Program!");

        iznajmi.setOnAction(e -> {
            alert.setContentText("Da li zelite da iznajmite knjigu?");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES && !vreme.getText().isEmpty() && !knjiga.getText().isEmpty() && !korisnik.getText().isEmpty() && !brknjiga.getText().isEmpty() && !biblioteka.getText().isEmpty()) {

                try {
                    tblRez.dodajIznajmljivanje(
                            new IznajmljivanjeBuilder()
                                    .Korisnik(prijvaljeniKor)
                                    .vracanje(vreme.getText())
                                    .idKnjiga(Integer.parseInt(knjiga.getText()))
                                    .brojIznKnjiga(Integer.parseInt(brknjiga.getText()))
                                    .idBiblioteka(Integer.parseInt(biblioteka.getText()))
                                    .build());
                    // PrikazIznajmljivanja prikaziz = new PrikazIznajmljivanja(prijvaljeniKor);
                    primaryStage.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(IznajmljivanjeGUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Program!");
                alert1.setContentText("Niste uneli trazene podatke.");
                alert1.showAndWait();
            }
        });
        main.getChildren().addAll(left);
        main.setAlignment(Pos.CENTER);

        Scene scene = new Scene(main, 700, 500);

        primaryStage.setTitle("Iznajmljivanje");
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
    }
}
