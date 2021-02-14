package cui;

import domein.DomeinController;
import java.util.List;
import java.util.Scanner;
import talen.Taal;

/**
 * begin van de klasse UC4Applicatie, een applicatie klasse voor opgeslagen
 * spelletjes te laden
 */
public class UC4Applicatie {

    private final DomeinController dc;

    /**
     * constructor UC4Applicatie vanuit de constructor wordt de domeinController
     * ingesteld en de functie start aangeroepen start geeft een lijst van de
     * opgeslagen spellen en geeft de optie om een spel te laden
     *
     * @param domeinController
     */
    public UC4Applicatie(DomeinController domeinController) {
        this.dc = domeinController;
        start();
    }

    private void start() {
        Scanner s = new Scanner(System.in);

        List<String> spelletjes = dc.geefOverzichtSpelletjes();
        System.out.printf("%25s%25s:%n", Taal.getString("nameGame"), Taal.getString("L"));
        spelletjes.forEach((spelleke)
                -> {
            System.out.printf("%s%n", spelleke);
        });
        if (spelletjes.isEmpty()) {
            System.out.printf("%n%S%n", Taal.getString("NOA"));
            System.out.println("");
            UC2Applicatie a2 = new UC2Applicatie(dc);
        }

        System.out.printf("%s%n", Taal.getString("qGiveName"));
        String naam = s.nextLine().trim();
        while (dc.bestaatNaam(naam) == false) {
            System.out.printf("%s%n", Taal.getString("nameUsed"));
            System.out.printf("%s%n", Taal.getString("qGiveName"));
            naam = s.nextLine().trim();
        }
        dc.laadSpel(naam);
        System.out.printf(dc.geefSpelbord());
        dc.verwijderSpel();
        System.out.printf("%S%n", Taal.getString("DBdelete"));
        UC3Applicatie a3 = new UC3Applicatie(dc);

    }

}
