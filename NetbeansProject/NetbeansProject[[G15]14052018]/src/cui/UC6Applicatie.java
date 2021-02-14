package cui;

import domein.DomeinController;
import exceptions.UitdagingNietGevondenException;
import java.util.InputMismatchException;
import java.util.Scanner;
import talen.Taal;

/**
 * begin van de klasse UC5Applicatie, een applicatie klasse voor het accepteren
 * van uitdagingen
 *
 * @author arthurpauwels
 */
public class UC6Applicatie {

    private final DomeinController dc;

    /**
     * methode om deze UC te starten, deze stelt de dc in en roept dan de start
     * methode op
     *
     * @param dc
     */
    public UC6Applicatie(DomeinController dc) {
        this.dc = dc;
        start();
    }

    private void start() {
        Scanner s = new Scanner(System.in);
        try {
            toonUitdagingen();
            kiesUitdaging(s);
            startUitdaging();
        } catch (UitdagingNietGevondenException e) {
            System.out.println(Taal.getString("geenUitdagingenTeAccepteren"));
            UC1Applicatie.kiesUC();
        }
    }

    private void toonUitdagingen() throws UitdagingNietGevondenException {
        System.out.println(dc.geefUitdagingenTeAccepteren());
    }

    private void kiesUitdaging(Scanner s) {
        System.out.println(Taal.getString("qUitdagingID"));
        boolean ok = false;
        do {
            try {
                int id = s.nextInt();
                dc.stelUitdagingInVanID(id);
                ok = true;
            } catch (InputMismatchException e) {
                System.out.println(Taal.getString("notANumber"));
                System.out.println(Taal.getString("tryInputAgain"));
                s.next();
            } catch (UitdagingNietGevondenException e) {
                System.out.println(Taal.getString("challengeNotFound"));
                System.out.println(Taal.getString("tryInputAgain"));
                s.next();
            }
        } while (!ok);
    }

    private void startUitdaging() {
        dc.startUitdagingAlspel();
        dc.geefOverzichtSpel();
        geefSpelbord();
        UC3Applicatie a3 = new UC3Applicatie(dc);
    }

    private void geefSpelbord() {
        System.out.printf(dc.geefSpelbord());
    }

}//einde kalsse
