package exceptions;


public class UitdagingNietGevondenException extends Exception {

    /**
     * Creates a new instance of <code>UitdagingNietGevondenException</code>
     * without detail message.
     */
    public UitdagingNietGevondenException() {
    }

    /**
     * Constructs an instance of <code>UitdagingNietGevondenException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public UitdagingNietGevondenException(String msg) {
        super(msg);
    }
}
