package persistentie;

import domein.Moeilijkheidsgraad;
import domein.Speler;
import domein.Uitdaging;
import exceptions.UitdagingNietGevondenException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * begin van de klasse UitdagingMapper
 *
 * @author reeve
 */
public class UitdagingMapper {

    private static final String INSERT_UITDAGING
            = "INSERT INTO ID222177_g15.uitdaging (speler, tegenspeler, moeilijkheidsgraad, teRadenCode)"
            + "VALUES (?, ?, ?, ?)";
    private static final String SELECT_UITDAGINGID
            = "SELECT MAX(id) FROM ID222177_g15.uitdaging WHERE speler = ?";
    private static final String UPDATE_SCORESPELER = "UPDATE ID222177_g15.uitdaging SET scoreSpeler = ? WHERE id = ?";
    private static final String UPDATE_SCORETEGENSPELER = "UPDATE ID222177_g15.uitdaging SET scoreTegenspeler = ? WHERE id = ?";
    private static final String SELECT_UITDAGING_VOOR_TEGENSPELER = "SELECT * FROM ID222177_g15.uitdaging WHERE tegenspeler = ? AND id = ?";
    private static final String SCORE_MG_SPELER = "SELECT speler, SUM(if(scoreSpeler <= scoreTegenspeler, 3, -1)) as score\n"
            + "FROM ID222177_g15.uitdaging\n"
            + "WHERE scoreSpeler IS NOT NULL AND scoreTegenspeler IS NOT NULL AND moeilijkheidsgraad = ?\n"
            + "GROUP BY speler";
    private static final String SCORE_MG_TEGENSPELER = "SELECT tegenSpeler, SUM(if(scoreSpeler > scoreTegenspeler, 3, -1)) as score\n"
            + "FROM ID222177_g15.uitdaging\n"
            + "WHERE scoreSpeler IS NOT NULL AND scoreTegenspeler IS NOT NULL AND moeilijkheidsgraad = ?\n"
            + "GROUP BY tegenSpeler";

