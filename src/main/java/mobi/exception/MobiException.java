package mobi.exception;

/**
 * Represents exceptions for the Mobi ChatBot.
 * <p>
 * Thrown whenever user enters invalid input or an
 * error occurs during command execution
 * </p>
 */
public class MobiException extends Exception {

    /**
     * Initializes MobiException with the specified detail message.
     *
     * @param msg message explaining the exception cause
     */
    public MobiException(String msg) {
        super(msg);
    }
}
