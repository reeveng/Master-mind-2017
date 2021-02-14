package domein;

import exceptions.LegePogingException;
import java.security.SecureRandom;

/**
 * begin van de klasse moeilijkheidsgraad
 *
 * @author reeve
 */
public class Moeilijkheidsgraad {

    private final String naam;
    private final int aantalPosities;

    /**
     * constructor moeilijkheidsgraad
     *
     * @param naam
     * @param aantalPosities
     */
    public Moeilijkheidsgraad(String naam, int aantalPosities) {
        this.naam = naam;
        this.aantalPosities = aantalPosities;

    }
    
    /**
     *stelt de naam van de moeilijkheidsgraad in en het aantal posities naar gelang de naam
     * @param naam
     */
    public Moeilijkheidsgraad(String naam){
        this.naam = naam;
        if (naam.equals("moeilijk")) {
            this.aantalPosities = 5;
        } else {
            this.aantalPosities = 4;
        }
    }
    
    /**
     *
     * @return
     */
    public int getAantalPosities(){
        return aantalPosities;
    }

    /**
     * getter voor de naam
     *
     * @return
     */
    public String getNaam() {
        return naam;
    }

    /**
     * een methode die de te raden code teruggeeft, deze wordt opgesteld aan de
     * hand van 1 array, deze array wordt opgevuld met random
     * getallen tussen 0 - 8/9, en daarna wordt aan elke waarde van de getallen in de array een kleur toegekend
     *
     * @return
     */
    public int[] geefGemaskeerdeCode() {
        SecureRandom random = new SecureRandom();
        int randomGetal;

        int[] code = new int[aantalPosities];

        int teller = 0;
        if (naam.equals("gemakkelijk")) {
            do {
                randomGetal = random.nextInt(8) + 1;
                boolean uniek = true;
                for (int i = 0; i <= teller; i++) {
                    if (code[i] == randomGetal) {
                        uniek = false;
                    }
                }
                if (uniek) {
                    code[teller] = randomGetal;
                    teller++;
                }
            } while (teller != aantalPosities); //einde lus gemakkelijk
        } else if (naam.equals("normaal")) {
            do {
                randomGetal = random.nextInt(8) + 1;
                code[teller] = randomGetal;
                teller++;
            } while (teller != aantalPosities); //einde lus normaal
        } else {
            int aantalNul = 0;
            do {
                randomGetal = random.nextInt(9);
                if (aantalNul <= 2 || randomGetal != 0) {
                    code[teller] = randomGetal;
                    teller++;
                    if (randomGetal == 0) {
                        aantalNul++;
                    }
                }

            } while (teller != aantalPosities); // einde lus moeilijk
        }

        return code;

    }

    /**
     * deze methode gaat na of de poging, een geldige poging is, indien een veld
     * leeg is bij normaal of gemakkelijk wordt een exception geworpen, indien
     * meer dan 2 pinnen leeg werden ingevuld wordt er een exception geworpen
     *
     * @param poging
     */
    public void valideerPoging(int[] poging) {
        if (naam.equals("gemakkelijk") || naam.equals("normaal")) {
            for (int pin : poging) {
                if (pin == 9 || pin == 0) {
                    throw new LegePogingException("Een of meerdere pinnen waren leeg");
                }
            }
        }
        if (naam.equals("moeilijk")) {
            int keerLeeg = 0;
            for (int pin : poging) {
                if (pin == 9 || pin == 0) {
                    keerLeeg++;
                }
                if (keerLeeg > 2) {
                    throw new LegePogingException("Meer dan twee pinnnen waren leeg");
                }
            }
        }
    }

    /**
     * deze methode vergelijkt de poging met de te raden code (gemaskeerdeCode)
     *
     * @param poging
     * @param teRadenCode
     * @return
     */
    public int[] evalueerPoging(int[] poging, int[] teRadenCode) {
        int[] uit = new int[aantalPosities];
        if (naam.equals("gemakkelijk")) {
            for (int i = 0; i < uit.length; i++) {  //als de pin overeen komt moet hij de rest niet meer checken voor dezelfde kleur
                if (poging[i] == (teRadenCode[i])) {
                    uit[i] = 8;
                } else { //als de pin niet overeen komt moeten de andere kleuren worden gechecked
                    boolean komtVoor = false;
                    for (int pin : teRadenCode) {
                        if (pin == (poging[i])) {
                            komtVoor = true;
                            break;
                        }
                    }
                    uit[i] = komtVoor ? 7 : 9;
                }
            }
        }
        if (naam.equals("normaal") || naam.equals("moeilijk")) {
            int aantalWit = 0, aantalZwart = 0;
            for (int i = 0; i < uit.length; i++) {
                if (poging[i] == teRadenCode[i]) {
                    aantalWit++;
                } else {

                    for (int pin : teRadenCode) {
                        if (pin == (poging[i])) {
                            aantalZwart++;
                            break;
                        }
                    }

                }
            }
            /*
            array opvulen:
            alle witte in het begin, gevolgd door de zwarte, andere blijven leeg
            kan zijn dat er nog fouten in staan hier onder
            of dat er nog iets is dat wat efficienter is
             */
            for (int i = 0; i < aantalWit; i++) {
                uit[i] = 8;
            }
            for (int i = 0; i < aantalZwart; i++) {
                uit[(i + aantalWit)] = 7;
            }
            for (int i = 0; i < (uit.length - (aantalWit + aantalZwart)); i++) {
                uit[(i + aantalWit + aantalZwart)] = 9;
            }
        }
        return uit;
    }
}
