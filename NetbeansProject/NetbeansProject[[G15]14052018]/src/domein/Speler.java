package domein;

import exceptions.VerplichtVeldException;
import talen.Taal;

/**
 * begin van de klasse Speler
 *
 * @author reeve
 */
public class Speler {

    private final String spelersnaam;
    private final String wachtwoord;
    private int[] gewonnenMoeilijkheidsgraden;

    //Constructors
    /**
     * Constructor van de klasse speler
     *
     * @param spelersnaam
     * @param wachtwoord
     * @param gewonnenMoeilijkheidsgraden
     */
    public Speler(String spelersnaam, String wachtwoord, int[] gewonnenMoeilijkheidsgraden) {
        controleerSpelersnaam(spelersnaam);
        this.spelersnaam = spelersnaam;
        controleerWachtwoord(wachtwoord);
        this.wachtwoord = wachtwoord;
        setGewonnenMoeilijkheidsgraden(gewonnenMoeilijkheidsgraden);
    } //om een bestaande speler in het spel te steken

    /**
     * Constructor van de klasse speler
     *
     * @param spelersnaam
     * @param wachtwoord
     */
    public Speler(String spelersnaam, String wachtwoord) {
        this(spelersnaam, wachtwoord, new int[3]);
    } //om een nieuwe speler aan te maken

    /**
     *constructor voor een object van speler met als naam en wactwoord ""
     */
    public Speler()
    {
        this.spelersnaam="";
        this.wachtwoord="";
    }
    
    
    
    private void setGewonnenMoeilijkheidsgraden(int[] score) {
        this.gewonnenMoeilijkheidsgraden = score;
    }

    /**
     * verhoogt de moeilijkheidsgraad met 1
     *
     * @param mg
     */
    public void verhoogMoeilijkheidsgraadMetEen(Moeilijkheidsgraad mg) {
        switch (mg.getNaam()) {
            case "gemakkelijk":
                gewonnenMoeilijkheidsgraden[0]++;
                break;
            case "normaal":
                gewonnenMoeilijkheidsgraden[1]++;
                break;
            case "moeilijk":
                gewonnenMoeilijkheidsgraden[2]++;
                break;
            default:
                throw new IllegalArgumentException("geen moeilijkheidsgraad ingegeven");

        }
    }

    /**
     * getter van het attribuut spelersnaam
     *
     * @return
     */
    public String getSpelersnaam() {
        return spelersnaam;
    }

    /**
     * getter van het attribuut wachtwoord
     *
     * @return
     */
    public String getWachtwoord() {
        return wachtwoord;
    }

    /**
     * getter van het attribuut gewonnen moeilijkheidsgraden
     *
     * @return
     */
    public int[] getGewonnenMoeilijkheidsgraden() {
        return gewonnenMoeilijkheidsgraden;
    }

    private void controleerSpelersnaam(String spelersnaam) {
        if (spelersnaam.length() == 0) {
            throw new VerplichtVeldException("spelersnaam mag niet leeg zijn");
        }
    }

    private void controleerWachtwoord(String wachtwoord) {
        if (wachtwoord == null) {
            throw new VerplichtVeldException("wachtwoord moet ingevuld zijn");
        }
        if (wachtwoord.length() < 8) {
            throw new VerplichtVeldException("Wachtwoord te kort (is minder dan 6)");
        }
        if (!(wachtwoord.matches("\\d[A-Za-z]*\\d"))) {
            throw new VerplichtVeldException("Wachtwoord moet minstens een cijfer voor & achter bevatten");
        }
    }

    @Override
    public String toString() {
        return String.format("%s%n", getSpelersnaam());
    }

    /**
     * geeft per moeilijkheidsgraad, hoeveel keer de gebruiker gewonnen is terug
     *
     * @param mg
     * @return
     */
    public int geefAantalKeerGewonnenPerMoeilijkheidsgraad(int mg) {
        return getGewonnenMoeilijkheidsgraden()[mg];
    }

    /**
     * geeft een array van het aantal sterren per moeilijkheidsgraad terug
     *
     * @return
     */
    public int[] geefAantalSterren() {
        int[] sterren = new int[3];
        for (int i = 0; i < gewonnenMoeilijkheidsgraden.length; i++) {
            if (gewonnenMoeilijkheidsgraden[i] >= 250) {
                sterren[i] = 5;
            } else if (gewonnenMoeilijkheidsgraden[i] >= 100) {
                sterren[i] = 4;
            } else if (gewonnenMoeilijkheidsgraden[i] >= 50) {
                sterren[i] = 3;
            } else if (gewonnenMoeilijkheidsgraden[i] >= 20) {
                sterren[i] = 2;
            } else if (gewonnenMoeilijkheidsgraden[i] >= 10) {
                sterren[i] = 1;
            } else {
                sterren[i] = 0;
            }

        }
        return sterren;
    }

    /**
     * geeft het aantal nog te gaan overwinningen tot de volgende ster
     *
     * @param deGraad
     * @return
     */
    public String geefSterVerschil(int deGraad) {
        int aantal[] = geefAantalSterren();
        int aantalSter = aantal[deGraad];
        int verschil = 0;
        switch (aantalSter) {
            case 4:
                verschil = 250 - gewonnenMoeilijkheidsgraden[deGraad];
                break;
            case 3:
                verschil = 100 - gewonnenMoeilijkheidsgraden[deGraad];
                break;
            case 2:
                verschil = 50 - gewonnenMoeilijkheidsgraden[deGraad];
                break;
            case 1:
                verschil = 20 - gewonnenMoeilijkheidsgraden[deGraad];
                break;
            default:
                verschil = 10 - gewonnenMoeilijkheidsgraden[deGraad];
                break;

        }
        if (verschil < 0) {
            return "Gewonnen";
        } else {
            return String.format("%s%d%n", Taal.getString("nextstar"), verschil);
        }
    }
}//einde klasse Speler
