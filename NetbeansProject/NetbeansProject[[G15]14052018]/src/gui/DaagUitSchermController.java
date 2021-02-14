/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import talen.Taal;

/**
 * FXML Controller class
 *
 * @author arthurpauwels
 */
public class DaagUitSchermController extends BorderPane
{

    private final DomeinController dc;

    private static final ObservableList TS1 = FXCollections.observableArrayList();
    private static final ObservableList TS2 = FXCollections.observableArrayList();
    private static final ObservableList TS3 = FXCollections.observableArrayList();
    @FXML
    private Label lblTitel;
    @FXML
    private Label lblMG;
    @FXML
    private RadioButton rbtEasy;
    @FXML
    private RadioButton rbtNormal;
    @FXML
    private RadioButton rbtHard;
    @FXML
    private Button btnStart;
    @FXML
    private ListView<String> listSpelers;
    private KeuzeSchermController ks;
    @FXML
    private Label lblFoutbericht;
    @FXML
    private Button btnBack;

    public DaagUitSchermController(DomeinController dc, KeuzeSchermController ks)
    {
        this.dc = dc;
        this.ks = ks;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DaagUitScherm.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try
        {
            loader.load();
        } catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
        initGui();
    }

    private void initGui()
    {
        ToggleGroup group = new ToggleGroup();
        rbtEasy.setToggleGroup(group);
        rbtEasy.setSelected(false);
        rbtNormal.setToggleGroup(group);
        rbtHard.setToggleGroup(group);
        lblTitel.setText(Taal.getString("Uitdaging"));
        lblMG.setText(Taal.getString("L"));
        rbtEasy.setText(Taal.getString("L1"));
        rbtNormal.setText(Taal.getString("L2"));
        rbtHard.setText(Taal.getString("L3"));
        btnStart.setText(Taal.getString("Start"));
        btnBack.setText(Taal.getString("back"));

        String[] ts1 = dc.geefTegenspelersArray("gemakkelijk");
        String[] ts2 = dc.geefTegenspelersArray("normaal");
        String[] ts3 = dc.geefTegenspelersArray("moeilijk");

        TS1.addAll(ts1);
        TS2.addAll(ts2);
        TS3.addAll(ts3);

    }

    @FXML
    private void rbtEasyOnAction(ActionEvent event)
    {
        listSpelers.setItems(TS1);
    }

    @FXML
    private void rbtNormalOnAction(ActionEvent event)
    {
        listSpelers.setItems(TS2);
    }

    @FXML
    private void rbtHardOnAction(ActionEvent event)
    {
        listSpelers.setItems(TS3);
    }

    @FXML
    private void btnStartOnAction(ActionEvent event)
    {
        String tegenSpeler = listSpelers.getSelectionModel().getSelectedItem();
        if (listSpelers.getSelectionModel().isEmpty())
        {
            lblFoutbericht.setText(Taal.getString("qChoosePlayerToChallange"));
            lblFoutbericht.setWrapText(true);
        } else
        {
            lblFoutbericht.setText("");
            if (rbtEasy.isSelected())
            {
                uitdagingStarten(1, tegenSpeler);
            } else if (rbtNormal.isSelected())
            {
                uitdagingStarten(2, tegenSpeler);

            } else if (rbtHard.isSelected())
            {
                uitdagingStarten(2, tegenSpeler);

            } else
            {
                lblFoutbericht.setText(Taal.getString("EXC1"));
            }
        }
    }

    private void uitdagingStarten(int graad, String speler)
    {
        dc.startUitdaging(graad, speler);
        dc.registreerUitdaging();
        dc.stelUitdagingIdIn();
        dc.startUitdagingAlspel();

        SpelSchermController ss = new SpelSchermController(dc, true, ks);
        Scene scene = new Scene(ss, 600, 600);
        Stage stage = (Stage) this.getScene().getWindow();
        stage.setScene(scene);
        //stage.setFullScreen(true);
        stage.show();
    }

    @FXML
    private void btnBackClicked(ActionEvent event)
    {
        Stage stage = (Stage) this.getScene().getWindow();
        stage.setScene(ks.getScene());
    }
}
