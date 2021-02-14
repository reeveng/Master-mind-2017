package domein;

import persistentie.MoeilijkHeidsGraadMapper;

/**
 * begin van de klasse moeilijkheidsgraadrepository
 *
 * @author reeve
 */
public class MoeilijkheidsgraadRepository {

    private final MoeilijkHeidsGraadMapper mapper;

    /**
     * Constructor van moeilijkheidsgraadrepository
     */
    public MoeilijkheidsgraadRepository() {
        mapper = new MoeilijkHeidsGraadMapper();
    }

    /**
     * geeft de juiste moeilijkheidsgraad weer
     *
     * @param naam
     * @return
     */
    public Moeilijkheidsgraad geefOpNaam(String naam) {
        Moeilijkheidsgraad graad = mapper.geefMoeilijkheidsgraadOpNaam(naam);
        if (graad.getNaam().equals(naam)) {
            return graad;
        }

        return null;
    }
}//einde klasse
