package errors;

/*
 *****************************************
 * INPUT CLASS BROUGHT IN FROM ASSIGNMENT 2
 *****************************************
 *
 * Invalid Input Exception - thrown when the input provided by the user is invalid (covers invalid input
 * that is not covered by InputMismatchException), i.e. when a user has to enter a number within a range
 * and they enter a number outside of the range.
 */
public class InvalidInputException extends RuntimeException {


    public InvalidInputException() {
        super("The input provided was not valid!");
    }


    public InvalidInputException(String message) {
        super(message);
    }


    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
