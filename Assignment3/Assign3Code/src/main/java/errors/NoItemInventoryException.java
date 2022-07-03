package errors;

public class NoItemInventoryException  extends RuntimeException {
    public NoItemInventoryException() {
        super("No such item in the inventory");
    }

    public NoItemInventoryException(String message) {
        super(message);
    }

    public NoItemInventoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
