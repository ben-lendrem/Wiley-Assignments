package Java.Errors;

/*
 * Invalid Input Exception - thrown when the input provided by the user is invalid (covers invalid input
 * that is not covered by InputMismatchException), i.e. when a user has to enter a number within a range
 * and they enter a number outside of the range.
 */
public class InvalidInputException extends RuntimeException {
    /**
     * InvalidInputException() - constructs a default Throwable with a default message provided.
     */
    public InvalidInputException() {
        super("The input provided was not valid!");
    }

    /**
     * @param message - custom message provided for outputting
     */
    public InvalidInputException(String message) {
        super(message);
    }

    /**
     *
     * @param message - custom message provided for outputting
     * @param cause - throwable used for exception wrapping
     */
    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
