package exceptions;

public class VerplichtVeldException extends RuntimeException {

    public VerplichtVeldException() {
        super("Veld is verplicht");
    }

    public VerplichtVeldException(String message) {
        super(message);
    }

    public VerplichtVeldException(String message, Throwable cause) {
        super(message, cause);
    }

    public VerplichtVeldException(Throwable cause) {
        super(cause);
    }

    public VerplichtVeldException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
