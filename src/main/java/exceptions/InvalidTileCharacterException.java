package exceptions;

public class InvalidTileCharacterException extends RuntimeException {
  public InvalidTileCharacterException(String message) {
    super(message);
  }
}
