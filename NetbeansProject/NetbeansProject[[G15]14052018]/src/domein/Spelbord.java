package domein;

import java.util.ArrayList;
import java.util.List;

/**
 * begin van de klasse spelbord
 *
 * @author reeve
 */
public class Spelbord {

    private int[][] bord;
    List<Integer> codepinnen;
    private int aantalPogingen = 0;

    /**
     * Constructor van de klasse spelbord
     *
     * @param posities
     */
    public Spelbord(int posities) {

        setBord(new int[12][posities * 2]);  // controlepin
        codepinnen = new ArrayList<>();
        setCodepinnen(codepinnen);
    }

    /**
     * Constructor van de klasse spelbord
     *
     * @param bord
     */
    public Spelbord(int[][] bord) {
        this.bord = bord;
        codepinnen = new ArrayList<>();
        setCodepinnen(codepinnen);
    }

    private void setBord(int[][] bord) {
        this.bord = bord;
    }

    private void setCodepinnen(List<Integer> codepinnen) {
        this.codepinnen = codepinnen;

        for(int i = 0; i < 9; i++){
            codepinnen.add(i);
        }
        //codepinnen.add(1); //rood
        //codepinnen.add(2); //geel
        //codepinnen.add(3);//groen
        //codepinnen.add(4);//oranje
        //codepinnen.add(5);//bruin
        //codepinnen.add(6);//blauw
        //codepinnen.add(7);//zwart
        //codepinnen.add(8);//wit
        //codepinnen.add(9); //leeg

    }

    /**
     * getter van het attribuut bord
     *
     * @return
     */
    public int[][] getBord() {
        return bord;
    }

    /**
     * getter van het attribuut Codepinnen
     *
     * @return
     */
    public List<Integer> getCodepinnen() {
        return codepinnen;
    }

    /**
     * getter van het attribuut aantalpogingen
     *
     * @return
     */
    public int getAantalPogingen() {
        return aantalPogingen;
    }

    /**
     * verhoogt het aantal pogingen met 1
     *
     * @param verhoging
     */
    public void verhoogAantalPogingenMet(int verhoging) {
        aantalPogingen++;
    }

    public final void setAantalPogingen(int aantalPogingen)
    {
        this.aantalPogingen = aantalPogingen;
    }
    
    

    /**
     * registreert een poging, combineert de poging met de passende codopinnen
     *
     * @param poging
     * @param controle
     */
    public void registreerPoging(int[] poging, int[] controle) {

        int length = poging.length + controle.length;
        int[] result = new int[length];
        System.arraycopy(poging, 0, result, 0, poging.length);
        System.arraycopy(controle, 0, result, poging.length, controle.length);
        bord[aantalPogingen] = result;

//        for (int i = 0; i < bord[aantalPogingen].length; i++)
//        {
//            bord[aantalPogingen][i] = poging[i];
//            bord[aantalPogingen][i + poging.length] = controle[i];
//        }
        verhoogAantalPogingenMet(1);
    }
}// einde klasse Spelbord