    /**
     * voegt een uitdaging-object toe aan de databank
     *
     * @param uitdaging
     */
    public void voegToe(Uitdaging uitdaging) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(INSERT_UITDAGING)) {
            query.setString(1, uitdaging.getSpeler().getSpelersnaam());
            query.setString(2, uitdaging.getTegenspeler().getSpelersnaam());
            query.setString(3, uitdaging.getMoeilijkheidsgraad().getNaam());
            query.setString(4, Arrays.toString(uitdaging.getCode()));
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    /**
     * geeft de laatste nummer van een uitdaging terug voor een bepaalde speler
     *
     * @param speler
     * @return
     */
    public int geefLaatsteNummerUitdaging(Speler speler) {
        int nummer = 0;
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(SELECT_UITDAGINGID)) {
            query.setString(1, speler.getSpelersnaam());
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    nummer = rs.getInt("MAX(id)");
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return nummer;
    }

    /**
     * update de score in de databank hordende bij een bepaalde speler, hiervoor
     * heeft de methode een uitdagingid, originele score en de speler nodig
     *
     * @param uitdagingid
     * @param score
     * @param speler
     */
    public void updateScore(int uitdagingid, int score, Speler speler) {
        if (geefNaamSpeler(uitdagingid).equals(speler.getSpelersnaam())) {
            updateScoreSpeler(uitdagingid, score);
        } else {
            updateScoreTegenSpeler(uitdagingid, score);
        }

    }

    private void updateScoreSpeler(int uitdagingid, int score) {
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(UPDATE_SCORESPELER)) {
            query.setInt(1, score);
            query.setInt(2, uitdagingid);
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void updateScoreTegenSpeler(int uitdagingid, int score) {
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(UPDATE_SCORETEGENSPELER)) {
            query.setInt(1, score);
            query.setInt(2, uitdagingid);
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private String geefNaamSpeler(int id) {
        String naam = "";
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("SELECT speler FROM ID222177_g15.uitdaging where id = ?")) {
            query.setInt(1, id);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {

                    naam = rs.getString("speler");

                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return naam;

    }

    /**
     * geeft een uitdaging terug op naam van de tegenspeler (tegenspelersnaam)
     * en id van de speler
     *
     * @param tegenspelersnaam
     * @param id
     * @return
     * @throws UitdagingNietGevondenException
     */
    public Uitdaging geefUitdagingOpNaamTegenSpelerEnID(String tegenspelersnaam, int id) throws UitdagingNietGevondenException {
        Uitdaging uitdaging = null;
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(SELECT_UITDAGING_VOOR_TEGENSPELER)) {
            query.setString(1, tegenspelersnaam);
            query.setInt(2, id);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    String naamSpeler = rs.getString("speler");
                    String teRadenCode = rs.getString("teRadenCode");
                    String mg = rs.getString("moeilijkheidsgraad");

                    Speler speler = SpelerMapper.geefSpeler(naamSpeler);
                    Speler tegenspeler = SpelerMapper.geefSpeler(tegenspelersnaam);
                    Moeilijkheidsgraad moeilijkheidsgraad = new Moeilijkheidsgraad(mg);

                    String[] items = teRadenCode.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");
                    int[] code = new int[items.length];

                    for (int i = 0; i < items.length; i++) {

                        code[i] = Integer.parseInt(items[i]);

                    }
                    uitdaging = new Uitdaging(speler, tegenspeler, moeilijkheidsgraad, code, id);
                }
            }
        } catch (SQLException ex) {
            throw new UitdagingNietGevondenException();
        }
        return uitdaging;
    }

    /**
     * geeft een uitdaging die nog geaccepteerd moet worden terug voor een
     * speler (spelersnaam)
     *
     * @param spelersnaam
     * @return
     */
    public Uitdaging[] geefUitdagingenTeAccepteren(String spelersnaam) {
        ArrayList<Uitdaging> uitdagingen = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g15.uitdaging WHERE tegenspeler = ? AND (scoreTegenspeler = 0 OR scoreTegenspeler IS NULL)")) {
            query.setString(1, spelersnaam);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    String naamSpeler = rs.getString("speler");
                    String teRadenCode = rs.getString("teRadenCode");
                    String mg = rs.getString("moeilijkheidsgraad");
                    int id = rs.getInt("id");

                    Speler speler = SpelerMapper.geefSpeler(naamSpeler);
                    Speler tegenspeler = SpelerMapper.geefSpeler(spelersnaam);
                    Moeilijkheidsgraad moeilijkheidsgraad = new Moeilijkheidsgraad(mg);

                    String[] items = teRadenCode.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");
                    int[] code = new int[items.length];

                    for (int i = 0; i < items.length; i++) {

                        code[i] = Integer.parseInt(items[i]);

                    }

                    uitdagingen.add(new Uitdaging(speler, tegenspeler, moeilijkheidsgraad, code, id));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return uitdagingen.toArray(new Uitdaging[uitdagingen.size()]);
    }

    /**
     * stelt de scores voor een bepaalde mg in
     *
     * @param mg
     * @return
     */
    public ArrayList<ArrayList<String>> scoresSpelerMG(String mg) {
        ArrayList<ArrayList<String>> scoreBord = new ArrayList<ArrayList<String>>();
        ArrayList<String> punten = new ArrayList<>();
        ArrayList<String> namen = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(SCORE_MG_SPELER)) {
            query.setString(1, mg);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    punten.add(Integer.toString(rs.getInt("score")));
                    namen.add(rs.getString("speler"));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        scoreBord.add(namen);
        scoreBord.add(punten);
        return scoreBord;
    }

//    private static final String SCORE_MG_SPELER = "SELECT speler, SUM(if(scoreSpeler <= scoreTegenspeler, 3, -1)) as score\n"
//            + "FROM uitdaging\n"
//            + "WHERE scoreSpeler IS NOT NULL AND scoreTegenspeler IS NOT NULL AND moeilijkheidsgraad = ?\n"
//            + "GROUP BY speler";
    /**
     * geeft een klassement van alle spelers terug voor een mg
     *
     * @param mg
     * @return
     */
    public String[][] geefKlassementSpelers(String mg) {
        ArrayList<String> spelers = new ArrayList<>();
        ArrayList<String> scores = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(SCORE_MG_SPELER)) {
            query.setString(1, mg);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    spelers.add(rs.getString("speler"));
                    scores.add(Integer.toString(rs.getInt("score")));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        int size = spelers.size();
        return new String[][]{spelers.toArray(new String[size]), scores.toArray(new String[size])};
    }

    /**
     * geeft een klassement van alle tegenspelers terug voor een mg
     *
     * @param mg
     * @return
     */
    public String[][] geefKlassementTegenSpelers(String mg) {
        ArrayList<String> spelers = new ArrayList<>();
        ArrayList<String> scores = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(SCORE_MG_TEGENSPELER)) {
            query.setString(1, mg);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    spelers.add(rs.getString("tegenSpeler"));
                    scores.add(Integer.toString(rs.getInt("score")));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        int size = spelers.size();
        return new String[][]{spelers.toArray(new String[size]), scores.toArray(new String[size])};
    }
}//einde klasse

