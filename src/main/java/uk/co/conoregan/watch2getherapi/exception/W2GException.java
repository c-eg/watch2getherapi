package uk.co.conoregan.watch2getherapi.exception;

/**
 * Watch 2 gether based exceptions.
 */
public class W2GException extends Exception {
    /**
     * Constructor with message.
     */
    public W2GException(String message) {
        super(message);
    }

    /**
     * Constructor with throwable.
     */
    public W2GException(Throwable cause) {
        super(cause);
    }
}
