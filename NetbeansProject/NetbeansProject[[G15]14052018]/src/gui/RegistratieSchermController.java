/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import exceptions.SpelersNaamInGebruikException;
import exceptions.VerplichtVeldException;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import talen.Taal;

/**
 * FXML Controller class
 *
 * @author reeve
 */
public class RegistratieSchermController extends GridPane {

    //final attributen
    private final DomeinController dc;
    private final AanMeldSchermController as;

    //FXML attributen
    @FXML
    private Label lblPassword;
    @FXML
    private Label lblName;
    @FXML
    private TextField txfName;
    @FXML
    private Label lblRepeatPassword;
    @FXML
    private Label lblMessage;
    @FXML
    private Label lblTitel;
    @FXML
    private Button btnConfirm;
    @FXML
    private ImageView ivLogoMastermind;
    @FXML
    private PasswordField psfPassword;
    @FXML
    private PasswordField psfRepeatPassword;
    

    public RegistratieSchermController(DomeinController dc, String naam, AanMeldSchermController as) {
        this.dc = dc;
        this.as = as;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RegistratieScherm.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        initGui(naam);
    }

    private void initGui(String naam) {
        lblTitel.setText(Taal.getString("register"));
        lblPassword.setText(Taal.getString("pass"));
        lblRepeatPassword.setText(Taal.getString("confirmPass"));
        lblName.setText(Taal.getString("userName"));
        txfName.setText(naam);
        btnConfirm.setText(Taal.getString("register"));
    }

    @FXML
    private void btnRegistreerClicked(ActionEvent event) {
        try {
            dc.registreer(txfName.getText(), psfPassword.getText(), psfRepeatPassword.getText());
            lblMessage.setText("OK");
            lblMessage.setTextFill(Color.GREEN);
            laadKeuzeScherm();
        } catch (SpelersNaamInGebruikException spelersNaamInGebruikException) {
            lblMessage.setText(Taal.getString("nameUsed"));

        } catch (VerplichtVeldException verplichtVeldException) {
            lblMessage.setText(Taal.getString("passFalse") + Taal.getString("passRules"));
        }
        //laadKeuzeScherm();

    }

    private void laadKeuzeScherm() {
        KeuzeSchermController sc = new KeuzeSchermController(dc, as);
        Scene scene = new Scene(sc, 600, 600);
        Stage stage = (Stage) this.getScene().getWindow();
        stage.setScene(scene);
//        stage.setFullScreen(true);
        stage.show();
    }
}
