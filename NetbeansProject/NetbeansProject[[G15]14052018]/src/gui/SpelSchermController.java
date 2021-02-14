package gui;

import domein.DomeinController;
import exceptions.LegePogingException;
import exceptions.SpelNaamInGebruikException;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;
import talen.Taal;

/**
 * FXML Controller class
 *
 * @author arthu
 */
public class SpelSchermController extends SplitPane
{

    @FXML
    private VBox vorigePogingPaneel;
    @FXML
    private FlowPane huidigePogingPaneel;
    @FXML
    private FlowPane pinPaneel;
    @FXML
    private Button submitButton;
    @FXML
    private Button clearButton;
    private HBox buttonBox;
    private String huidigePoging;
    private char[] pinKleurenLetter = new char[]
    {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'
    };

    private DomeinController dc;
    private static int[] poging;
    private static int teller = 0;
//    private int maxCirkel;
    private Color[] pinKleuren = new Color[]
    {
        Color.TRANSPARENT, Color.RED, Color.YELLOW, Color.GREEN, Color.ORANGE, Color.BROWN, Color.BLUE, Color.BLACK, Color.WHITE, Color.TRANSPARENT
    };
    @FXML
    private Label lblFoutBericht;
    private TextArea txaEndGame;
    @FXML
    private Label lblEndGame;
    @FXML
    private Button btnSave;
    @FXML
    private TextField txfName;
    @FXML
    private Button btnSaved;
    @FXML
    private Button btnBack;
    private final KeuzeSchermController ks;

    public SpelSchermController(DomeinController dc, boolean nieuw, KeuzeSchermController ks)
    {
        this.dc = dc;
        this.ks = ks;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SpelScherm.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try
        {
            loader.load();
        } catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
        voegKeuzesToe();
        initGui();
        if (nieuw == false)
        {
            geladenSpel();
        }

    }

    /**
     * voegt de cirkels toe die kunnen gekozen worden met een event handler per
     * cirkel. Laatste cirkel is de lege poging(transparant), deze krijgt een
     * zwarte rand op het einde van de lus
     */
    private void voegKeuzesToe()
    {
        for (int i = 0; i < pinKleurenLetter.length - 1; i++)
        {
            Circle cirkel = new Circle();
            cirkel.setRadius(20);
            cirkel.setFill(pinKleuren[i]);
            cirkel.setStroke(Color.BLACK);
            cirkel.setOnMouseClicked(new PinMouseClickListener(pinKleurenLetter[i]));
            pinPaneel.getChildren().add(cirkel);
        }

    }

    private void initGui()
    {
        submitButton.setText(Taal.getString("entertry"));
        clearButton.setText(Taal.getString("clear"));
        lblEndGame.setVisible(false);
        btnSaved.setText(Taal.getString("?SV2"));
        btnSave.setText(Taal.getString("?SV"));
        btnSaved.setVisible(false);
        btnSave.setVisible(false);
        txfName.setVisible(false);
        btnBack.setText(Taal.getString("back"));
        btnBack.setVisible(true);
    }

    /**
     * geeft de poging in. Stelt een foutbericht in bij verkeerde/ongeldige
     * poging;
     */
    @FXML
    private void klikOpSubmit()
    {

        try
        {
            dc.speelWedstrijd(poging);
            System.out.printf(dc.getTeRadenCode());
            voegVorigePogingToe();
            resetHuidigePoging();
            ++teller;

            btnSave.setVisible(true);
            System.out.printf("%d%S", teller, "ingave succesvol");
            lblFoutBericht.setText(Taal.getString("rowinsert") + "" + teller + ".");
            lblFoutBericht.setTextFill(Color.web("#00FF00"));
            eindeSpel();

        } catch (ArrayIndexOutOfBoundsException | LegePogingException e)
        {
            lblFoutBericht.setTextFill(Color.web("#0076a3"));
            lblFoutBericht.setText(Taal.getString("EXC4"));

        }

    }

    @FXML
    private void klikOpClear()
    {

        huidigePogingPaneel.getChildren().clear();
        huidigePoging = "";
        lblFoutBericht.setText("");

    }

    /**
     * Stelt de weergave van de gekozen poging in. Als een cirkel aangeklikt is
     * verschijnt deze op het paneel. 4 of 5 (moeilijk) cirkels toegelaten
     */
    public void updateHuidigePogingPaneel()
    {
        try
        {
            int[][] pogingen = dc.geefPoging();
            setAantalPogingen(pogingen);
            char[] pogingLetters = huidigePoging.toCharArray();
            poging = new int[pogingen[teller].length / 2];
            Circle pin = new Circle();
            int tellerCirkel = 0;
            for (int i = 0; i < pogingLetters.length; i++)
            {
                for (int j = 0; j < pinKleurenLetter.length; j++)
                {

                    if (pogingLetters[i] == pinKleurenLetter[j])
                    {

                        pin.setStroke(Color.BLACK);
                        pin.setRadius(20);
                        pin.setFill(pinKleuren[j]);
                        poging[tellerCirkel] = j;
                        ++tellerCirkel;
                        break;
                    }
                }
            }
            huidigePogingPaneel.getChildren().add(pin);
        } catch (ArrayIndexOutOfBoundsException ex)
        {
            lblFoutBericht.setTextFill(Color.RED);
            lblFoutBericht.setText(Taal.getString("EXC4"));
        }

    }

