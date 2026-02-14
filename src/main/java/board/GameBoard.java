package board;


import java.util.Arrays;

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

    // Test gameBoard prints correctly
    public static void main(String[] args) {
        GameBoard gameBoardTest = new GameBoard();
        System.out.println(gameBoardTest);

    }




}
