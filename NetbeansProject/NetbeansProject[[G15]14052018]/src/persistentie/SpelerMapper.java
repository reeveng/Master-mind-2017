package persistentie;

import domein.Speler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//"jdbc:mysql://ID222177_g15.db.webhosting.be?user=ID222177_g15&password=QuedLif6";
/**
 * begin van de klasse SpelerMapper
 *
 * @author reeve
 */
public class SpelerMapper {

    private static final String INSERT_SPELER = "INSERT INTO ID222177_g15.speler (spelersnaam, wachtwoord, gemakkelijk, normaal, moeilijk)"
            + "VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_SPELER = "UPDATE ID222177_g15.speler SET gemakkelijk = ?, normaal = ?, moeilijk = ? WHERE spelersnaam = ?";

    /**
     * voegt een speler object toe aan de databank
     *
     * @param speler
     */
    public void voegToe(Speler speler) {

        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(INSERT_SPELER)) {
            query.setString(1, speler.getSpelersnaam());
            query.setString(2, speler.getWachtwoord());
            query.setInt(3, speler.getGewonnenMoeilijkheidsgraden()[0]);
            query.setInt(4, speler.getGewonnenMoeilijkheidsgraden()[1]);
            query.setInt(5, speler.getGewonnenMoeilijkheidsgraden()[2]);
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * geeft een lijst van de spelers uit de databank
     *
     * @return
     */
    public List<Speler> geefSpelers() {
        List<Speler> spelers = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g15.speler");
                ResultSet rs = query.executeQuery()) {

            while (rs.next()) {
                String spelersnaam = rs.getString("spelersnaam");
                String wachtwoord = rs.getString("wachtwoord");
                int[] graden = new int[3];
                graden[0] = rs.getInt("gemakkelijk");
                graden[1] = rs.getInt("normaal");
                graden[2] = rs.getInt("moeilijk");

                spelers.add(new Speler(spelersnaam, wachtwoord, graden));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return spelers;
    }

    /**
     * geeft een lijst van de tegenspelers per moeilijkheidsgraad (mg)
     *
     * @param mg
     * @return
     */
    public List<Speler> geefTegenspelers(String mg) {
        List<Speler> spelers = new ArrayList<>();
        if (mg.equals("gemakkelijk")) {
            spelers = geefSpelers();
        } else if (mg.equals("normaal")) {
            try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                    PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g15.speler WHERE gemakkelijk >= 20")) {
                try (ResultSet rs = query.executeQuery()) {
                    while (rs.next()) {
                        String spelersnaam = rs.getString("spelersnaam");
                        String wachtwoord = rs.getString("wachtwoord");
                        int[] graden = new int[3];
                        graden[0] = rs.getInt("gemakkelijk");
                        graden[1] = rs.getInt("normaal");
                        graden[2] = rs.getInt("moeilijk");

                        spelers.add(new Speler(spelersnaam, wachtwoord, graden));
                    }
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (mg.equals("moeilijk")) {
            try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                    PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g15.speler WHERE normaal >= 20")) {
                try (ResultSet rs = query.executeQuery()) {
                    while (rs.next()) {
                        String spelersnaam = rs.getString("spelersnaam");
                        String wachtwoord = rs.getString("wachtwoord");
                        int[] graden = new int[3];
                        graden[0] = rs.getInt("gemakkelijk");
                        graden[1] = rs.getInt("normaal");
                        graden[2] = rs.getInt("moeilijk");

                        spelers.add(new Speler(spelersnaam, wachtwoord, graden));
                    }
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            throw new IllegalArgumentException("moeilijkehidsgraad was niet herkend");
        }

        return spelers;
    }

    /**
     * geeft een speler object terug op basis van een spelersnaam
     *
     * @param spelersnaam
     * @return
     */
    public static Speler geefSpeler(String spelersnaam) {
        Speler speler = null;

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g15.speler WHERE spelersnaam = ?")) {
            query.setString(1, spelersnaam);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
//                    String spelersnaam = rs.getString("spelersnaam");
                    String wachtwoord = rs.getString("wachtwoord");
                    int[] graden = new int[3];
                    graden[0] = rs.getInt("gemakkelijk");
                    graden[1] = rs.getInt("normaal");
                    graden[2] = rs.getInt("moeilijk");

                    speler = new Speler(spelersnaam, wachtwoord, graden);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return speler;
    }

    /**
     * slaat de winst op per moeilijkheidsgraad van een speler
     *
     * @param speler
     */
    public void slaWinstOp(Speler speler) {
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(UPDATE_SPELER)) {
            int[] graden = speler.getGewonnenMoeilijkheidsgraden();
            query.setInt(1, graden[0]);
            query.setInt(2, graden[1]);
            query.setInt(3, graden[2]);
            query.setString(4, speler.getSpelersnaam());

//                graden[0] = query.setInt("gemakkelijk");
//                graden[1] = rs.getInt("normaal");
//                graden[2] = rs.getInt("moeilijk");
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}//einde klasse
