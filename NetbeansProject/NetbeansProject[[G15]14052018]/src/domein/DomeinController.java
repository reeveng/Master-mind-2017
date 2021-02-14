package domein;

import exceptions.SpelNaamInGebruikException;
import exceptions.SpelerBestaatNietException;
import exceptions.SpelersNaamInGebruikException;
import exceptions.UitdagingNietGevondenException;
import exceptions.VerplichtVeldException;
import exceptions.WachtwoordException;

import java.util.List;
import java.util.Scanner;
import talen.Taal;

/**
 * begin van de klasse DomeinController
 *
 * @author reeve
 */
public class DomeinController {

    private final MoeilijkheidsgraadRepository moeilijkheidsgraadRepository;

    private Speler speler;
    private final SpelerRepository spelerRepository;
    private final SpelRepository spelRepository;
    private final UitdagingRepository uitdagingRepo;
    private Spel spel;
    private Uitdaging uitdaging;

    /**
     * constructor van de DomeinController
     */
    public DomeinController() {
        spelerRepository = new SpelerRepository();
        spelRepository = new SpelRepository();
        moeilijkheidsgraadRepository = new MoeilijkheidsgraadRepository();
        uitdagingRepo = new UitdagingRepository();
    }

    /**
     * een methode die de gebruiker toelaat zich aan te melden, via een al
     * bestaande naam met wachtwoord, als de speler zich probeert aan te melden
     * met een niet bestaande naam in de DB wordt een exception geworpen
     *
     * @param spelersnaam
     * @param wachtwoord
     */
    public void meldAan(String spelersnaam, String wachtwoord) { //als de speler in spelerRepository zit en het wachtwoord overeenkomt kan hij zich aanmelden

        if (spelerRepository.bestaatSpeler(spelersnaam) == false) {
            throw new SpelerBestaatNietException("registreer");
        } else {
            Speler gevondenSpeler = spelerRepository.geefSpeler(spelersnaam, wachtwoord);
            if (gevondenSpeler != null) {
                setSpeler(gevondenSpeler);
            } else {
                throw new WachtwoordException(Taal.getString("passFalse"));
            }

        }
    }

    /**
     *stelt de speler als een lege speler in
     */
    public void logUit() {
        setSpeler(new Speler());
    }

    /**
     * een methode die de gebruiker toelaat zich te registreren met een nieuwe
     * gebruikersnaam (spelersnaam) en wachtwoord, indien het de gebruikersnaam
     * al in gebruik is wordt en het wachtwoord niet klopt met de wachtwoord
     * eisen, wordt een exception geworpen
     *
     * @param spelersnaam
     * @param wachtwoord
     * @param bevestigingWachtwoord
     */
    public void registreer(String spelersnaam, String wachtwoord, String bevestigingWachtwoord) {
        if (spelerRepository.bestaatSpeler(spelersnaam) == true) {
            throw new SpelersNaamInGebruikException("probeer opnieuw");
        } else {
            if (!wachtwoord.equals(bevestigingWachtwoord)) { //als het wachtwoord niet gelijk is aan het bevestigings wachtwoord dan wordt er een iae gegeven
                throw new VerplichtVeldException("Wachtwoorden komen niet overeen.");
            }

            Speler nieuwSpeler = new Speler(spelersnaam, wachtwoord); //anders wordt de speler aangemaakt
            spelerRepository.voegToe(nieuwSpeler);
            setSpeler(nieuwSpeler);
        }
    }

    /**
     * geeft een de gebruikersnaam (spelersnaam) terug
     *
     * @return
     */
    public String geefSpeler() {
        return speler.getSpelersnaam();
    }

    /**
     * geeft een lijst van Speler objecten terug
     *
     * @return
     */
    public String geefSpelers() {
        List<Speler> spelers = spelerRepository.geefSpelers();
        int teller = 1;
        String output = "";
        for (Speler speler : spelers) {
            output += String.format("%s %s", teller, speler);
            teller++;
        }
        return output;
    }

    private void setSpeler(Speler speler) {
        this.speler = speler;
    }

    /**
     * geeft een overzicht terug
     *
     * @return
     */
    public int[] geefOverzichtSpel() {

        return speler.getGewonnenMoeilijkheidsgraden();
    }

    /**
     * geeft de gebruiker de keuze om een spel te starten met 1 van de 3
     * moeilijkheidsgraden, als de gebruiker een ongeldige moeilijkheidsgraad
     * kiest wordt een exception geworpen
     *
     * @param keuze
     */
    public void startSpel(int keuze) {
        String naam = "";
        if (keuze == 1 || keuze == 2 || keuze == 3) {
            switch (keuze) {
                case 1:
                    naam = "gemakkelijk";
                    break;
                case 2:
                    naam = "normaal";
                    break;
                default:
                    naam = "moeilijk";
                    break;
            }

            Moeilijkheidsgraad graad;
            graad = moeilijkheidsgraadRepository.geefOpNaam(naam);

            setSpel(new Spel(speler, graad));
        } else {
            throw new VerplichtVeldException("moeilijkheidsgraad ongeldig");
        }
    }

