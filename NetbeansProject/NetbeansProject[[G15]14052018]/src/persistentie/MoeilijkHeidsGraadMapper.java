package persistentie;

import domein.Moeilijkheidsgraad;
import java.util.ArrayList;
import java.util.List;

/**
 * begin van de klasse MoeilijkheidsgraadMapper
 *
 * @author arthu
 */
public class MoeilijkHeidsGraadMapper {

    /**
     * geeft de moeilijkheidsgraad op naam terug uit de databank
     *
     * @param naam
     * @return
     */
    public Moeilijkheidsgraad geefMoeilijkheidsgraadOpNaam(String naam) {
//        Moeilijkheidsgraad moeilijkheidsgraad = null;
//
//        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
//                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g15.moeilijkheidsgraad WHERE naam = ?"))
//        {
//            query.setString(1, naam);
//            try (ResultSet rs = query.executeQuery())
//            {
//                if (rs.next())
//                {
//               
//                    int posities = rs.getInt("posities");
//
//                    moeilijkheidsgraad = new Moeilijkheidsgraad(naam, posities);
//                }
//            }
//        } catch (SQLException ex)
//        {
//            throw new RuntimeException(ex);
//        }
        List<Moeilijkheidsgraad> graden = getMoeilijkheidsgraden();
        Moeilijkheidsgraad moeilijkheidsgraad = null;
        for (Moeilijkheidsgraad zoekMoeilijkheidsgraad : graden) {
            if (naam.equals(zoekMoeilijkheidsgraad.getNaam())) {
                moeilijkheidsgraad = zoekMoeilijkheidsgraad;
            }
        }
        return moeilijkheidsgraad;
    }

    /**
     * getter van het attributt moeilijkheidsgraden
     *
     * @return
     */
    public List<Moeilijkheidsgraad> getMoeilijkheidsgraden() {
        List<Moeilijkheidsgraad> graden = new ArrayList<>();

        graden.add(new Moeilijkheidsgraad("gemakkelijk", 4));
        graden.add(new Moeilijkheidsgraad("moeilijk", 5));
        graden.add(new Moeilijkheidsgraad("normaal", 4));

//        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
//                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g15.moeilijkheidsgraden");
//                ResultSet rs = query.executeQuery()) {
//
//            while (rs.next()) {
//                String naam = rs.getString("naam");
//                int posities = rs.getInt("posities");
//
//                graden.add(new Moeilijkheidsgraad(naam, posities));
//            }
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
        return graden;
    }
}
