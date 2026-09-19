package mechanics.turn.scoring;

import board.BoardSquareEffect;
import board.tiles.LetterTile;

public record TileAndEffect(LetterTile tile, BoardSquareEffect effect) {
}
