package exceptions;

public class InvalidTileException extends Exception {
    public InvalidTileException() {
        super("Invalid or null tile.");
    }
}
