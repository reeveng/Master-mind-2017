/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import exceptions.UitdagingNietGevondenException;
import java.io.IOException;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import talen.Taal;

/**
 * FXML Controller class
 *
 * @author arthu
 */
public class UitdagingAanvaardenController extends GridPane {

    @FXML
    private Label lblTitel;
    @FXML
    private ListView<String> lvwLijst;
    @FXML
    private Button btnStart;

    /**
     * Initializes the controller class.
     */
    private DomeinController dc;
    private KeuzeSchermController ks;
    @FXML
    private Label lblFoutbericht;
    @FXML
    private Button btnBack;

    public UitdagingAanvaardenController(DomeinController dc, KeuzeSchermController ks) {

        this.dc = dc;
        this.ks = ks;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UitdagingAanvaarden.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        buildGui();
    }

    @FXML
    private void btnStartClicked(ActionEvent event) {
        try {
            String keuze = lvwLijst.getSelectionModel().getSelectedItem();
            String[] deKeuze = keuze.split(" ");
            int id = Integer.parseInt(deKeuze[0]);
            dc.stelUitdagingInVanID(id);
        } catch (UitdagingNietGevondenException ex) {
            lblFoutbericht.setText(Taal.getString("geenUitdagingenTeAccepteren"));
        }

        startUitdaging();

    }

    private void buildGui() {
        reload();
        lblTitel.setText(Taal.getString("menuUC4"));
        btnStart.setText(Taal.getString("Start"));
        btnBack.setText(Taal.getString("back"));
    }

    private void startUitdaging() {
        dc.startUitdagingAlspel();
        SpelSchermController ss = new SpelSchermController(dc, true, ks);
        Scene scene = new Scene(ss, 600, 600);
        Stage stage = (Stage) this.getScene().getWindow();
        stage.setScene(scene);
//        stage.setFullScreen(true);
        stage.show();
    }

    @FXML
    private void btnBackClicked(ActionEvent event) {
        Stage stage = (Stage) this.getScene().getWindow();
        stage.setScene(ks.getScene());
    }

    public void reload() {
        ObservableList<String> items = FXCollections.observableArrayList();
        try {
            String uitd = dc.geefUitdagingenTeAccepteren();
            String[] uitdagingen = uitd.split(String.format("%n"));
            items.addAll(Arrays.asList(uitdagingen));
            
        } catch (UitdagingNietGevondenException ex) {
        } finally {
            lvwLijst.setItems(items);
        }
    }
}