    /**
     * controleert of de 12 pogingen bereikt zijn en/of het spel gewonnen is.
     *
     */
    public void eindeSpel()
    {

        if (dc.isEindeSpel() == true)
        {
            if (dc.isGewonnen() == true)
            {
                lblFoutBericht.setText(Taal.getString("won"));
                lblFoutBericht.setTextFill(Color.web("#00FF00"));
                dc.registreerWinst();
                btnSave.setVisible(false);
            }
            dc.updateScore();
            lblEndGame.setVisible(true);
            lblEndGame.setStyle("text-area-background: grey;");
            lblEndGame.setText(dc.geefEindOverzicht());
            verbergFuncties();
            btnBack.setVisible(true);

        }

    }

    /**
     * Wanneer een spel geladen is worden de aantal reeds gemaakte pogingen
     * ingesteld.
     *
     */
    public void setAantalPogingen(int[][] pogingen)
    {
        int t = -1;
        for (int[] i : pogingen)
        {
            t++;
            if (i[0] == 0)
            {
                teller = t;
                dc.setAantalPogingen(teller);
                break;
            }
        }
    }

    /**
     * Wanneer een poging geldig wordt deze aan het visuele spelbord toegevoegd
     */
    public void voegVorigePogingToe()
    {
        int[][] pogingen = dc.geefPoging();
        Circle[] pins = new Circle[pogingen[teller].length];

        for (int rij = 0; rij < pins.length; rij++)
        {
            int positie = pogingen[teller][rij];
            pins[rij] = new Circle();
            pins[rij].setRadius(20);
            pins[rij].setStroke(Color.BLACK);
            pins[rij].setFill(pinKleuren[positie]);

        }

        FlowPane flow = new FlowPane();
        flow.setStyle("-fx-background-color:  #808080;");
        flow.setHgap(5);
        flow.setVgap(5);
        flow.getChildren().addAll(pins);
        vorigePogingPaneel.getChildren().add(flow);
    }

    /**
     *
     */
    public void resetHuidigePoging()
    {
        huidigePoging = "";
        poging = new int[0];
//        updateHuidigePogingPaneel();
        huidigePogingPaneel.getChildren().clear();
    }

    @FXML
    private void btnSaveClicked(ActionEvent event)
    {
        txfName.setVisible(true);
        btnSaved.setVisible(true);
    }

    @FXML
    private void btnSavedClicked(ActionEvent event)
    {
        if (txfName.getText().isEmpty())
        {
            lblFoutBericht.setText(Taal.getString("nameUsed"));
        } else
        {
            try
            {
                String naam = txfName.getText();

                dc.registreerSpel(naam, dc.getBord());

            } catch (SpelNaamInGebruikException ex)
            {
                lblFoutBericht.setText(Taal.getString("nameUsed"));
            }
            lblFoutBericht.setText(Taal.getString("SV"));
            lblFoutBericht.setTextFill(Color.web("#00FF00"));
            btnBack.setVisible(true);
            verbergFuncties();
        }
    }

    private void verbergFuncties()
    {
        submitButton.setVisible(false);
        clearButton.setVisible(false);
        btnSaved.setVisible(false);
        btnSave.setVisible(false);
        txfName.setVisible(false);
    }

    @FXML
    private void btnBackClicked(ActionEvent event)
    {
        Stage stage = (Stage) this.getScene().getWindow();
        stage.setScene(ks.getScene());
        stage.show();
    }

    /**
     * Wanneer een opgeslagen spel geladen moet worden, zal eerst het visuele
     * spelbord opgevuld worden met de opgeslagen pogingen.
     */
    private void geladenSpel()
    {
        int[][] pogingen = dc.geefPoging();
        int t = -1;
        for (int[] i : pogingen)
        {
            t++;
            if (i[0] == 0)
            {
                teller = t;
                dc.setAantalPogingen(teller);
                break;
            }
        }
        Circle[] pins = new Circle[pogingen[teller].length];
        for (int rij = 0; rij < t; rij++)
        {
            for (int kolom = 0; kolom < pogingen[rij].length; kolom++)
            {
                int positie = pogingen[rij][kolom];

                pins[kolom] = new Circle();
                pins[kolom].setRadius(20);
                pins[kolom].setStroke(Color.BLACK);
                pins[kolom].setFill(pinKleuren[positie]);
            }
            FlowPane flow = new FlowPane();
            flow.setStyle("-fx-background-color:  #808080;");
            flow.setHgap(5);
            flow.setVgap(5);
            flow.getChildren().addAll(pins);
            vorigePogingPaneel.getChildren().add(flow);
        }

    }

    class PinMouseClickListener implements EventHandler<MouseEvent>
    {

        private char letter;

        PinMouseClickListener(char letter)
        {
            this.letter = letter;
        }

        @Override
        public void handle(MouseEvent event)
        {

            huidigePoging += letter;
            updateHuidigePogingPaneel();
        }
    }
}
