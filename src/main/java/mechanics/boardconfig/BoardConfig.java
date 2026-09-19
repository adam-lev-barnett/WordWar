package mechanics.boardconfig;

import exceptions.InvalidBoardSquareException;

public enum BoardConfig {
    INSTANCE;

    public static final int ROWS = 15;
    public static final int COLS = 15;

    public static void validateSquare(int row, int col) throws InvalidBoardSquareException {
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS) throw new InvalidBoardSquareException("Selected coordinate is out of bounds");
    }
}
