package cui;

import domein.DomeinController;
import exceptions.VerplichtVeldException;
import java.util.InputMismatchException;
import java.util.Scanner;
import talen.Taal;

/**
 * begin van de klasse UC2Applicatie, een applicatie klasse om mastermind te
 * starten
 */
public class UC2Applicatie {

    private final DomeinController dc;

    /**
     * constructor van de UC2Applicatie, roept de start van UC2Applicatie op en
     * stelt de domeinController in. De start-methode start het spel.
     *
     * @param domeinController
     */
    public UC2Applicatie(DomeinController domeinController) {
        this.dc = domeinController;
        start();
    }

    private void start() {
        boolean blijvenHerhalenFlag = true;

        geefOverzichtSpel();

        do {
            try {
                startSpel();
                blijvenHerhalenFlag = false;
            } catch (VerplichtVeldException verplichtVeldException) {
                System.out.printf("%n%S%n%n", Taal.getString("EXC1"));
            } catch (InputMismatchException inputMismatchException) {
                System.out.printf("%n%S%n%n", Taal.getString("EXC2"));
            }
        } while (blijvenHerhalenFlag == true);
        geefSpelbord();
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

    /**
     * Geeft de gebruiker de optie om zijn moeilijkheidsgraad te kiezen. Met
     * deze moeilijkheidsgraad wordt er een nieuw spel gestart
     */
    private void startSpel() {
        Scanner s = new Scanner(System.in);
        System.out.printf("%n%s%n", Taal.getString("diffRules"));
        System.out.printf("%s %n", Taal.getString("chooseDiff"));
        System.out.printf("%s %n", Taal.getString("chooseDiff2"));

        String keuze = s.nextLine();
        int[] in = dc.geefOverzichtSpel();
        boolean herhaal = true;
        while (herhaal) {
            boolean bnormaal = false,
                    bmoeilijk = false;

            if (in[0] > 20) {
                bnormaal = true;
            }
            if (in[1] > 20) {
                bmoeilijk = true;
            }

            switch (Integer.parseInt(keuze)) {
                case 1:
                    herhaal = false;
                    System.out.printf("%s%s%n", Taal.getString("choose"), keuze);
                    dc.startSpel(1);
                    break;
                case 2:
                    if (bnormaal) {
                        herhaal = false;
                        System.out.printf("%s%s%n", Taal.getString("choose"), keuze);
                        dc.startSpel(2);
                    } else {
                        System.out.printf("%s%n", Taal.getString("EXC5"));
                        keuze = s.nextLine();
                    }
                    break;
                case 3:
                    if (bmoeilijk) {
                        herhaal = false;
                        System.out.printf("%s%s%n", Taal.getString("choose"), keuze);
                        dc.startSpel(3);
                    } else {
                        System.out.printf("%s%n", Taal.getString("EXC5"));
                        keuze = s.nextLine();
                    }
            }
        }
    }

    private void geefSpelbord() {

        System.out.printf(dc.geefSpelbord());

        UC3Applicatie a3 = new UC3Applicatie(dc);
    }

} // einde klasse UC2App
