/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import talen.Taal;

/**
 * FXML Controller class
 *
 * @author arthurpauwels
 */
public class KeuzeSchermController extends BorderPane {

    private final AanMeldSchermController as;
    private final DomeinController dc;
    @FXML
    private Label lblWelkom;
    @FXML
    private Button btnQuit;
    @FXML
    private Label lblStartGame;
    @FXML
    private Button btnStartE;
    @FXML
    private Button btnStartN;
    @FXML
    private Button btnStartH;
    @FXML
    private Button btnLoad;
    @FXML
    private Button btnChallenge;
    @FXML
    private Button btnAcceptChallenge;
    @FXML
    private Button btnShowScores;
    @FXML
    private Label lblStats;
    @FXML
    private TextArea txaStats;

    /**
     * Initializes the controller class.
     *
     * @param dc
     */
    public KeuzeSchermController(DomeinController dc, AanMeldSchermController as) {
        this.dc = dc;
        this.as = as;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("KeuzeScherm.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        initGui();

    }

    private void initGui() {
        lblWelkom.setText(String.format("%s, %s!", Taal.getString("welcome"), dc.geefSpeler()));
        lblStats.setText(Taal.getString("stats"));
        btnStartE.setText(Taal.getString("gemakkelijk"));
        btnStartN.setDisable(true);
        btnStartN.setText(Taal.getString("normaal"));
        btnStartH.setDisable(true);
        btnStartH.setText(Taal.getString("moeilijk"));
        geefOverzicht();
        btnLoad.setText(Taal.getString("menuUC2"));
        btnQuit.setText(Taal.getString("logout"));
        btnChallenge.setText(Taal.getString("menuUC3"));
        btnAcceptChallenge.setText(Taal.getString("menuUC4"));
        btnShowScores.setText(Taal.getString("menuUC5"));
    }

    @FXML
    private void btnQuitClicked(ActionEvent event) {
        as.resetAanMeldScherm();
        Stage stage = (Stage) this.getScene().getWindow();
        stage.setScene(as.getScene());
        dc.logUit();

    }

    @FXML
    private void btnStartEClicked(ActionEvent event) {
        spelLaden(1);
    }

    @FXML
    private void btnStartNClicked(ActionEvent event) {
        spelLaden(2);
    }

    @FXML
    private void btnStartHClicked(ActionEvent event) {

        spelLaden(3);
    }

    @FXML
    private void btnLoadClicked(ActionEvent event) {
        LaadSpelController ls = new LaadSpelController(dc, this);
        Scene scene = new Scene(ls, 600, 600);
        sceneLaden(scene);
    }

    @FXML
    private void btnChallengeClicked(ActionEvent event
    ) {
        DaagUitSchermController dus = new DaagUitSchermController(dc, this);
        Scene scene = new Scene(dus, 700, 700);
        sceneLaden(scene);

    }

    @FXML
    private void btnAcceptChallengeClicked(ActionEvent event
    ) {
        UitdagingAanvaardenController uas = new UitdagingAanvaardenController(dc, this);
        uas.reload();
        Scene scene = new Scene(uas, 600, 600);
        sceneLaden(scene);
    }

    @FXML
    private void btnShowScoresClicked(ActionEvent event
    ) {
        KlassementController kc = new KlassementController(dc, this);
        Scene scene = new Scene(kc, 600, 600);
        sceneLaden(scene);
    }

    private void geefOverzicht() {
        int[] scores = dc.geefOverzichtSpel();
        Button[] btns = {btnStartN, btnStartH};
        
        String overzichtStr = String.format("%30s%30s%n", Taal.getString("L"), Taal.getString("score"));
        overzichtStr += String.format("%30s%30d%n", Taal.getString(dc.decodeerMoeilijkheidsgraad(1)), scores[0]);
        for (int i = 0; i < btns.length; i++){
            if (scores[i] < 20){
                overzichtStr += String.format(Taal.getString("jeMoetNogXKeerSpelen"), 20 - scores[i]);
            } else {
                btns[i].setDisable(false);
            }
            overzichtStr += String.format("%30s%30d%n", Taal.getString(dc.decodeerMoeilijkheidsgraad(i+2)), scores[i+1]);
        }
        
        txaStats.setText(overzichtStr);
    }

    private void spelLaden(int graad) {
        dc.startSpel(graad);
        SpelSchermController ss = new SpelSchermController(dc, true, this);
        Scene scene = new Scene(ss, 1000, 700);
        sceneLaden(scene);

    }

    private void sceneLaden(Scene scene) {

        Stage stage = (Stage) this.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
