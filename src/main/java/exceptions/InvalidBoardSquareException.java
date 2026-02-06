package exceptions;

public class InvalidBoardSquareException extends Exception {
    public InvalidBoardSquareException(String message) {
        super("Invalid board square: " + message);
    }
}
