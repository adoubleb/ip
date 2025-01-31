package exceptions;

/**
 * Represents an exception that is thrown when an invalid or unrecognized command is encountered.
 * This custom exception is typically used in the application to handle errors related to
 * user input when parsing commands.
 *
 * This class extends the {@code Exception} class and provides a constructor to initialize the error message.
 *
 * Usage scenarios:
 * - Thrown when the user provides an invalid or undefined command.
 * - Used to indicate input-related errors during command processing.
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException(String message) {
        super(message);
    }
}
