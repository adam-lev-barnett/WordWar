package mechanics.turn;

import androidx.annotation.NonNull;
import tiles.LetterTile;
import java.util.Objects;

/**
 * Represents a single tile placed at a specific board coordinate (row, col).
 */
public record PlacedTile(LetterTile tile, int row, int col) {
    public PlacedTile {
        Objects.requireNonNull(tile, "LetterTile cannot be null");
    }

    @NonNull
    @Override
    public String toString() {
        return tile.getLetter() + "@[" + row + "," + col + "]";
    }
}
