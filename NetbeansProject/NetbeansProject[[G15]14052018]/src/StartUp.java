
import cui.*;
import domein.DomeinController;

/**
 * begin van de klasse StartUp
 * @author reeve
 */
public class StartUp {
    
    /**
     * de main methode die de domeinController meegeeft aan de verschillende UC-applicaties
     * @param args
     */
    public static void main(String[] args) {
        DomeinController dc = new DomeinController();
        UC1Applicatie a1 = new UC1Applicatie(dc);
    }
}
