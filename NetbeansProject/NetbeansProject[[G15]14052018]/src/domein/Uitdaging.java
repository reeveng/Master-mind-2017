package domein;

/**
 *  Klasse uitdaging
 * @author arthurpauwels
 */
public class Uitdaging {

    private final Speler speler;
    private final Speler tegenspeler;
    private final Moeilijkheidsgraad moeilijkheidsgraad;
    private final int[] code;
    private int id;

    /**
     * Constructor van de klasse Uitdaging
     *
     * @param speler
     * @param tegenspeler
     * @param moeilijkheidsgraad
     * @param code
     */
    public Uitdaging(Speler speler, Speler tegenspeler, Moeilijkheidsgraad moeilijkheidsgraad, int[] code) {
        this.speler = speler;
        this.tegenspeler = tegenspeler;
        this.moeilijkheidsgraad = moeilijkheidsgraad;
        this.code = code;
    }

    /**
     * Constructor van de klasse Uitdaging
     * @param speler
     * @param tegenspeler
     * @param moeilijkheidsgraad
     * @param code
     * @param id
     */
    public Uitdaging(Speler speler, Speler tegenspeler, Moeilijkheidsgraad moeilijkheidsgraad, int[] code, int id) {
        this(speler, tegenspeler, moeilijkheidsgraad, code);
        setId(id);

    }

    /**
     * Constructor van de klasse Uitdaging
     *
     * @param speler
     * @param tegenspeler
     * @param moeilijkheidsgraad
     */
    public Uitdaging(Speler speler, Speler tegenspeler, Moeilijkheidsgraad moeilijkheidsgraad) {
        this(speler, tegenspeler, moeilijkheidsgraad, moeilijkheidsgraad.geefGemaskeerdeCode());
    }

    /**
     * getter van het speler attribuut
     *
     * @return
     */
    public Speler getSpeler() {
        return speler;
    }

    /**
     * getter van het tegenspeler attribuut
     *
     * @return
     */
    public Speler getTegenspeler() {
        return tegenspeler;
    }

    /**
     * getter van het attribuut moeilijkheidsgraad
     *
     * @return
     */
    public Moeilijkheidsgraad getMoeilijkheidsgraad() {
        return moeilijkheidsgraad;
    }

    /**
     * getter van het attribuut code
     *
     * @return
     */
    public int[] getCode() {
        return code;
    }

    public int getId() {
        return id;
    }

    public final void setId(int id) {
        this.id = id;
    }

}//einde klasse
