package exceptions;

public class NoTilesPlayedException extends RuntimeException {
    public NoTilesPlayedException() {
        super("No tiles were selected");
    }
}
