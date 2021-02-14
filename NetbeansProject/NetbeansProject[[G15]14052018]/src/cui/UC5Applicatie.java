package cui;

import domein.DomeinController;
import domein.Speler;
import java.util.Scanner;
import talen.Taal;

/**
 * begin van de klasse UC5Applicatie, een applicatie klasse om iemand uit te
 * dagen
 *
 */
public class UC5Applicatie {

    private final DomeinController dc;

    /**
     * Constructor van de UC5Applicatie, die de start methode aanroept en die de
     * domeinController insteld
     *
     * @param domeinController
     */
    public UC5Applicatie(DomeinController domeinController) {
        this.dc = domeinController;
        start();
    }

    private void start() {
        Scanner s = new Scanner(System.in);
        geefOverzichtSpel();
        boolean herhaal = true;
        do {
            try {

                int mg = kiesMoeilijkheidsgraad(s);
                String tegenspeler = kiesTegenspeler(mg);

                startUitdaging(mg, tegenspeler);
                geefSpelbord();
                UC3Applicatie a3 = new UC3Applicatie(dc);
                herhaal = false;
            } catch (IllegalArgumentException eax) {
                System.out.printf("%s%n%n", Taal.getString("PNOA"));
            }
        } while (herhaal == true);

    }

    private void geefOverzichtSpel() {
        String resul = String.format("%15s%25s%n", Taal.getString("L"), Taal.getString("NOG"));

        int[] in = dc.geefOverzichtSpel();
        String[] benamingen
                = {
                    Taal.getString("L1"), Taal.getString("L2"), Taal.getString("L3")
                };
        resul += String.format("%-15s%25s%n", benamingen[0], in[0]);
        if (in[0] >= 20) {
            resul += String.format("%-15s%25s%n", benamingen[1], in[1]);
        }
        if (in[1] >= 20) {
            resul += String.format("%-15s%25s%n", benamingen[2], in[2]);
        }
        System.out.printf(resul);
    }

    private int kiesMoeilijkheidsgraad(Scanner s) {
        System.out.printf("%n%s%n", Taal.getString("diffRules"));
        System.out.printf("%s: %n", Taal.getString("chooseDiff"));
        System.out.printf("%s: %n", Taal.getString("chooseDiff2"));

        String keuze = s.next();
        System.out.printf("%s%s%n", Taal.getString("choose"), keuze);
        while (!(keuze.equals("1") || keuze.equals("2") || keuze.equals("3"))) {
            System.out.printf("%s: %n", Taal.getString("chooseDiff"));
            System.out.printf("%s: %n", Taal.getString("chooseDiff2"));
            keuze = s.next();
        }
        return Integer.parseInt(keuze);
    }

    /**
     * geeft de gebruiker de mogelijkheid om een tegenspeler te kiezen
     *
     */
    private String kiesTegenspeler(int gekozenMG) {
        return dc.geefTegenspelers(gekozenMG);
    }

    private void startUitdaging(int mg, String tegenspeler) {
        dc.startUitdaging(mg, tegenspeler);
        dc.registreerUitdaging();
        dc.stelUitdagingIdIn();
        dc.startUitdagingAlspel();
    }

    private void geefSpelbord() {
        System.out.printf(dc.geefSpelbord());
    }
}//einde UC5App

