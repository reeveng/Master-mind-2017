package cui;

import domein.DomeinController;

/**
 * begin van de klasse UC7Applicatie De klasse voor het tonen van het klassement
 *
 * @author arthurpauwels
 */
public class UC7Applicatie {

    private final DomeinController dc;

    /**
     * constructor van de klasse, roept methodes op om te starten en stelt de dc
     * in
     *
     * @param dc
     */
    public UC7Applicatie(DomeinController dc) {
        this.dc = dc;
        start();
    }

    private void start() {
        System.out.println(dc.toonKlassement());
        UC1Applicatie.kiesUC();
    }
}
