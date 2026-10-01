package board;

import mechanics.pieceplacement.Direction;
import tiles.LetterTile;

import java.util.Collections;
import java.util.List;

/**
 * Represents a complete word placed on the game board as a Java record.
 */
public record BoardWord(
        String word,
        int startRow,
        int startCol,
        int endRow,
        int endCol,
        Direction direction,
        List<LetterTile> tiles,
        int score
) {
    public BoardWord {
        tiles = (tiles != null) ? List.copyOf(tiles) : Collections.emptyList();
    }

    /** Used to check if a word occupies a specific square on the board*/
    public boolean occupiesSquare(int row, int col) {
        if (direction == Direction.HORIZONTAL) {
            return row == startRow && col >= startCol && col <= endCol;
        } else {
            return col == startCol && row >= startRow && row <= endRow;
        }
    }
}
