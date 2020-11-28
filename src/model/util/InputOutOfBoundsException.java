package model.util;

/**
 * This class represents an exception that is thrown when an
 * input from the user is not inside the valid limits.
 * @author Tasos Papadopoulos
 * @version 28.11.2020
 * */
public class InputOutOfBoundsException extends RuntimeException{
    /**
     * Constructs an {@code InputOutOfBoundsException} with no detail
     * message.
     */
    public InputOutOfBoundsException() {
        super();
    }

    /**
     * Constructs an {@code InputOutOfBoundsException} with the specified
     * detail message.
     *
     * @param   s   the detail message.
     */
    public InputOutOfBoundsException(String s) {
        super(s);
    }
}
