package persistentie;

import domein.Moeilijkheidsgraad;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * begin van de klasse RijMapper
 *
 * @author reeve
 */
public class RijMapper {

    private static final String DELETE_RIJ
            = "DELETE FROM ID222177_g15.rij WHERE idrij = ?";
    private static final String MAAK_RIJ
            = "INSERT INTO ID222177_g15.rij (idrij, cel0, cel1, cel2, cel3, cel4)"
            + "VALUES (?, ?, ?, ?, ?, ?)";

    /**
     * voegt een rij met idrij en tot 5 pogingen (cel0, cel1, cel2, cel3, cel4)
     * toe aan de databank
     *
     * @param idrij
     * @param cel0
     * @param cel1
     * @param cel2
     * @param cel3
     * @param cel4
     */
    public void voegToe(String idrij, int cel0, int cel1, int cel2, int cel3, int cel4) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(MAAK_RIJ)) {
            query.setString(1, idrij);
            query.setInt(2, cel0);
            query.setInt(3, cel1);
            query.setInt(4, cel2);
            query.setInt(5, cel3);
            query.setInt(6, cel4);
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    void verwijderRij(String idrij) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(DELETE_RIJ)) {
            query.setString(1, idrij);
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * geeft een rij met codepinnen terug, de rij heeft een idrij
     *
     * @param idrij
     * @param teRadenCode
     * @param mg
     * @return
     */
    public int[] geefRijPlusControle(String idrij, int[] teRadenCode, Moeilijkheidsgraad mg) {
        int[] poging = new int[mg.getAantalPosities()];
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g15.rij where idrij =?")) {
            query.setString(1, idrij);
            try (ResultSet rs = query.executeQuery()) {

                while (rs.next()) {

                    for (int i = 0; i < poging.length; i++) {
                        poging[i] = rs.getInt(String.format("cel%d", i));

                    }

                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        int[] controle = mg.evalueerPoging(poging, teRadenCode);
        int length = poging.length + controle.length;
        int[] result = new int[length];
        System.arraycopy(poging, 0, result, 0, poging.length);
        System.arraycopy(controle, 0, result, poging.length, controle.length);
        return result;
    }
}//einde klasse
