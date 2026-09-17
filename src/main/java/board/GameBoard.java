package board;


import exceptions.InvalidBoardSquareException;
import exceptions.InvalidMoveException;
import mechanics.dictionary.Dictionary;
import mechanics.pieceplacement.Direction;
import mechanics.turn.Submission;
import mechanics.turn.scoring.ScoreCalculator;
import tiles.LetterTile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static board.BoardSquareEffect.*;

// Hard-coded dimensions for now to mimic Scrabble board
public class GameBoard {

    private final BoardSquare[][] gameBoard = new BoardSquare[15][15];

    public GameBoard() {
        gameBoard[0][0] = new BoardSquare(TRIPLE_WORD_SCORE);
        gameBoard[0][1] = new BoardSquare(PLAIN);
        gameBoard[0][2] = new BoardSquare(PLAIN);
        gameBoard[0][3] = new BoardSquare(TILE_SWAP);
        gameBoard[0][4] = new BoardSquare(PLAIN);
        gameBoard[0][5] = new BoardSquare(PLAIN);
        gameBoard[0][6] = new BoardSquare(PLAIN);
        gameBoard[0][7] = new BoardSquare(REMOVE_WORD);
        gameBoard[0][8] = new BoardSquare(PLAIN);
        gameBoard[0][9] = new BoardSquare(PLAIN);
        gameBoard[0][10] = new BoardSquare(PLAIN);
        gameBoard[0][11] = new BoardSquare(TILE_SWAP);
        gameBoard[0][12] = new BoardSquare(PLAIN);
        gameBoard[0][13] = new BoardSquare(PLAIN);
        gameBoard[0][14] = new BoardSquare(TRIPLE_WORD_SCORE);

        gameBoard[1][0] = new BoardSquare(PLAIN);
        gameBoard[1][1] = new BoardSquare(DOUBLE_WORD_SCORE);
        gameBoard[1][2] = new BoardSquare(PLAIN);
        gameBoard[1][3] = new BoardSquare(PLAIN);
        gameBoard[1][4] = new BoardSquare(PLAIN);
        gameBoard[1][5] = new BoardSquare(TRIPLE_LETTER_SCORE);
        gameBoard[1][6] = new BoardSquare(PLAIN);
        gameBoard[1][7] = new BoardSquare(PLAIN);
        gameBoard[1][8] = new BoardSquare(PLAIN);
        gameBoard[1][9] = new BoardSquare(TRIPLE_LETTER_SCORE);
        gameBoard[1][10] = new BoardSquare(PLAIN);
        gameBoard[1][11] = new BoardSquare(PLAIN);
        gameBoard[1][12] = new BoardSquare(PLAIN);
        gameBoard[1][13] = new BoardSquare(DOUBLE_WORD_SCORE);
        gameBoard[1][14] = new BoardSquare(PLAIN);

        gameBoard[2][0] = new BoardSquare(PLAIN);
        gameBoard[2][1] = new BoardSquare(PLAIN);
        gameBoard[2][2] = new BoardSquare(DOUBLE_WORD_SCORE);
        gameBoard[2][3] = new BoardSquare(PLAIN);
        gameBoard[2][4] = new BoardSquare(PLAIN);
        gameBoard[2][5] = new BoardSquare(PLAIN);
        gameBoard[2][6] = new BoardSquare(DOUBLE_LETTER_SCORE);
        gameBoard[2][7] = new BoardSquare(PLAIN);
        gameBoard[2][8] = new BoardSquare(DOUBLE_LETTER_SCORE);
        gameBoard[2][9] = new BoardSquare(PLAIN);
        gameBoard[2][10] = new BoardSquare(PLAIN);
        gameBoard[2][11] = new BoardSquare(PLAIN);
        gameBoard[2][12] = new BoardSquare(DOUBLE_WORD_SCORE);
        gameBoard[2][13] = new BoardSquare(PLAIN);
        gameBoard[2][14] = new BoardSquare(PLAIN);

        gameBoard[3][0] = new BoardSquare(TILE_SWAP);
        gameBoard[3][1] = new BoardSquare(PLAIN);
        gameBoard[3][2] = new BoardSquare(PLAIN);
        gameBoard[3][3] = new BoardSquare(HALVE_LETTERS);
        gameBoard[3][4] = new BoardSquare(PLAIN);
        gameBoard[3][5] = new BoardSquare(PLAIN);
        gameBoard[3][6] = new BoardSquare(PLAIN);
        gameBoard[3][7] = new BoardSquare(TILE_SWAP);
        gameBoard[3][8] = new BoardSquare(PLAIN);
        gameBoard[3][9] = new BoardSquare(PLAIN);
        gameBoard[3][10] = new BoardSquare(PLAIN);
        gameBoard[3][11] = new BoardSquare(HALVE_LETTERS);
        gameBoard[3][12] = new BoardSquare(PLAIN);
        gameBoard[3][13] = new BoardSquare(PLAIN);
        gameBoard[3][14] = new BoardSquare(TILE_SWAP);

        gameBoard[4][0] = new BoardSquare(PLAIN);
        gameBoard[4][1] = new BoardSquare(PLAIN);
        gameBoard[4][2] = new BoardSquare(PLAIN);
        gameBoard[4][3] = new BoardSquare(PLAIN);
        gameBoard[4][4] = new BoardSquare(DOUBLE_WORD_SCORE);
        gameBoard[4][5] = new BoardSquare(PLAIN);
        gameBoard[4][6] = new BoardSquare(PLAIN);
        gameBoard[4][7] = new BoardSquare(PLAIN);
        gameBoard[4][8] = new BoardSquare(PLAIN);
        gameBoard[4][9] = new BoardSquare(PLAIN);
        gameBoard[4][10] = new BoardSquare(DOUBLE_WORD_SCORE);
        gameBoard[4][11] = new BoardSquare(PLAIN);
        gameBoard[4][12] = new BoardSquare(PLAIN);
        gameBoard[4][13] = new BoardSquare(PLAIN);
        gameBoard[4][14] = new BoardSquare(PLAIN);

        gameBoard[5][0] = new BoardSquare(PLAIN);
        gameBoard[5][1] = new BoardSquare(TRIPLE_LETTER_SCORE);
        gameBoard[5][2] = new BoardSquare(PLAIN);
        gameBoard[5][3] = new BoardSquare(PLAIN);
        gameBoard[5][4] = new BoardSquare(PLAIN);
        gameBoard[5][5] = new BoardSquare(TRIPLE_LETTER_SCORE);
        gameBoard[5][6] = new BoardSquare(PLAIN);
        gameBoard[5][7] = new BoardSquare(PLAIN);
        gameBoard[5][8] = new BoardSquare(PLAIN);
        gameBoard[5][9] = new BoardSquare(TRIPLE_LETTER_SCORE);
        gameBoard[5][10] = new BoardSquare(PLAIN);
        gameBoard[5][11] = new BoardSquare(PLAIN);
        gameBoard[5][12] = new BoardSquare(PLAIN);
        gameBoard[5][13] = new BoardSquare(TRIPLE_LETTER_SCORE);
        gameBoard[5][14] = new BoardSquare(PLAIN);

        gameBoard[6][0] = new BoardSquare(PLAIN);
        gameBoard[6][1] = new BoardSquare(PLAIN);
        gameBoard[6][2] = new BoardSquare(DOUBLE_LETTER_SCORE);
        gameBoard[6][3] = new BoardSquare(PLAIN);
        gameBoard[6][4] = new BoardSquare(PLAIN);
        gameBoard[6][5] = new BoardSquare(PLAIN);
        gameBoard[6][6] = new BoardSquare(DOUBLE_LETTER_SCORE);
        gameBoard[6][7] = new BoardSquare(PLAIN);
        gameBoard[6][8] = new BoardSquare(DOUBLE_LETTER_SCORE);
        gameBoard[6][9] = new BoardSquare(PLAIN);
        gameBoard[6][10] = new BoardSquare(PLAIN);
        gameBoard[6][11] = new BoardSquare(PLAIN);
        gameBoard[6][12] = new BoardSquare(DOUBLE_LETTER_SCORE);
        gameBoard[6][13] = new BoardSquare(PLAIN);
        gameBoard[6][14] = new BoardSquare(PLAIN);

        gameBoard[7][0] = new BoardSquare(REMOVE_WORD);
        gameBoard[7][1] = new BoardSquare(PLAIN);
        gameBoard[7][2] = new BoardSquare(PLAIN);
        gameBoard[7][3] = new BoardSquare(TILE_SWAP);
        gameBoard[7][4] = new BoardSquare(PLAIN);
        gameBoard[7][5] = new BoardSquare(PLAIN);
        gameBoard[7][6] = new BoardSquare(PLAIN);
        gameBoard[7][7] = new BoardSquare(DOUBLE_WORD_SCORE); // CENTER OF BOARD
        gameBoard[7][8] = new BoardSquare(PLAIN);
        gameBoard[7][9] = new BoardSquare(PLAIN);
        gameBoard[7][10] = new BoardSquare(PLAIN);
        gameBoard[7][11] = new BoardSquare(SHUFFLE_WORD);
        gameBoard[7][12] = new BoardSquare(PLAIN);
        gameBoard[7][13] = new BoardSquare(PLAIN);
        gameBoard[7][14] = new BoardSquare(REMOVE_WORD);

        // Mirror top of board
        int j = 6;
        for (int i = 8; i < gameBoard.length && j >= 0; i++) {
            gameBoard[i] = Arrays.copyOf(gameBoard[j], gameBoard[j].length);
            j--;
        }

    }




