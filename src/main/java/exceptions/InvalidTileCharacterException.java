package exceptions;

public class InvalidTileCharacterException extends Exception {
    public InvalidTileCharacterException() {
        super("Invalid tile character.");
    }
}
