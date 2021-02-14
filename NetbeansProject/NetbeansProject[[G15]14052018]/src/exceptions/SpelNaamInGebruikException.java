
package exceptions;

public class SpelNaamInGebruikException extends Exception
{
   public SpelNaamInGebruikException() {
       super("De spelnaam is reeds in gebruik");
    }

    public SpelNaamInGebruikException(String message) {
        super(message);
    }

    public SpelNaamInGebruikException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpelNaamInGebruikException(Throwable cause) {
        super(cause);
    }

    public SpelNaamInGebruikException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
