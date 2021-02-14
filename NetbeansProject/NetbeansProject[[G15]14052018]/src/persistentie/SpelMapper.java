package persistentie;

import domein.Moeilijkheidsgraad;
import domein.Spel;
import domein.Spelbord;
import domein.Speler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//"jdbc:mysql://ID222177_g15.db.webhosting.be?user=ID222177_g15&password=QuedLif6";
/**
 * begin van de klasse SpelMapper
 *
 * @author reeve
 */
public class SpelMapper {

    private static final String INSERT_SPEL
            = "INSERT INTO ID222177_g15.spel (naamSpel, naam, speler, moeilijkheidsgraad, code, uitdagingnr, idRij0, idRij1, idRij2, idRij3, idRij4, idRij5, idRij6, idRij7, idRij8, idRij9, idRij10, idRij11)"
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_SPEL
            = "DELETE FROM ID222177_g15.spel WHERE speler = ? AND naam = ?";

    private final RijMapper RM = new RijMapper();

    /**
     * voegt een spel-object met een bord toe aan de databank
     *
     * @param spel
     * @param bord
     */
    public void voegToe(Spel spel, int[][] bord) {
        {
            try (
                    Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                    PreparedStatement query = conn.prepareStatement(INSERT_SPEL)) {
                String naamSpel = String.format("%s%s", spel.getNaam(), spel.getSpeler().getSpelersnaam());
                query.setString(1, naamSpel);
                query.setString(2, spel.getNaam());
                query.setString(3, spel.getSpeler().getSpelersnaam());
                query.setString(4, spel.getMoeilijkheidsgraad().getNaam());
                query.setString(5, comprimeerCode(spel.getTeRadenCode()));
                query.setInt(6, spel.getUitdagingnr());

                String idrij = "";
                for (int i = 0; i < 12; i++) {
                    idrij = naamSpel + String.format("r%02d", i);
                    RM.voegToe(idrij, bord[i][0], bord[i][1], bord[i][2], bord[i][3], bord[i][4]);
                }

                for (int i = 0; i < 12; i++) {
                    query.setString((7 + i), naamSpel + String.format("r%02d", i));
                }
                query.executeUpdate();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
    }

    /**
     * geeft een spel met een naam (naamSpel) terug uit de databank
     *
     * @param naamSpel
     * @return
     */
    public Spel geefSpel(String naamSpel) {
        Spel hetSpel = null;
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g15.spel where naam =?")) {
            query.setString(1, naamSpel);
            try (ResultSet rs = query.executeQuery()) {

                while (rs.next()) {
                    String naam = rs.getString("naam");
                    String speler = rs.getString("speler");
                    String code = rs.getString("code");
                    int[] teRadenCode = decomprimeerCode(code);
                    String naamgraad = rs.getString("moeilijkheidsgraad");
                    Moeilijkheidsgraad graad = new Moeilijkheidsgraad(naamgraad, bepaalPosities(naamgraad));
                    int[][] bord = geefBordPlusControle(teRadenCode, graad, rs.getString("naamSpel"));
                    int uitdaging = rs.getInt("uitdagingnr");

                    Speler deSpeler = SpelerMapper.geefSpeler(speler);

                    hetSpel = new Spel(deSpeler, graad, naam, teRadenCode, new Spelbord(bord), uitdaging);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return hetSpel;
    }

    /**
     * geeft een bord met de terug op basis van een naam, deze heeft de
     * teRadenCode en de mg nodig om te controleren
     *
     * @param teRadenCode
     * @param mg
     * @param naamSpel
     * @return
     */
    public int[][] geefBordPlusControle(int[] teRadenCode, Moeilijkheidsgraad mg, String naamSpel) {
        int[][] uit = new int[12][mg.getAantalPosities() * 2];

        for (int i = 0; i < 12; i++) {
            uit[i] = RM.geefRijPlusControle(naamSpel + String.format("r%02d", i), teRadenCode, mg);

        }
        return uit;
    }

    /**
     * geeft een lijst van de opgeslagen spelletjes met hun moeilijkheidsgraad,
     * van de ingelogde speler terug uit de databank
     *
     * @param speler
     * @return
     */
    public List<String> geefSpelletjes(String speler) {
        List<String> spelletjes = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g15.spel WHERE speler = ?")) {
            query.setString(1, speler);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    String naam = rs.getString("naam");
                    String mg = rs.getString("moeilijkheidsgraad");

                    spelletjes.add(String.format("%25s%25s", naam, mg));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return spelletjes;
    }

    /**
     * geeft een lijst van de spelletjes op naam terug uit de databank van de
     * ingelogde speler
     *
     * @param speler
     * @return
     */
    public List<String> geefSpelletjesOpNaam(String speler) {
        List<String> spelletjesOpNaam = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g15.spel WHERE speler = ?")) {
            query.setString(1, speler);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    String naam = rs.getString("naam");

                    spelletjesOpNaam.add(String.format("%s", naam));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return spelletjesOpNaam;
    }

    /**
     * verwijdert een spel-object met een naam (naamSpel) van een speler
     * (naamSpeler) uit de databank
     *
     * @param naamSpeler
     * @param naamSpel
     */
    public void verwijderSpel(String naamSpeler, String naamSpel) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(DELETE_SPEL)) {
            query.setString(1, naamSpeler);
            query.setString(2, naamSpel);

            for (int i = 0; i < 12; i++) {
                String rij =naamSpel+naamSpeler+ String.format("r%02d", i);
                RM.verwijderRij(rij);
            }
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * bepaalt het aantal posities aan de hand van de moeilijkheidsgraad (naam)
     *
     * @param naam
     * @return
     */
    public int bepaalPosities(String naam) {
        int posities;
        if ("moeilijk".equals(naam)) {
            posities = 5;
        } else {
            posities = 4;
        }

        return posities;
    }

    /**
     * comprimeert de code
     *
     * @param code
     * @return
     */
    public String comprimeerCode(int code[]) {
        String resul = "";
        for (int i = 0; i < code.length; i++) {

            if (i < code.length - 1) {
                resul += String.format("%d-", code[i]);
            } else {
                resul += String.format("%d", code[i]);
            }
        }

        return resul;
    }

    /**
     * decomprimeert de code
     *
     * @param compCode
     * @return
     */
    public int[] decomprimeerCode(String compCode) {
        String[] array = compCode.split("-", -1);
        int[] deCode = Arrays.asList(array).stream().mapToInt(Integer::parseInt).toArray();
        return deCode;
    }
}//einde klass SpelMapper
