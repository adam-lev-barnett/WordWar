package exceptions;

public class InvalidPointValueException extends Exception {
    public InvalidPointValueException() {
        super("Letter tile score must be between 0 and 10");
    }
}
