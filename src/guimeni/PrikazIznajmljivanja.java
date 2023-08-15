/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guimeni;

import baza.DBKontroler;
import entiteti.Iznajmljivanje;
import entiteti.Korisnik;
import entiteti.Zaposleni;
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
public class PrikazIznajmljivanja {

    public PrikazIznajmljivanja(Korisnik prijvaljeniKor) throws ClassNotFoundException, SQLException {
        Stage primaryStage = new Stage();

        HBox main = new HBox(20);//glavni box u kojem su sa leve strane opcije a sa desne tabela

        VBox left = new VBox(35);//box sa leve strane u kome se nalaze polja i opcije

        DBKontroler tblKor = new DBKontroler();
        //kreiramo box u koji cemo smestiti  polja i tekstove

        HBox leftUp = new HBox(5);
        leftUp.setAlignment(Pos.CENTER);

        Label tekst2 = new Label("Id korisnik: ");
        TextField korisnikTF = new TextField();

        Label tekst3 = new Label("Rok Vracanja: ");
        TextField rok = new TextField();

        Label tekst4 = new Label("Id knjiga: ");
        TextField knjiga = new TextField();

        Label tekst5 = new Label("Broj izdatih knjiga: ");
        TextField brKnjiga = new TextField();

        Label tekst6 = new Label("Id biblioteka: ");
        TextField biblioteka = new TextField();

        VBox tekst = new VBox(15);
        tekst.getChildren().addAll(tekst2, tekst3, tekst4, tekst5, tekst6);
        VBox polja = new VBox(6.5);
        polja.getChildren().addAll(korisnikTF, rok, knjiga, brKnjiga, biblioteka);

        leftUp.getChildren().addAll(tekst, polja);
        //pravimo 2 box kako bi u jednom smestili tekstove a u drugom polja
        //box u koji smestamo opcije
        HBox leftMiddle = new HBox(20);
        Button pocetna = new Button("Pocetna");
        Button izmeni = new Button("Izmeni");
        Button obrisi = new Button("Obrisi");
        leftMiddle.getChildren().addAll(pocetna, izmeni, obrisi);
        leftMiddle.setAlignment(Pos.CENTER);

        left.getChildren().addAll(leftMiddle, leftUp);

        //pravimo tabelu u kojoj cemo ispisivati podatke iz baze
        TableView tv = new TableView();
        tblKor.IznajmljivanjeTabel(tv);

        //Kreiramo alert sa opcijama Yes i NO
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        alert.setTitle("Program!");

        pocetna.setOnAction(e -> {
            primaryStage.close();
        });

        obrisi.setOnAction(e -> {
            try {
                alert.setContentText("Da li zelite da obrisete tekuci zapis?");
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES && !tv.getSelectionModel().isEmpty()) {
                    tblKor.obrisiIznajmljivanje(rok.getText());
                    tv.getItems().clear();
                    tv.getItems().addAll(tblKor.listaIznajmljivanje());
                    rok.clear();

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

        izmeni.setOnAction(e -> {
            try {
                alert.setContentText("Da li zelite da izmenite tekuci zapis?");
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES && !tv.getSelectionModel().isEmpty()) {
                    int selectedIx = tv.getSelectionModel().getSelectedIndex();

                    Iznajmljivanje k = new Iznajmljivanje(Integer.parseInt(korisnikTF.getText()), rok.getText(), Integer.parseInt(knjiga.getText()), Integer.parseInt(brKnjiga.getText()), Integer.parseInt(biblioteka.getText()));
                    tblKor.izmeniIznajmljivanje(k, tblKor.listaIznajmljivanje().get(tv.getSelectionModel().getSelectedIndex()).getRok_vracanja());
                    tv.getItems().clear();
                    tv.getItems().addAll(tblKor.listaIznajmljivanje());
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

                    rok.setText(tblKor.listaIznajmljivanje().get(tv.getSelectionModel().getSelectedIndex()).getRok_vracanja());

                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(PrikazKorisnika.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        main.getChildren().addAll(left, tv);

        Scene scene = new Scene(main, 700, 650);

        primaryStage.setTitle("Korisnik");
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
    }
}
