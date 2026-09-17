package mechanics.turn.scoring;

import board.BoardSquareEffect;
import exceptions.InvalidTileException;
import tiles.LetterTile;
import tiles.PointMap;

import java.util.ArrayList;
import java.util.List;

public enum ScoreCalculator {
    INSTANCE;

    public int calculateTotalScore(List<LetterTile> tiles, List<BoardSquareEffect> effects, boolean isBingo, List<String> xWords) throws InvalidTileException {
        if (tiles == null || tiles.isEmpty()) return 0;
        if (effects == null) effects = new ArrayList<>();
        boolean doubleWord = false;
        boolean tripleWord = false;

        int totalScore = 0;
        int xWordScore = calculateXWords(xWords);

        for (int i = 0; i < tiles.size(); i++) {
            LetterTile tile = tiles.get(i);

            switch (effects.get(i)) {
                case DOUBLE_LETTER_SCORE -> totalScore += 2 * tile.getPointValue();
                case TRIPLE_LETTER_SCORE ->  totalScore += 3 * tile.getPointValue();
                case DOUBLE_WORD_SCORE -> {
                    doubleWord = true;
                    totalScore += tile.getPointValue();
                }
                case TRIPLE_WORD_SCORE -> {
                    tripleWord = true;
                    totalScore += tile.getPointValue();
                }
            }

        }

        if (doubleWord && !tripleWord) totalScore*=2;
        if (!doubleWord && tripleWord) totalScore*=3;
        if (doubleWord && tripleWord) totalScore = totalScore*2 + totalScore*3;

        if (isBingo) totalScore += 50;
        return totalScore;

    }

    private int calculateXWords(List<String> words) throws InvalidTileException {
        if (words == null || words.isEmpty()) return 0;
        int score = 0;
        for (String word : words) {
            for (char c : word.toCharArray()) {
                score += PointMap.get(c);
            }
        }

        return score;
    }
}
