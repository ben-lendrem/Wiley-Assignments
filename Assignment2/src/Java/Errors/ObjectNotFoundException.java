package Java.Errors;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException() {
        super("The object was not found!");
    }

    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
