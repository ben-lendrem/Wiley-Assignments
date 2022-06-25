package Java.Errors;

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
