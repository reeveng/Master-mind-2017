package gui;

import domein.DomeinController;
import exceptions.SpelerBestaatNietException;
import exceptions.WachtwoordException;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import talen.Taal;

/**
 * FXML Controller class
 *
 * @author arthu
 */
public class AanMeldSchermController extends VBox
{

    @FXML
    private Button buttonNL;
    @FXML
    private Button buttonEN;
    @FXML
    private Button buttonFR;
    @FXML
    private Label lblNaam;
    @FXML
    private Label lblWachtwoord;
    @FXML
    private Button buttonAanmelden;
    @FXML
    private Button buttonRegistreer;

    private DomeinController dc;
    @FXML
    private Label lblFoutbericht;
    @FXML
    private TextField txfNaam;
    @FXML
    private PasswordField psfWachtwoord;

    public AanMeldSchermController(DomeinController dc)
    {
        this.dc = dc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AanMeldScherm.fxml"));
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

        buttonNL.setText("Nederlands");
        buttonFR.setText("Français");
        buttonEN.setText("English");
        String lang = "nl";
        String country = "BE";
        Locale l = new Locale(lang, country);
        ResourceBundle r = ResourceBundle.getBundle("talen/Bundle", l);
        Taal.setR(r);
        initRestGui();
    }

    public void resetAanMeldScherm()
    {
        psfWachtwoord.setText("");
        txfNaam.setText("");
        initRestGui();
    }

    @FXML
    private void frButtonPushed(ActionEvent event)
    {
        String lang = "fr";
        String country = "BE";
        Locale l = new Locale(lang, country);
        ResourceBundle r = ResourceBundle.getBundle("talen/Bundle", l);
        Taal.setR(r);
        initRestGui();
    }

    @FXML
    private void nlButtonPushed(ActionEvent event)
    {
        String lang = "nl";
        String country = "BE";
        Locale l = new Locale(lang, country);
        ResourceBundle r = ResourceBundle.getBundle("talen/Bundle", l);
        Taal.setR(r);
        initRestGui();
    }

    @FXML
    private void enButtonPushed(ActionEvent event)
    {
        String lang = "en";
        String country = "US";
        Locale l = new Locale(lang, country);
        ResourceBundle r = ResourceBundle.getBundle("talen/Bundle", l);
        Taal.setR(r);
        initRestGui();
    }

    private void initRestGui()
    {
        lblNaam.setText("");
        lblNaam.setText(Taal.getString("userName"));
        lblWachtwoord.setText(Taal.getString("pass"));
        buttonAanmelden.setText(Taal.getString("login"));
        buttonRegistreer.setText(Taal.getString("register"));
        buttonRegistreer.setVisible(false);
    }

    @FXML
    private void btnAanmeldenClicked(ActionEvent event)
    {
        try
        {
            dc.meldAan(txfNaam.getText(), psfWachtwoord.getText());
            System.out.printf(dc.geefSpeler());
        } catch (SpelerBestaatNietException ex)
        {
            lblFoutbericht.setText(Taal.getString("nameUnknown"));
            buttonRegistreer.setVisible(true);
        } catch (WachtwoordException ex)
        {
            lblFoutbericht.setText(Taal.getString("passFalse"));
            psfWachtwoord.clear();
        }
        laadKeuzeScherm();
    }

    @FXML
    private void btnRegistreerClicked(ActionEvent event)
    {
        RegistratieSchermController rs = new RegistratieSchermController(dc, txfNaam.getText(), this);
        Scene scene = new Scene(rs, 600, 600);
        Stage stage = (Stage) this.getScene().getWindow();
        stage.setScene(scene);
        //   stage.setFullScreen(true);
        stage.show();
    }

    private void laadKeuzeScherm()
    {
        KeuzeSchermController ks = new KeuzeSchermController(dc, this);
        Scene scene = new Scene(ks, 600, 600);
        Stage stage = (Stage) this.getScene().getWindow();
        stage.setScene(scene);
//        stage.setFullScreen(true);
        stage.show();
    }
}