    // Test to make sure that the gameboard is constructed properly
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (BoardSquare[] bs : this.gameBoard) {
            sb.append(Arrays.toString(bs)).append("\n");
        }

        return sb.toString();
    }

    public boolean validateMove(Submission submission) {

        if (submission == null) return false;

        int row = submission.row();
        int col = submission.col();
        // Can't play tiles beyond bounds of board
        if (gameBoard[0].length - row < 0 && gameBoard.length - col < 0) return false;

        if (Dictionary.INSTANCE.validateWord(buildMainWord(submission))) {
            /*  Initialize list of covered bonus effects to send to score calculator to process
                Each effect list index corresponds to the index of the tile placed on it
             */
            List<BoardSquareEffect> accumulatedEffects = new ArrayList<>();
            if (submission.direction() == Direction.HORIZONTAL) {
                int currentCol = col;
                for (LetterTile tile : submission.tiles()) {
                    gameBoard[row][col].setTile(tile);
                    accumulatedEffects.add(gameBoard[row][col].getEffect());
                    currentCol++;
                }
            }

            boolean isBingo = validateBingo(submission);
            int score = ScoreCalculator.INSTANCE.calculateTotalScore(submission.tiles(), accumulatedEffects, validateBingo();
        }
    }

    public void placeWord(Submission submission) {
        int row = submission.row();
        int col = submission.col();
        if (submission.direction() == Direction.HORIZONTAL) {
            if (submission.tiles().size() >= gameBoard[0].length) throw new InvalidMoveException("Move will exceed board bounds");
            for (LetterTile tile : submission.tiles()) {
                gameBoard[row][col].setTile(tile);
                col++;
            }
        }
        else {
            if (submission.tiles().size() >= gameBoard.length) throw new InvalidMoveException("Move will exceed board bounds");
            for (LetterTile tile : submission.tiles()) {
                gameBoard[row][col].setTile(tile);
                row++;
            }
        }
    }

    //TODO
    private boolean validateBingo(Submission submission) {
        return false;
    }

    private String buildMainWord(Submission submission) {
        int row = submission.row();
        int col = submission.col();
        int numTiles = submission.tiles().size();

        StringBuilder sb = new StringBuilder();

        // Beginning of word
        sb.append(getStartOfWord(row, col, submission.direction()));

        // Tiles
        for (LetterTile tile : submission.tiles()) {
            sb.append(tile.getLetter());
        }

        // End of word
        if (submission.direction() == Direction.HORIZONTAL) sb.append(getEndOfWord(row, col + numTiles, submission.direction()));
        else sb.append(getEndOfWord(row + numTiles, col, submission.direction()));

        return sb.toString();
    }

    private List<String> collectWords(Submission submission) {
        List<String> words = new ArrayList<>();
        int row = submission.row();
        int col = submission.col();
        StringBuilder sb = new StringBuilder();

        Direction oppositeDir = submission.direction() == Direction.HORIZONTAL ? Direction.VERTICAL : Direction.HORIZONTAL;
        // The crossing words' prefixes need to be checked in the opposite direction of the main word being placed
        for (LetterTile tile : submission.tiles()) {
            sb.append(getStartOfWord(row, col, oppositeDir));
            sb.append(tile.getLetter());
            sb.append(getEndOfWord(row, col, oppositeDir));
            words.add(sb.toString());
            // Re-use SB
            sb.setLength(0);
            if (submission.direction() == Direction.HORIZONTAL) col++;
            else row++;
        }

        return words;

    }




    private String getStartOfWord(int row, int col, Direction direction) {

        if (direction == null) throw new InvalidMoveException("Direction cannot be null");
        StringBuilder sb = new StringBuilder();

        if (direction == Direction.HORIZONTAL) {
            int currentCol = col;
            while (currentCol > 0 && gameBoard[row][currentCol].isFilled()) {
                sb.append(gameBoard[row][currentCol].getTile().getLetter());
                currentCol--;
            }
        }
        else {
            int currentRow = row;
            while (currentRow > 0 && gameBoard[currentRow][col].isFilled()) {
                sb.append(gameBoard[currentRow][col].getTile().getLetter());
                currentRow--;
            }
        }

        // Because we backtracked the tiles, we need to reverse the string builder so that the word is built properly
        sb.reverse();
        return sb.toString();
    }

    private String getEndOfWord(int row, int col, Direction direction) {

        if (direction == null) throw new InvalidMoveException("Direction cannot be null");
        if (row < 0 ||
            row >= gameBoard.length ||
            col < 0 ||
            col >= gameBoard[row].length)
            throw new InvalidMoveException("Invalid row or column"
        );

        StringBuilder sb = new StringBuilder();

        if (direction == Direction.HORIZONTAL) {
            int currentCol = col;
            while (currentCol < gameBoard[row].length && gameBoard[row][currentCol].isFilled()) {
                sb.append(gameBoard[row][currentCol].getTile().getLetter());
                currentCol++;
            }
        }
        else {
            int currentRow = row;
            while (currentRow < gameBoard.length && gameBoard[currentRow][col].isFilled()) {
                sb.append(gameBoard[currentRow][col].getTile().getLetter());
                currentRow++;
            }
        }

        return sb.toString();
    }

    private boolean hasAdjacentTiles(int row, int col, Direction direction) throws InvalidBoardSquareException {
        if (direction == null) throw new InvalidMoveException("Direction cannot be null");
        if (row < 0 || row >= gameBoard.length || col < 0 || col >= gameBoard[row].length) throw new InvalidBoardSquareException("Square is out of bounds");

        if (direction == Direction.HORIZONTAL) {

        }


    }

    // Test gameBoard prints correctly
    public static void main(String[] args) {
        GameBoard gameBoardTest = new GameBoard();
        System.out.println(gameBoardTest);

    }








}
