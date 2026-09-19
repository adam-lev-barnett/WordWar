package mechanics.turn.scoring;

import player.Player;
import board.tiles.LetterTile;

public record TileSubmission(Player player, LetterTile tile, int row, int col) {
}
