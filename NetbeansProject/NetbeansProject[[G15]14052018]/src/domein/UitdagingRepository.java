package domein;

import exceptions.UitdagingNietGevondenException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import persistentie.UitdagingMapper;

/**
 * begin van de klasse UitdagingRepository
 *
 * @author reeve
 */
public class UitdagingRepository {

    private final UitdagingMapper mapper;

    /**
     * Constructor van de klasse UitdagingRepository
     */
    public UitdagingRepository() {
        mapper = new UitdagingMapper();
    }

    /**
     * voegt een uitdaging toe aan de databank
     * @param uitdaging
     */
    public void voegUitdagingToe(Uitdaging uitdaging) {
        mapper.voegToe(uitdaging);
    }

    /**
     * update de score van een bepaalde speler in de databank
     * @param uitdagingid
     * @param score
     * @param speler
     */
    public void updateScore(int uitdagingid, int score, Speler speler) {
        mapper.updateScore(uitdagingid, score, speler);
    }

    /**
     * geeft het nummer terug van de laatste uitdaging dat we in de databank hebben gestoken
     * @param speler
     * @return
     */
    public int geefLaatsteNummerUitdaging(Speler speler) {
        return mapper.geefLaatsteNummerUitdaging(speler);
    }

    /**
     * zoekt in de databank naar een uitdaging waarvan de naam van de tegenspeler gekend is
     * @param spelersnaam
     * @param id
     * @return
     * @throws UitdagingNietGevondenException
     */
    public Uitdaging geefUitdagingOpNaamTegenSpelerEnID(String spelersnaam, int id) throws UitdagingNietGevondenException {
        return mapper.geefUitdagingOpNaamTegenSpelerEnID(spelersnaam, id);
    }

    /**
     *geeft alle uitdagingen die nog moeten geaccepteerd worden door een speler
     * @param spelersnaam
     * @return
     */
    public Uitdaging[] geefUitdagingenTeAccepteren(String spelersnaam) {
        return mapper.geefUitdagingenTeAccepteren(spelersnaam);
    }

    //deze methode moet je per mg oproepen

    /**
     * maakt een klassement voor een bepaalde moeilijkheidsgraad, deze maakt gebruik van een zelfgemaakte sorteer methode.
     * @param mg
     * @return
     */
    public String[][] toonKlassementVoorMG(String mg) {
        String[][] klassementSpelers = mapper.geefKlassementSpelers(mg);
        String[][] klassementTegenSpelers = mapper.geefKlassementTegenSpelers(mg);
        String[][] klassement = combineerKlassementen(klassementSpelers, klassementTegenSpelers);
        return sorteerKlassement(klassement);

    }

    private String[][] combineerKlassementen(String[][] klassementSpelers, String[][] klassementTegenSpelers) {
        
        List<String> klassementNamen = Arrays.asList(klassementSpelers[0]);
        List<Integer> klassementScore = new ArrayList<>();
        ArrayList<String> klassementUitNamen = new ArrayList();
        klassementUitNamen.addAll(klassementNamen);
        ArrayList<Integer> klassementUitScore = new ArrayList<>();
        
        for(String score : klassementSpelers[1]){
            klassementScore.add(Integer.parseInt(score));
            klassementUitScore.add(Integer.parseInt(score));
        }
        for (int i = 0; i < klassementTegenSpelers[0].length; i++) {
            boolean naamGevonden = false;
            int j = 0;
            do {
                
                String naamSpeler = klassementNamen.get(j);
                String naamTegenspeler = klassementTegenSpelers[0][i];
                if (naamSpeler.equals(naamTegenspeler)) {
                    klassementUitScore.set(j, klassementScore.get(j) + Integer.parseInt(klassementTegenSpelers[1][i]));
                    naamGevonden = true;
                }
                j++;
            } while (!naamGevonden && j < klassementNamen.size());
            if (!naamGevonden){
                klassementUitNamen.add(klassementTegenSpelers[0][i]);
                klassementUitScore.add(Integer.parseInt(klassementTegenSpelers[1][i]));
            }
        }
        
        String[] scoreString = new String[klassementUitScore.size()];
        for (int i = 0; i<scoreString.length; i++){
            scoreString[i] = Integer.toString(klassementUitScore.get(i));
        }
        
        return new String[][]{klassementUitNamen.toArray(new String[klassementUitNamen.size()]),scoreString};
    }
    
    private String[][] sorteerKlassement(String[][] klassement){
        return quickSortKlassement(klassement, 0, klassement[0].length - 1);
    }
    
    private String[][] quickSortKlassement(String[][] klassement, int begin, int einde){
        if (begin+5 > einde){
            klassement = cardSortBisKlassement(klassement, begin, einde);
        } else {
            int midden = begin+einde/2;
            if(Integer.parseInt(klassement[1][midden]) > Integer.parseInt(klassement[1][begin])) klassement = wisselKlassement(klassement ,midden, begin);
            if(Integer.parseInt(klassement[1][einde]) > Integer.parseInt(klassement[1][begin])) klassement = wisselKlassement(klassement, einde, begin);
            if(Integer.parseInt(klassement[1][einde]) > Integer.parseInt(klassement[1][midden])) klassement = wisselKlassement(klassement, einde, midden);  
            int spil = Integer.parseInt(klassement[1][midden]);
            klassement = wisselKlassement(klassement, midden, einde-1);
            int links = begin+1;
            int rechts = einde-2;
            while(links <= rechts){
                while (Integer.parseInt(klassement[1][links]) > spil) links++;
                while (Integer.parseInt(klassement[1][rechts]) < spil) rechts--;
                if (links <= rechts){
                    klassement = wisselKlassement(klassement, links, rechts);
                    links++;
                    rechts--;
                }
            }
            klassement = wisselKlassement(klassement, links, spil);
            klassement = quickSortKlassement(klassement, begin, links-1);
            klassement = quickSortKlassement(klassement, links+1, einde);
        }
        return klassement;
    }
    
    private String[][] cardSortBisKlassement(String[][] klassement, int begin, int einde){
        int score;
        String naam;
        int j;
        for(int i = begin+1; i <= einde; i++){
            score = Integer.parseInt(klassement[1][i]);
            naam = klassement[0][i];
            j = i;
            while(j > begin && score > Integer.parseInt(klassement[1][j-1])){
                klassement[0][j] = klassement[0][j-1];
                klassement[1][j] = klassement[1][j-1];
                j--;
            }
            klassement[0][j] = naam;
            klassement[1][j] = Integer.toString(score);
        }
        return klassement;
    }

    private String[][] wisselKlassement(String[][] k ,int a, int b) {
        String tempNaam = k[0][a];
        String tempScore = k[1][a];
        
        k[0][a] = k[0][b];
        k[1][a] = k[1][b];
        
        k[0][b] = tempNaam;
        k[1][b] = tempScore;
        
        return k;
    }
}//einde klasse
