package domein;

import exceptions.SpelersNaamInGebruikException;
import java.util.List;
import persistentie.SpelerMapper;

/**
 * begin van de klasse spelerrepository
 *
 * @author reeve
 */
public class SpelerRepository {

    private final SpelerMapper mapper;

    /**
     * constructor van spelerrepository
     */
    public SpelerRepository() {
        mapper = new SpelerMapper();
    }

    /**
     * checkt of een spelersnaam al bestaat in de DB
     *
     * @param spelersnaam
     * @return
     */
    public boolean bestaatSpeler(String spelersnaam) {
        return mapper.geefSpeler(spelersnaam) != null;
    }

    /**
     * voegt een speler toe aan de DB
     *
     * @param speler
     */
    public void voegToe(Speler speler) {
        if (bestaatSpeler(speler.getSpelersnaam())) {
            throw new SpelersNaamInGebruikException();
        }

        mapper.voegToe(speler);
    }

    /**
     * geeft een speler object terug
     *
     * @param spelersnaam
     * @param wachtwoord
     * @return
     */
    public Speler geefSpeler(String spelersnaam, String wachtwoord) {
        Speler s = mapper.geefSpeler(spelersnaam);
        if (s.getWachtwoord().equals(wachtwoord)) {
            return s;
        }

        return null;
    }

    /**
     * geeft een speler object terug, de speler die op dit moment aan het spelen
     * is
     *
     * @param naam
     * @return
     */
    public Speler geefSpelerOpNaam(String naam) {
        return mapper.geefSpeler(naam);
    }

    /**
     * geeft een lijst van spelers terug
     *
     * @return
     */
    public List<Speler> geefSpelers() {
        return mapper.geefSpelers();
    }

    /**
     * slaat de winst op na het spelen van een spel
     *
     * @param speler
     */
    public void slaWinstOp(Speler speler) {
        mapper.slaWinstOp(speler);
    }

    /**
     * geeft een speler array terug
     *
     * @param mg
     * @return
     */
    public Speler[] geefTegenspelersVoor(String mg) {
        Speler[] tegenspelers = mapper.geefTegenspelers(mg).toArray(new Speler[mapper.geefTegenspelers(mg).size()]);
        return tegenspelers;
    }
}