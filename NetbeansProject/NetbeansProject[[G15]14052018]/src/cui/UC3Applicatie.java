package cui;

import domein.DomeinController;
import exceptions.LegePogingException;
import exceptions.SpelNaamInGebruikException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

import java.util.Scanner;
import talen.Taal;

/**
 * begin van de klasse UC3Applicatie, een applicatie klasse om een spel te
 * starten
 *
 * @author reeve
 */
public class UC3Applicatie {

    private final DomeinController dc;
    private static int teller = 0;
    private static String eenRij;
    private static final String[] KLEUREN
            = {
                Taal.getString("1"), Taal.getString("2"), Taal.getString("3"), Taal.getString("4"), Taal.getString("5"), Taal.getString("6"), Taal.getString("7"), Taal.getString("8"), Taal.getString("9")
            };

    /**
     * Constructor van de UC3Applicatie, roept de start methode op
     *
     * @param domeinController
     */
    public UC3Applicatie(DomeinController domeinController) {
        this.dc = domeinController;
        boolean herhalen3 = true;
        do {
            try {
                eenRij = "";
                start();
                herhalen3 = false;
            } catch (SpelNaamInGebruikException ex) {
                System.out.printf("%S%n", Taal.getString("nameUsed"));
            }
        } while (herhalen3);
        UC1Applicatie.kiesUC();
    }

    private void start() throws SpelNaamInGebruikException {
        Scanner s = new Scanner(System.in);

        speelOfBewaar(s);
        geefCodepinnen();
        System.out.printf("%s%n", Taal.getString("continue"));

        boolean herhaal = true;

        int[][] pogingen = dc.geefPoging();

        int t = -1;
        for (int[] i : pogingen) {
            t++;
            if (i[0] == 0) {
                teller = t;
                dc.setAantalPogingen(teller);
                break;
            }
        }

        int[] poging = new int[pogingen[teller].length / 2];

        while (herhaal) {
            boolean herhaal2 = true;
            do {
                try {
                    poging = geefCodePinnenIn(poging, s);

                    herhaal2 = false;
                } catch (LegePogingException | InputMismatchException ex) {
                    System.out.printf("%s%n", Taal.getString("EXC4"));
                }

            } while (herhaal2);

            dc.speelWedstrijd(poging);

            geefRijenWeer(pogingen);
            speelOfBewaar(s);
            teller++;
            if (dc.isEindeSpel() == true) {
                dc.updateScore();
                herhaal = false;
            }
        }//einde while lus
        System.out.printf("%s%n", Taal.getString("end"));
        System.out.println("");
        if (dc.isGewonnen() == true) {
            System.out.printf("%S%n", Taal.getString("won"));
            dc.registreerWinst();
            System.out.printf("%s%n", Taal.getString("SV"));
        }
        toonEindeSpel();
    }//einde start

    private void speelOfBewaar(Scanner s) throws SpelNaamInGebruikException {
        String naam;
        System.out.printf("%s%n", Taal.getString("qWantToPlay"));
        System.out.printf(Taal.getString("confirm"));
        String keuze = s.next();
        while (!"1".equals(keuze) && !"2".equals(keuze)) {
            System.out.printf("%s%n", Taal.getString("qWantToPlay"));
            System.out.printf(Taal.getString("confirm"));

            keuze = s.next();
        }
        if ("1".equals(keuze)) {
            System.out.printf("OK%n");
        } else {
            System.out.printf("%s%n", Taal.getString("qGiveName"));
            naam = s.next();
            dc.registreerSpel(naam, dc.getBord());
            System.out.printf("%S%n", Taal.getString("SV"));
            UC1Applicatie.kiesUC(); //einde use case, terug opnieuw kiezen

        }
    }

    private void geefCodepinnen() {
        List<Integer> pinnen = dc.geefCodePinnen();
        String codepinnen = String.format("%s%n", Taal.getString("overviewPeg"));

        for (int pin : pinnen) {
            codepinnen += Taal.getString(Integer.toString(pin));
            codepinnen += " ";
        }
        System.out.printf("%s%n", codepinnen);
    }

    private void geefRijenWeer(int[][] pogingen) {

        eenRij += String.format("%s%d%5s", Taal.getString("row"), teller + 1, "||");
        for (int kolom = 0; kolom < pogingen[teller].length; kolom++) {
            eenRij += String.format("%2s ", Taal.getString(Integer.toString(pogingen[teller][kolom]) + pogingen[teller][kolom]));
        }
        eenRij += String.format("%n");

        System.out.printf(eenRij);

    }

    private int[] geefCodePinnenIn(int[] poging, Scanner s) {
        String kleur;
        for (int i = 0; i < poging.length; i++) {
            System.out.printf("%s %d ", Taal.getString("giveP"), i + 1);
            kleur = s.next().trim();

            if (Arrays.asList(KLEUREN).contains(kleur)) {
                int tellerk = 0;
                for (String deKleur : KLEUREN) {
                    tellerk++;
                    if (kleur.equals(deKleur)) {
                        poging[i] = tellerk;
                        break;
                    }
                }
            } else {
                throw new LegePogingException("Ongeldige poging");
            }
        }
        return poging;
    }

    private void toonEindeSpel() {
        System.out.print(dc.geefEindOverzicht());
    }
}//einde klasse UC3App
