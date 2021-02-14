package domein;

import java.util.List;

/**
 * begin van de klasse Spel
 *
 * @author reeve
 */
public class Spel
{

    /*eigen attributen*/
    private final int[] teRadenCode;
    private String naam;
    private boolean gewonnen = false;

    /*klasse attributen*/
    private Spelbord spelbord;
    private final Moeilijkheidsgraad moeilijkheidsgraad;
    private final Speler speler;
    private int uitdagingnr;

    
    /**
     * Constructor van de klasse Spel
     *
     * @param teRadenCode
     * @param naam
     * @param moeilijkheidsgraad
     * @param speler
     * @param spelbord
     */
    public Spel(int[] teRadenCode, String naam, Moeilijkheidsgraad moeilijkheidsgraad, Speler speler, Spelbord spelbord)
    {
        this.teRadenCode = teRadenCode;
        this.naam = naam;
        this.moeilijkheidsgraad = moeilijkheidsgraad;
        this.speler = speler;
        setSpelbord(spelbord);
    }
    /**
     * Constructor van de klasse Spel
     *
     * @param teRadenCode
     * @param naam
     * @param moeilijkheidsgraad
     * @param speler
     */
    public Spel(int[] teRadenCode, String naam, Moeilijkheidsgraad moeilijkheidsgraad, Speler speler)
    {
        this.teRadenCode = teRadenCode;
        this.naam = naam;
        this.moeilijkheidsgraad = moeilijkheidsgraad;
        this.speler = speler;
        maakSpelbord(moeilijkheidsgraad);
    }

    /**
     * Constructor van de klasse Spel
     *
     * @param speler
     * @param moeilijkheidsgraad
     */
    public Spel(Speler speler, Moeilijkheidsgraad moeilijkheidsgraad)
    {
        this(moeilijkheidsgraad.geefGemaskeerdeCode(), "onbekend", moeilijkheidsgraad, speler);
       maakSpelbord(moeilijkheidsgraad);
    }

    /**
     * Constructor van de klasse Spel
     *
     * @param speler
     * @param moeilijkheidsgraad
     * @param teRadenCode
     */
    public Spel(Speler speler, Moeilijkheidsgraad moeilijkheidsgraad, int[] teRadenCode)
    {

        this(teRadenCode, "onbekend", moeilijkheidsgraad, speler);

    }

    /**
     *Constructor van de klasse Spel
     * @param speler
     * @param moeilijkheidsgraad
     * @param teRadenCode
     * @param uitdagingnr
     */
    public Spel(Speler speler, Moeilijkheidsgraad moeilijkheidsgraad, int[] teRadenCode, int uitdagingnr)
    {

        this(teRadenCode, "onbekend", moeilijkheidsgraad, speler);
        this.uitdagingnr=uitdagingnr;
    }
    
    /**
     *Constructor van de klasse Spel
     * @param s
     * @param mg
     * @param naam
     * @param teRadenCode
     * @param bord
     * @param uitdagingnr
     */
    public Spel(Speler s, Moeilijkheidsgraad mg, String naam,  int[] teRadenCode, Spelbord bord, int uitdagingnr){
        this(teRadenCode, naam,  mg, s, bord);
        this.uitdagingnr=uitdagingnr;
    }

    private void setSpelbord(Spelbord spelbord)
    {
        this.spelbord = spelbord;
    }

    /**
     * setter van het attribuut naam
     *
     * @param naam
     */
    public final void setNaam(String naam)
    {
        this.naam = naam;
    }

    /**
     * getter van het attribuut spelbord
     *
     * @return
     */
    public Spelbord getSpelbord()
    {
        return spelbord;
    }

    /**
     * getter van het attribuut TeRadenCode
     *
     * @return
     */
    public int[] getTeRadenCode()
    {
        return teRadenCode;
    }

    /**
     * geeft het aantal pogingen terug
     *
     * @return
     */
    public int geefAantalPogingen()
    {
        return spelbord.getAantalPogingen();
    }

    /**
     * geeft terug of de speler is gewonnen
     *
     * @return
     */
    public boolean isGewonnen()
    {
        return gewonnen;
    }

    private void maakSpelbord(Moeilijkheidsgraad moeilijkheidsgraad)
    {
        String graadnaam = moeilijkheidsgraad.getNaam();
        if (graadnaam.equals("moeilijk"))
        {
            setSpelbord(new Spelbord(5));
        } else
        {
            setSpelbord(new Spelbord(4));
        }
    }

    /**
     * getter van de moeilijkheidsgraad
     *
     * @return
     */
    public Moeilijkheidsgraad getMoeilijkheidsgraad()
    {
        return moeilijkheidsgraad;
    }

    /**
     * getter van het attribuut naam
     *
     * @return
     */
    public String getNaam()
    {
        return naam;
    }

    /**
     * getter van het attribuut speler
     *
     * @return
     */
    public Speler getSpeler()
    {
        return speler;
    }

    /**
     * geeft de codepinnen terug
     *
     * @return
     */
    public List<Integer> geefCodePinnen()
    {
        return spelbord.getCodepinnen();
    }

    /**
     * geeft het bord met de pogingen terug
     *
     * @return
     */
    public int[][] geefPoging()
    {
        return spelbord.getBord();
    }

    /**
     * registreer het aantal pogingen
     *
     * @param aantalPogingen
     */
    public final void setAantalPogingen(int aantalPogingen)
    {
        spelbord.setAantalPogingen(aantalPogingen);
    }

    /**
     *registreert de poging
     * @param poging
     */
    public void registreerPoging(int[] poging)
    {
        int[] controle = moeilijkheidsgraad.evalueerPoging(poging, teRadenCode);
        boolean allesJuist = true;
        for (int pin : controle)
        {
            if (pin != 8)
            {
                allesJuist = false;
                break;
            }
        }
        if (allesJuist)
        {
            gewonnen = true;
        }
        spelbord.registreerPoging(poging, controle);
    }

    /**
     * geeft terug of het einde van het spel bereikt is
     *
     * @return
     */
    public boolean isEindeSpel()
    {
        return (gewonnen || spelbord.getAantalPogingen() >= 12);
    }

    /**
     * valideert de poging / ( = checkt of het een geldige poging is)
     *
     * @param poging
     */
    public void valideerPoging(int[] poging)
    {
        moeilijkheidsgraad.valideerPoging(poging);
    }

    /**
     * getter van het attribuut bord
     *
     * @return
     */
    public int[][] getBord()
    {
        return spelbord.getBord();
    }

    public int getUitdagingnr()
    {
        return uitdagingnr;
    }
    
    
}//einde klasse Spel

