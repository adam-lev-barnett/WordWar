package mechanics.turn;

import board.BoardSquareEffect;
import board.BoardWord;

import java.util.Collections;
import java.util.List;

/**
 * Result of evaluating or playing a move submission as an immutable Java record.
 * Holds validity, formed words with their scores, total score, triggered action effects, and bingo status.
 */
public record MoveResult(
        boolean isValid,
        String failureReason,
        int totalScore,
        List<BoardWord> formedWords,
        List<BoardSquareEffect> actionEffects,
        boolean isBingo,
        List<PlacedTile> placedTiles
) {
    public MoveResult {
        formedWords = (formedWords != null) ? List.copyOf(formedWords) : Collections.emptyList();
        actionEffects = (actionEffects != null) ? List.copyOf(actionEffects) : Collections.emptyList();
        placedTiles = (placedTiles != null) ? List.copyOf(placedTiles) : Collections.emptyList();
    }

    public static MoveResult valid(int totalScore, List<BoardWord> formedWords,
                                   List<BoardSquareEffect> actionEffects,
                                   boolean isBingo, List<PlacedTile> placedTiles) {
        return new MoveResult(true, null, totalScore, formedWords, actionEffects, isBingo, placedTiles);
    }

    public static MoveResult invalid(String reason) {
        return new MoveResult(false, reason, 0, Collections.emptyList(), Collections.emptyList(), false, Collections.emptyList());
    }
}