    private void setSpel(Spel spel) {
        this.spel = spel;
    }

    /**
     * geeft een spelbord terug
     *
     * @return
     */
    public String geefSpelbord() {
        Spelbord spelbord = spel.getSpelbord();
        int[][] guess = spelbord.getBord();
        String bord;
        bord = String.format("%16s%50s%n", Taal.getString("positions"), Taal.getString("controlpin"));
        for (int[] gues : guess) {
            for (int col = 0; col < gues.length; col++) {
                String s = String.format(Taal.getString(Integer.toString(gues[col]) + Integer.toString(gues[col])));
                bord += String.format("%s      ", s);
            }
            bord += String.format("%n");
        }
        bord += String.format(getTeRadenCode());
        return bord;
    }

    /**
     * geeft de te raden code terug
     *
     * @return
     */
    public String getTeRadenCode() {
        int[] code = spel.getTeRadenCode();
        String teRadenCode = "";
        for (int deCode : code) {
            teRadenCode += String.format("%s", Taal.getString(Integer.toString(deCode)) + " "); //String.valueOf

        }

        return String.format("%s %s%n", Taal.getString("codetoguess"), teRadenCode);
    }

    /**
     * geeft een lijst van codepinnen terug
     *
     * @return
     */
    public List<Integer> geefCodePinnen() {
        return spel.geefCodePinnen();
    }

    /**
     * geeft de pogingen terug
     *
     * @return
     */
    public int[][] geefPoging() {
        return spel.geefPoging();
    }

    /**
     *geeft tegenspelers als een array van Speler objecten
     * @param gekozenMG
     * @return
     */
    public String[] geefTegenspelersArray(String gekozenMG) {
        Speler[] tegenspelers = spelerRepository.geefTegenspelersVoor(gekozenMG);
        String[] uit = new String[tegenspelers.length];
        for (int i = 0; i < uit.length; i++) {
            uit[i] = tegenspelers[i].getSpelersnaam();
        }
        return uit;
    }

    /**
     * geeft een lijst van mogelijke tegenspelers weer
     *
     * @param gekozenMG
     * @return
     */
    public String geefTegenspelers(int gekozenMG) {
        Scanner s = new Scanner(System.in);

        System.out.printf("%s%n", Taal.getString("qChoosePlayerToChallange"));
        Speler[] tegenspelers = spelerRepository.geefTegenspelersVoor(decodeerMoeilijkheidsgraad(gekozenMG));
        
        if (tegenspelers.length <= 0) {
            throw new IllegalArgumentException("geen speler gevonden voor die moeilijkheidsgraad");
        }
        String lijstSpelers = String.format("%15s %15s%n", Taal.getString("player"), Taal.getString("LT"));
        for (Speler tegenspeler : tegenspelers) {
            lijstSpelers += String.format("%15s %15d%n", tegenspeler.getSpelersnaam(), tegenspeler.geefAantalKeerGewonnenPerMoeilijkheidsgraad((gekozenMG - 1)));
        }

        System.out.print(lijstSpelers);
        boolean gevonden = false;
        String gekozenSpeler = s.next();
        for (Speler eenSpeler : tegenspelers) {
            if (gekozenSpeler.equals(eenSpeler.getSpelersnaam())) {
                gevonden = true;
                break;
            }

        }
        while (!gevonden) {
            System.out.printf("%s%n", Taal.getString("nameUsed"));
            gekozenSpeler = s.next();
            for (Speler eenSpeler : tegenspelers) {
                if (gekozenSpeler.equals(eenSpeler.getSpelersnaam())) {
                    gevonden = true;
                    break;
                }

            }
        }
        return gekozenSpeler;
    }

    /**
     * decodeert de moeilijkheidsgraad
     *
     * @param moeilijkheidsgraad
     * @return
     */
    public String decodeerMoeilijkheidsgraad(int moeilijkheidsgraad) {
        String mg;
        switch (moeilijkheidsgraad) {
            case 1:
                mg = "gemakkelijk";
                break;
            case 2:
                mg = "normaal";
                break;
            default:
                mg = "moeilijk";
                break;
        }
        return mg;
    }

    /**
     * valideert en registreert een poging
     *
     * @param poging
     */
    public void speelWedstrijd(int[] poging) {
        spel.valideerPoging(poging);
        spel.registreerPoging(poging);

    }

