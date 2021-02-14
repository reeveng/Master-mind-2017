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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import talen.Taal;

public class LaadSpelController extends GridPane
{

    private final DomeinController dc;
    private final KeuzeSchermController ks;
    @FXML
    private ListView<String> LVOverzicht;
    @FXML
    private Button btnLaadSpel;
    @FXML
    private Label lblTitel;
    @FXML
    private Button btnBack;

    public LaadSpelController(DomeinController controller, KeuzeSchermController ks)
    {
        this.dc = controller;
        this.ks = ks;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LaadSpel.fxml"));
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

    @FXML
    private void btnLaadSpelClicked(ActionEvent event)
    {
        String naamStr[] = LVOverzicht.getSelectionModel().getSelectedItem().trim().split(" ");
        String naamSpel = String.format(naamStr[0]);
        dc.laadSpel(naamSpel);
        dc.verwijderSpel();
        SpelSchermController ss = new SpelSchermController(dc, false, ks);
        Scene scene = new Scene(ss, 600, 600);
        Stage stage = (Stage) this.getScene().getWindow();
        stage.setScene(scene);
//        stage.setFullScreen(true);
        stage.show();

    }

    private void initGui()
    {
        lblTitel.setText(Taal.getString("menuUC2"));
        btnBack.setText(Taal.getString("back"));
        btnLaadSpel.setText(Taal.getString("Start"));
        ObservableList<String> items = FXCollections.observableArrayList(
                dc.geefOverzichtSpelletjes());
        LVOverzicht.setItems(items);
    }

    @FXML
    private void btnBackClicked(ActionEvent event)
    {
        Stage stage = (Stage) this.getScene().getWindow();
        stage.setScene(ks.getScene());
    }

}
