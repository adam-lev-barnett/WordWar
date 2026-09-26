package mechanics.turn;

import mechanics.pieceplacement.Direction;
import tiles.LetterTile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a player's move submission as an immutable Java record.
 * Supports both sequential placement from (row, col) in a Direction,
 * and explicit tile placements.
 */
public record Submission(
        List<LetterTile> tiles,
        int row,
        int col,
        Direction direction,
        List<PlacedTile> explicitPlacements
) {
    public Submission {
        tiles = (tiles != null) ? List.copyOf(tiles) : Collections.emptyList();
        explicitPlacements = (explicitPlacements != null) ? List.copyOf(explicitPlacements) : Collections.emptyList();
    }

    public Submission(List<LetterTile> tiles, int row, int col, Direction direction) {
        this(tiles, row, col, direction, Collections.emptyList());
    }

    public Submission(List<PlacedTile> explicitPlacements) {
        this(
                extractTiles(explicitPlacements),
                extractRow(explicitPlacements),
                extractCol(explicitPlacements),
                extractDirection(explicitPlacements),
                explicitPlacements
        );
    }

    private static List<LetterTile> extractTiles(List<PlacedTile> placements) {
        if (placements == null || placements.isEmpty()) return Collections.emptyList();
        List<LetterTile> list = new ArrayList<>();
        for (PlacedTile pt : placements) {
            list.add(pt.tile());
        }
        return list;
    }

    private static int extractRow(List<PlacedTile> placements) {
        if (placements == null || placements.isEmpty()) return 0;
        int minRow = placements.get(0).row();
        for (PlacedTile pt : placements) {
            if (pt.row() < minRow) minRow = pt.row();
        }
        return minRow;
    }

    private static int extractCol(List<PlacedTile> placements) {
        if (placements == null || placements.isEmpty()) return 0;
        int minCol = placements.get(0).col();
        for (PlacedTile pt : placements) {
            if (pt.col() < minCol) minCol = pt.col();
        }
        return minCol;
    }

    private static Direction extractDirection(List<PlacedTile> placements) {
        if (placements == null || placements.size() <= 1) return Direction.HORIZONTAL;
        boolean sameRow = true;
        boolean sameCol = true;
        int firstRow = placements.get(0).row();
        int firstCol = placements.get(0).col();
        for (PlacedTile pt : placements) {
            if (pt.row() != firstRow) sameRow = false;
            if (pt.col() != firstCol) sameCol = false;
        }
        if (sameRow) return Direction.HORIZONTAL;
        if (sameCol) return Direction.VERTICAL;
        return Direction.HORIZONTAL;
    }

    public boolean hasExplicitPlacements() {
        return !explicitPlacements.isEmpty();
    }
}
