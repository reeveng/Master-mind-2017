package domein;

import java.util.List;
import persistentie.SpelMapper;

/**
 * begin van de klasse SpelRepository
 *
 * @author reeve
 */
public class SpelRepository {

    private final SpelMapper mapper;

    /**
     * Constructor van de klasse SpelRepository
     */
    public SpelRepository() {
        mapper = new SpelMapper();
    }

    /**
     * checkt of de naam al bestaat/ ( = checkt of de naam al wordt gebruikt
     * door een andere gebruiker)
     *
     * @param naam
     * @param spelersnaam
     * @return
     */
    public boolean bestaatNaam(String naam, String spelersnaam) {
        boolean bestaat = false;
        List<String> spelletjes = mapper.geefSpelletjesOpNaam(spelersnaam);
        if (spelletjes.contains(naam)) {
            bestaat = true;
        }

        return bestaat;
    }

    /**
     * voegt een spel toe aan de databank
     *
     * @param spel
     * @param bord
     */
    public void voegToe(Spel spel, int bord[][]) {
        mapper.voegToe(spel, bord);
    }

    /**
     * geeft een spel-object terug
     *
     * @param naam
     * @return
     */
    public Spel geefSpel(String naam) {
        return mapper.geefSpel(naam);
    }

    /**
     * geeft een overzicht van de opgeslagen spelletjes
     *
     * @param spelersnaam
     * @return
     */
    public List<String> geefOverzichtSpelletjes(String spelersnaam) {
        return mapper.geefSpelletjes(spelersnaam);
    }

    /**
     * verwijdert een spel uit de databank
     *
     * @param naam
     * @param naamSpel
     */
    public void verwijderSpel(String naam, String naamSpel) {
        mapper.verwijderSpel(naam, naamSpel);
    }
}
