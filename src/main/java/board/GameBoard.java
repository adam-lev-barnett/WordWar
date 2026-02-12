package board;

import exceptions.InvalidBoardSquareException;

import static board.BoardSquareEffect.*;

// Hard-coded dimensions for now to mimic Scrabble board
public class GameBoard {

    private BoardSquare[][] gameBoard = new BoardSquare[15][15];

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


    }




}
