package errors;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException() {
        super("Not enough funds to carry out the transaction!");
    }

    public InsufficientFundsException(String message) {
        super(message);
    }

    public InsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}
