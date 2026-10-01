package mechanics.turn.scoring;

import board.BoardSquareEffect;
import board.GameBoard;
import exceptions.InvalidMoveException;
import mechanics.pieceplacement.Direction;
import mechanics.turn.Submission;
import tiles.LetterTile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Calculates scores for words and turns in Word War / Scrabble.
 * Accurately scores newly placed tiles with square multipliers while treating existing tiles
 * at face value. A tile placed on a bonus square that is part of multiple words has its bonus
 * applied to each formed word.
 */
public enum ScoreCalculator {
    INSTANCE;

    public static final int BINGO_BONUS = 50;

    /**
     * Calculates the score for an individual word.
     *
     * @param tiles The tiles making up the word in sequence.
     * @param activeEffects The board square effect active for each tile position.
     *                      If a tile was an existing tile on the board (not placed this turn),
     *                      its effect MUST be PLAIN or null so bonuses are not reused.
     * @return The total points for this word.
     */
    public int calculateWordScore(List<LetterTile> tiles, List<BoardSquareEffect> activeEffects) {
        if (tiles == null || tiles.isEmpty()) return 0;

        int letterSum = 0;
        int wordMultiplier = 1;

        for (int i = 0; i < tiles.size(); i++) {
            LetterTile tile = tiles.get(i);
            int baseValue = (tile != null) ? tile.getPointValue() : 0;
            BoardSquareEffect effect = (activeEffects != null && i < activeEffects.size() && activeEffects.get(i) != null)
                    ? activeEffects.get(i)
                    : BoardSquareEffect.PLAIN;

            switch (effect) {
                case DOUBLE_LETTER_SCORE -> letterSum += 2 * baseValue;
                case TRIPLE_LETTER_SCORE -> letterSum += 3 * baseValue;
                case DOUBLE_WORD_SCORE -> {
                    letterSum += baseValue;
                    wordMultiplier *= 2;
                }
                case TRIPLE_WORD_SCORE -> {
                    letterSum += baseValue;
                    wordMultiplier *= 3;
                }
                default -> letterSum += baseValue;
            }
        }

        return letterSum * wordMultiplier;
    }

    /**
     * Calculates the total turn score for a submission on a given board,
     * including main word, cross-words, and any bingo bonus.
     */
    public int calculateTotalScore(GameBoard board, Submission submission) {
        int total = scoreMainWord(board, submission);
        int row = submission.row();
        int col = submission.col();
        Direction dir = (submission.direction() != null) ? submission.direction() : Direction.HORIZONTAL;
        int numTiles = submission.tiles().size();

        for (int i = 0; i < numTiles; i++) {
            int r = (dir == Direction.VERTICAL) ? row + i : row;
            int c = (dir == Direction.HORIZONTAL) ? col + i : col;
            total += scoreCrossWord(board, submission.tiles().get(i), r, c, dir);
        }

        if (validateBingo(submission)) {
            total += BINGO_BONUS;
        }

        return total;
    }

    public int scoreMainWord(GameBoard board, Submission submission) {
        int row = submission.row();
        int col = submission.col();
        Direction dir = (submission.direction() != null) ? submission.direction() : Direction.HORIZONTAL;
        int numTiles = submission.tiles().size();

        List<LetterTile> startTiles = getStartTiles(board, row, col, dir);
        int endRow = (dir == Direction.HORIZONTAL) ? row : row + numTiles;
        int endCol = (dir == Direction.HORIZONTAL) ? col + numTiles : col;
        List<LetterTile> endTiles = getEndTiles(board, endRow, endCol, dir);

        List<LetterTile> allTiles = new ArrayList<>(startTiles);
        allTiles.addAll(submission.tiles());
        allTiles.addAll(endTiles);

        if (allTiles.size() < 2) return 0;

        List<BoardSquareEffect> effects = new ArrayList<>();
        for (int i = 0; i < startTiles.size(); i++) {
            effects.add(BoardSquareEffect.PLAIN);
        }
        for (int i = 0; i < numTiles; i++) {
            int r = (dir == Direction.VERTICAL) ? row + i : row;
            int c = (dir == Direction.HORIZONTAL) ? col + i : col;
            effects.add(board.getSquare(r, c).getEffect());
        }
        for (int i = 0; i < endTiles.size(); i++) {
            effects.add(BoardSquareEffect.PLAIN);
        }

        return calculateWordScore(allTiles, effects);
    }