    /**
     * geeft het aantal sterren terug
     *
     * @return
     */
    public String geefAantalSterren() {
        int graad = this.geefGraad(spel.getMoeilijkheidsgraad());
        int[] sterren = speler.geefAantalSterren();
        String resul = String.format("%s %15s%n", Taal.getString("L"), Taal.getString("stars"));
        String[] graden
                = {
                    Taal.getString("L1"), Taal.getString("L2"), Taal.getString("L3")
                };

        resul += String.format("%s %20d%n", graden[graad], sterren[graad]);

        return resul;
    }

    /**
     * geeft het aantal keer winnen per moeilijkheidsgraad tot volgende ster
     * terug
     *
     * @return
     */
    public String geefVerschil() {
        int graad = this.geefGraad(spel.getMoeilijkheidsgraad());
        return speler.geefSterVerschil(graad);
    }

    /**
     * geeft terug of het einde van het spel al dan niet bereikt is
     *
     * @return
     */
    public boolean isEindeSpel() {
        return spel.isEindeSpel();
    }

    /**
     * geeft terug of de gebruiker gewonnen heeft of niet
     *
     * @return
     */
    public boolean isGewonnen() {
        return spel.isGewonnen();
    }

    /**
     * registreert/verhoogt de winst per moeilijkheidsgraad
     */
    public void registreerWinst() {

        speler.verhoogMoeilijkheidsgraadMetEen(spel.getMoeilijkheidsgraad());
        spelerRepository.slaWinstOp(speler);

    }

    /**
     * checkt of een spel in de databank kan opgeslagen worden, indien dit
     * mogelijk is voegt de methode dit spel toe, als de spelersnaam al in
     * gebruik is wordt een exception geworpen
     *
     * @param naam
     * @param bord
     * @throws exceptions.SpelNaamInGebruikException
     */
    public void registreerSpel(String naam, int bord[][]) throws SpelNaamInGebruikException, VerplichtVeldException {
        if (naam.trim() == null && naam.trim().equals("")) {
            throw new VerplichtVeldException("nameUsed");
        } else {
            if (spelRepository.bestaatNaam(naam, speler.getSpelersnaam()) == false) {
                spel.setNaam(naam);
                spelRepository.voegToe(spel, bord);
            } else {
                throw new SpelNaamInGebruikException("spelnaam in gebruik");
            }

        }
    }

    /**
     * geeft het aantal pogingen terug
     *
     * @return
     */
    public String getAantalPogingen() {
        return String.format("%s %d", Taal.getString("NOG"), spel.geefAantalPogingen());
    }

    /**
     * geeft een bord terug
     *
     * @return
     */
    public int[][] getBord() {
        return spel.getBord();
    }

    /**
     * geeft een Speler object terug van de Speler die momenteel aan het spelen
     * is
     *
     * @param naam
     * @return
     */
    public Speler geefSpeler(String naam) {
        return spelerRepository.geefSpelerOpNaam(naam);
    }

    /**
     * geeft de moeilijkheidsgraad terug
     *
     * @param mg
     * @return
     */
    public int geefGraad(Moeilijkheidsgraad mg) {
        int graad;
        switch (mg.getNaam()) {
            case "gemakkelijk":
                graad = 0;
                break;
            case "normaal":
                graad = 1;
                break;
            default:
                graad = 2;
                break;
        }

        return graad;
    }

    /**
     * set het gevraagde spel om verder te spelen
     *
     * @param naam
     */
    public void laadSpel(String naam) {
        Spel hetSpel = spelRepository.geefSpel(naam);
        setSpel(hetSpel);
    }

    /**
     * geeft een overzicht van het aantal gespeelde spelletjes weer
     *
     * @return
     */
    public List<String> geefOverzichtSpelletjes() {
        return spelRepository.geefOverzichtSpelletjes(geefSpeler());
    }

    /**
     * checkt of de/een naam al bestaat
     *
     * @param naam
     * @return
     */
    public boolean bestaatNaam(String naam) {
        return spelRepository.bestaatNaam(naam, speler.getSpelersnaam());
    }

    /**
     * verwijdert een gespeeld spel
     */
    public void verwijderSpel() {
        spelRepository.verwijderSpel(speler.getSpelersnaam(), spel.getNaam());
    }

    /**
     * start een uitdaging met een bepaalde moeilijkheidsgraad en tegenspeler
     *
     * @param mg
     * @param tegenspeler
     */
    public void startUitdaging(int mg, String tegenspeler) {
        setUitdaging(
                new Uitdaging(
                        speler,
                        spelerRepository.geefSpelerOpNaam(tegenspeler),
                        moeilijkheidsgraadRepository.geefOpNaam(decodeerMoeilijkheidsgraad(mg)))
        );
    }

