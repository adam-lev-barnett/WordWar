package exceptions;

public class EmptyBagException extends RuntimeException {
    public EmptyBagException(String message) {
        super(message);
    }
}