    public int scoreCrossWord(GameBoard board, LetterTile tile, int row, int col, Direction moveDirection) {
        Direction crossDir = moveDirection.perpendicular();
        List<LetterTile> startTiles = getStartTiles(board, row, col, crossDir);
        int endRow = (crossDir == Direction.VERTICAL) ? row + 1 : row;
        int endCol = (crossDir == Direction.HORIZONTAL) ? col + 1 : col;
        List<LetterTile> endTiles = getEndTiles(board, endRow, endCol, crossDir);

        if (startTiles.isEmpty() && endTiles.isEmpty()) {
            return 0;
        }

        List<LetterTile> crossTiles = new ArrayList<>(startTiles);
        crossTiles.add(tile);
        crossTiles.addAll(endTiles);

        if (crossTiles.size() < 2) return 0;

        List<BoardSquareEffect> effects = new ArrayList<>();
        for (int i = 0; i < startTiles.size(); i++) {
            effects.add(BoardSquareEffect.PLAIN);
        }
        effects.add(board.getSquare(row, col).getEffect());
        for (int i = 0; i < endTiles.size(); i++) {
            effects.add(BoardSquareEffect.PLAIN);
        }

        return calculateWordScore(crossTiles, effects);
    }

    public List<LetterTile> getStartTiles(GameBoard board, int row, int col, Direction direction) {
        if (direction == null) throw new InvalidMoveException("Direction cannot be null");
        List<LetterTile> list = new ArrayList<>();

        if (direction == Direction.HORIZONTAL) {
            int currentCol = col - 1;
            while (currentCol >= 0 && board.getSquare(row, currentCol) != null && board.getSquare(row, currentCol).isFilled()) {
                list.add(board.getSquare(row, currentCol).getTile());
                currentCol--;
            }
        } else {
            int currentRow = row - 1;
            while (currentRow >= 0 && board.getSquare(currentRow, col) != null && board.getSquare(currentRow, col).isFilled()) {
                list.add(board.getSquare(currentRow, col).getTile());
                currentRow--;
            }
        }

        Collections.reverse(list);
        return list;
    }

    public List<LetterTile> getEndTiles(GameBoard board, int row, int col, Direction direction) {
        if (direction == null) throw new InvalidMoveException("Direction cannot be null");
        List<LetterTile> list = new ArrayList<>();

        if (direction == Direction.HORIZONTAL) {
            if (row < 0 || row >= board.getNumRows()) return list;
            int currentCol = col;
            while (currentCol < board.getNumCols() && board.getSquare(row, currentCol) != null && board.getSquare(row, currentCol).isFilled()) {
                list.add(board.getSquare(row, currentCol).getTile());
                currentCol++;
            }
        } else {
            if (col < 0 || col >= board.getNumCols()) return list;
            int currentRow = row;
            while (currentRow < board.getNumRows() && board.getSquare(currentRow, col) != null && board.getSquare(currentRow, col).isFilled()) {
                list.add(board.getSquare(currentRow, col).getTile());
                currentRow++;
            }
        }

        return list;
    }

    public boolean validateBingo(Submission submission) {
        return submission != null && submission.tiles() != null && submission.tiles().size() == 7;
    }

    /**
     * Sums the scores of all words created in a turn and applies the bingo bonus if earned.
     *
     * @param wordScores List of scores for each word formed this turn.
     * @param isBingo True if the player used all 7 tiles in a single turn.
     * @return Total turn score.
     */
    public int calculateTotalScore(List<Integer> wordScores, boolean isBingo) {
        int total = 0;
        if (wordScores != null) {
            for (int ws : wordScores) {
                total += ws;
            }
        }
        if (isBingo) {
            total += BINGO_BONUS;
        }
        return total;
    }

    /**
     * Backwards-compatible score method for a single sequence of tiles and effects.
     */
    public int calculateScore(List<LetterTile> tiles, List<BoardSquareEffect> effects, boolean isBingo) {
        int wordScore = calculateWordScore(tiles, effects);
        return isBingo ? wordScore + BINGO_BONUS : wordScore;
    }
}
