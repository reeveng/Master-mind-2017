package exceptions;

public class SpelersNaamInGebruikException extends RuntimeException {

    public SpelersNaamInGebruikException() {
         super("De spelersnaam is reeds in gebruik");
    }

    public SpelersNaamInGebruikException(String message) {
        super(message);
    }

    public SpelersNaamInGebruikException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpelersNaamInGebruikException(Throwable cause) {
        super(cause);
    }

    public SpelersNaamInGebruikException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
