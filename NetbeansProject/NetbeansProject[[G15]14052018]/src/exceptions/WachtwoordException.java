package exceptions;

public class WachtwoordException extends RuntimeException {

    public WachtwoordException() {
         super("wachtwoord niet correct");
    }

    public WachtwoordException(String msg) {
        super(msg);
    }

    public WachtwoordException(String message, Throwable cause) {
        super(message, cause);
    }

    public WachtwoordException(Throwable cause) {
        super(cause);
    }

    public WachtwoordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
