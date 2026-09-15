package mechanics.turn;

import mechanics.pieceplacement.Direction;
import tiles.LetterTile;

import java.util.List;

public record Submission(List<LetterTile> tiles, int row, int col, Direction direction) {
}
