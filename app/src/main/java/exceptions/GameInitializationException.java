package exceptions;

public class GameInitializationException extends RuntimeException {
    public GameInitializationException(String message) {
        super(message);
    }
}
