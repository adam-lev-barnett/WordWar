package mechanics.turn.scoring;

import board.BoardSquareEffect;
import tiles.LetterTile;

import java.util.ArrayList;
import java.util.List;

public enum ScoreCalculator {
    INSTANCE;

    public int calculateScore(List<LetterTile> tiles, List<BoardSquareEffect> effects, boolean isBingo) {
        if (tiles == null || tiles.isEmpty()) return 0;
        if (effects == null) effects = new ArrayList<>();

        int score = 0;
        int wordMultiplier = 1;

        for (int i = 0; i < tiles.size(); i++) {
            LetterTile tile = tiles.get(i);

            switch (effects.get(i)) {
                case DOUBLE_LETTER_SCORE -> score += 2 * tile.getPointValue();
                case TRIPLE_LETTER_SCORE ->  score += 3 * tile.getPointValue();
                case DOUBLE_WORD_SCORE -> wordMultiplier *= 2;
                case TRIPLE_WORD_SCORE -> wordMultiplier *= 3;
            }

        }

        if (isBingo) score += 50;
        return wordMultiplier * score;

    }
}
