package cui;

import domein.DomeinController;
import exceptions.SpelerBestaatNietException;
import exceptions.SpelersNaamInGebruikException;
import exceptions.VerplichtVeldException;
import exceptions.WachtwoordException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import talen.Taal;

/**
 * begin van de klasse UC1Applicatie, een applicatie klasse om een gebruiker
 * zich te laten aanmelden of registreren
 *
 * @author reeve
 */
public class UC1Applicatie {

    private static DomeinController dc;

    /**
     * Constructor van de UC1Applicatie, roept de start van UC1Applicatie op en
     * stelt de domeinController in
     *
     * @param domeinController
     */
    public UC1Applicatie(DomeinController domeinController) {
        dc = domeinController;
        start();
    }

    private void start() {
        titelTekst();
        Taal.setR(kiesTaal());
        boolean blijvenHerhalenFlag = true;
        boolean blijvenHerhalenFlag2 = true;

        Scanner s = new Scanner(System.in);
        do {
            try {
                meldAan();
                blijvenHerhalenFlag2 = false;

            } catch (SpelerBestaatNietException spelerBestaatNietException) {
                System.out.printf("%s%n%n", Taal.getString("nameUnknown"));
                do {
                    try {
                        registreer();
                        blijvenHerhalenFlag = false;
                        blijvenHerhalenFlag2 = false;

                    } catch (SpelersNaamInGebruikException spelersNaamInGebruikException) {
                        System.out.printf("%s%n", Taal.getString("nameUsed"));

                    } catch (VerplichtVeldException verplichtVeldException) {
                        System.out.printf("%s%n%n", Taal.getString("passFalse"));
                        System.out.printf("%s%n%n", Taal.getString("passRules"));

                    }
                } while (blijvenHerhalenFlag == true);

            } catch (NullPointerException nullPointerException) {

                System.out.printf("%s%n%n", Taal.getString("passFalse"));

            }
        } while (blijvenHerhalenFlag2 == true);

        kiesUC();

    }

    /**
     * Vraagt aan de gebruiker om zijn gebruikersnaam en zijn wachtwoord. Indien
     * de gebruikersnaam juist is maar het wachtwoord niet, worden beide opnieuw
     * opgevraagd. Mocht de gebruiker nog niet over deze beschikken dan gaat hij
     * uit de methode.
     *
     */
    public void meldAan() {
        Scanner s = new Scanner(System.in);
        boolean herhaal = true;
        String wachtwoord;
        String naam;
        do {
            try {
                System.out.printf("%s %n", Taal.getString("userName"));
                String spelersnaam = s.next();
                System.out.printf("%s %n", Taal.getString("pass"));
                wachtwoord = s.next();
                dc.meldAan(spelersnaam, wachtwoord);
                herhaal = false;

            } catch (WachtwoordException e) {
                System.out.printf("%s%n", Taal.getString("passFalse"));
            }
        } while (herhaal);
        naam = dc.geefSpeler();
        System.out.printf("%s%n", naam);
    }

    /**
     * Vraagt aan de gebruiker om zich te registeren. Vraagt een nieuwe
     * gebruikersnaam, een wachtwoord en om hetzelfde wachtwoord te herhalen.
     */
    public void registreer() {
        Scanner s = new Scanner(System.in);

        System.out.printf("%s %n", Taal.getString("userName"));
        String spelersnaam = s.next();
        System.out.printf("%s %n", Taal.getString("pass"));
        String wachtwoord = s.next();
        System.out.printf("%s %n", Taal.getString("confirmPass"));
        String bevestigingwachtwoord = s.next();

        dc.registreer(spelersnaam, wachtwoord, bevestigingwachtwoord);
    }

    /**
     * geeft de gebruiker de keuze om een taal te kiezen (nederlands, frans of
     * engels)
     *
     * @return
     */
    public static ResourceBundle kiesTaal() {
        Scanner s = new Scanner(System.in);
        String lang;
        String country;

        System.out.printf("Kies uw taal:%nChoose your language:%nChoisissez votre langue:%n1 voor Nederlands%n2 pour Français%n3 for English%n ");
        String keuze = s.nextLine();
        while (!(keuze.equals("1") || (keuze.equals("2") || (keuze.equals("3"))))) {
            System.out.printf("Kies uw taal:%nChoose your language:%nChoisissez votre langue:%n1 voor Nederlands%n2 pour Français%n3 for English%n ");
            keuze = s.nextLine();
        }
        switch (keuze) {
            case "2":
                lang = "fr";
                country = "BE";
                break;
            case "3":
                lang = "en";
                country = "US";
                break;
            default:
                lang = "nl";
                country = "BE";
                break;
        }

        Locale l = new Locale(lang, country);
        ResourceBundle r = ResourceBundle.getBundle("talen/Bundle", l);
        System.out.printf("%s%n", r.getString("setLan"));

        return r;
    }

    /**
     * Vraagt aan de gebruiker welke usecase hij wil starten (de verschillende
     * opties: startMastermind, Load Game,...). Opent de gekozen UC naar gelang
     * de keuze van de gebruiker.
     */
    public static void kiesUC() {
        Scanner s = new Scanner(System.in);
        System.out.printf("%s%n", Taal.getString("chooseUC"));
        System.out.printf("%s%n", Taal.getString("menuUC"));
//    menuUC=|1 Start Mastermind| |2 Load Game| |3 Challange| |4 Accept Challange| |5 Show scores| |6 Exit|
        String keuze = s.next();
        while (!("1".equals(keuze) || "2".equals(keuze) || "3".equals(keuze) || "4".equals(keuze) || "5".equals(keuze) || "6".equals(keuze))) {
            System.out.printf("%s%n", Taal.getString("menuUC"));
            keuze = s.next();
        }

        switch (Integer.parseInt(keuze)) {
            case 1:
                UC2Applicatie a2 = new UC2Applicatie(dc);
                break;
            case 2:
                UC4Applicatie a4 = new UC4Applicatie(dc);
                break;
            case 3:
                UC5Applicatie a5 = new UC5Applicatie(dc);
                break;
            case 4:
                UC6Applicatie a6 = new UC6Applicatie(dc);
                break;
            case 5:
                UC7Applicatie a7 = new UC7Applicatie(dc);
                break;
            case 6:
                System.exit(0);
                break;
        }

    }

    private void titelTekst() {
        System.out.println("  __  __              _                          _             _ ");
        System.out.println(" |  \\/  |            | |                        (_)           | |");
        System.out.println(" | \\  / |  __ _  ___ | |_  ___  _ __  _ __ ___   _  _ __    __| |");
        System.out.println(" | |\\/| | / _` |/ __|| __|/ _ \\| '__|| '_ ` _ \\ | || '_ \\  / _` |");
        System.out.println(" | |  | || (_| |\\__ \\| |_|  __/| |   | | | | | || || | | || (_| |");
        System.out.println(" |_|  |_| \\__,_||___/ \\__|\\___||_|   |_| |_| |_||_||_| |_| \\__,_|");
        System.out.println("");
        System.out.println("");
    }
}