    /**
     * @param aantalPogingen
     */
    public final void setAantalPogingen(int aantalPogingen) {
        spel.setAantalPogingen(aantalPogingen);
    }

    private void setUitdaging(Uitdaging uitdaging) {
        this.uitdaging = uitdaging;
    }

    /**
     * start de uitdaging als een spel
     */
    public void startUitdagingAlspel() {
        this.spel = new Spel(speler, this.uitdaging.getMoeilijkheidsgraad(), this.uitdaging.getCode(), this.uitdaging.getId());
    }

    /**
     * geeft een overzicht weer aan het einde van een spel
     *
     * @return
     */
    public String geefEindOverzicht() {
        String result = "";
        result += String.format("%s%n", getTeRadenCode());
        result += String.format("%s%n", getAantalPogingen());
        result += String.format("%s%n", geefAantalSterren());
        result += String.format("%s%n", geefVerschil());

        return result;
    }

    /**
     *voegt een uitdaging toe aan de repository
     */
    public void registreerUitdaging() {
        uitdagingRepo.voegUitdagingToe(uitdaging);
    }

    /**
     *update de score in de database
     */
    public void updateScore() {
//         int getal= spel.getUitdagingnr();
//         int getal2= uitdaging.getId();
//            if(getal==getal2){    
        uitdagingRepo.updateScore(spel.getUitdagingnr(), spel.geefAantalPogingen(), speler); //}

    }

    /**
     * stelt de uitdaging in via zijn ID
     */
    public void stelUitdagingIdIn() {
        int nummer = uitdagingRepo.geefLaatsteNummerUitdaging(speler);
        uitdaging.setId(nummer);

    }

    /**
     * haalt een uitdaging op uit de DB via het ID en de naam van de speler
     * @param id
     * @throws UitdagingNietGevondenException
     */
    public void stelUitdagingInVanID(int id) throws UitdagingNietGevondenException {
        uitdaging = uitdagingRepo.geefUitdagingOpNaamTegenSpelerEnID(speler.getSpelersnaam(), id);
    }

    /**
     * geeft alle uitdagingen terug die nog niet geaccepteerd zijn
     * @return
     * @throws UitdagingNietGevondenException
     */
    public String geefUitdagingenTeAccepteren() throws UitdagingNietGevondenException {
        Uitdaging[] uitdagingen = uitdagingRepo.geefUitdagingenTeAccepteren(speler.getSpelersnaam());
        String uit = "";
        if (uitdagingen.length <= 0) {
            throw new UitdagingNietGevondenException();
        }
//        String uit = String.format("%4s%15s%20s%10s%n", Taal.getString("id"), Taal.getString("tegenspeler"), Taal.getString("L"), Taal.getString("LT"));
        for (Uitdaging dezeuitdaging : uitdagingen) {

            uit += String.format("%d%15s%20s%10s%n", dezeuitdaging.getId(), dezeuitdaging.getSpeler().getSpelersnaam(), dezeuitdaging.getMoeilijkheidsgraad().getNaam(), geefGraad(dezeuitdaging.getMoeilijkheidsgraad()));
        }

        return uit;
    }

    /**
     * haalt de de klassementen op en maakt ze klaar om geprint te worden
     * @return
     */
    public String toonKlassement() {
        String uit = "";
        uit += String.format("%s:%n", Taal.getString("gemakkelijk")) + maakKlassementVoorMG("gemakkelijk") + String.format("%n");
        uit += String.format("%s:%n", Taal.getString("normaal")) + maakKlassementVoorMG("normaal") + String.format("%n");
        uit += String.format("%s:%n", Taal.getString("moeilijk")) + maakKlassementVoorMG("moeilijk") + String.format("%n");
        return uit;
    }

    private String maakKlassementVoorMG(String mg) {
        String[][] klassement = uitdagingRepo.toonKlassementVoorMG(mg);

        if (klassement[0].length <= 0) {
            return String.format("%s%n", Taal.getString("nogGeenKlassementVoorMG"));
        }

        String uit = "";
        uit += String.format("%20s%20s%n", Taal.getString("speler"), Taal.getString("score"));

        for (int i = klassement[0].length - 1; i >= 0; i--) {
            uit += String.format("%20s%20s%n", klassement[0][i], klassement[1][i]);
        }
        return uit;
    }

    /**
     * geeft een klassement terug per mg als array van strings
     * @param mg
     * @return
     */
    public String[] geefKlassementStringArray(String mg) {
        String[][] klassement = uitdagingRepo.toonKlassementVoorMG(mg);
        String[] uit = new String[klassement[0].length];
        for (int i = 0; i < uit.length; i++) {
            uit[i] = String.format("%s%n%s", klassement[0][i], klassement[1][i]);
        }
        return uit;
    }
}//einde klasse domeincontroller
