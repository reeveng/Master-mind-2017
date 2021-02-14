package gui;

import domein.DomeinController;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import talen.Taal;


public class KlassementController extends BorderPane 
{
    private final DomeinController dc;
    private final KeuzeSchermController ks;
    @FXML
    private Label lblTitel;
    @FXML
    private Button btnBack;
    @FXML
    private Label lblMGN;
    @FXML
    private Label lblMGE;
    @FXML
    private Label lblMGH;
    @FXML
    private ListView<String> listE;
    @FXML
    private ListView<String> listN;
    @FXML
    private ListView<String> listH;
    @FXML
    private TextArea txaStatsSelected;
    @FXML
    private Button btnChallenge;

    public KlassementController(DomeinController controller, KeuzeSchermController ks)
    {
        this.ks = ks;
        this.dc = controller;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Klassement.fxml"));
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
        lblTitel.setText(Taal.getString("menuUC5"));
        btnBack.setText(Taal.getString("back"));
        lblMGE.setText(Taal.getString("gemakkelijk"));
        lblMGN.setText(Taal.getString("normaal"));
        lblMGH.setText(Taal.getString("moeilijk"));
        initLijsten();
        
    }
    
    private void initLijsten() {
        ObservableList<String> easy = FXCollections.observableArrayList(dc.geefKlassementStringArray("gemakkelijk"));
        listE.setItems(easy);
        ObservableList<String> normal = FXCollections.observableArrayList(dc.geefKlassementStringArray("normaal"));
        listN.setItems(normal);
        ObservableList<String> hard = FXCollections.observableArrayList(dc.geefKlassementStringArray("moeilijk"));
        listH.setItems(hard);
    }

    @FXML
    private void btnBackClicked(ActionEvent event) {
        Stage stage = (Stage) this.getScene().getWindow();
        stage.setScene(ks.getScene());
    }

    @FXML
    private void listEReq(ContextMenuEvent event) {
    }

    @FXML
    private void listEKlik(MouseEvent event) {
    }

    @FXML
    private void listNReq(ContextMenuEvent event) {
    }

    @FXML
    private void listNKlik(MouseEvent event) {
    }

    @FXML
    private void listHReq(ContextMenuEvent event) {
    }

    @FXML
    private void listHKlik(MouseEvent event) {
    }

    @FXML
    private void btnChallengeClicked(ActionEvent event) {
    }
}