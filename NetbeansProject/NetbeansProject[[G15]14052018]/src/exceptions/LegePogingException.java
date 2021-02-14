package exceptions;

public class LegePogingException extends RuntimeException {

    public LegePogingException() {
    }

    public LegePogingException(String msg) {
        super(msg);
    }

    public LegePogingException(String message, Throwable cause) {
        super(message, cause);
    }

    public LegePogingException(Throwable cause) {
        super(cause);
    }

    public LegePogingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
