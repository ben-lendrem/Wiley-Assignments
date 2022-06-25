package Java.Errors;

public class MalformedObjectException extends RuntimeException {
    public MalformedObjectException() {
        super("An object or it's attribute was malformed!");
    }

    public MalformedObjectException(String message) {
        super(message);
    }

    public MalformedObjectException(String message, Throwable cause) {
        super(message, cause);
    }
}
