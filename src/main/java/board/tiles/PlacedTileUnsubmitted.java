package board.tiles;

import exceptions.InvalidPointValueException;
import exceptions.InvalidTileException;
import mechanics.boardconfig.BoardConfig;

public class PlacedTileUnsubmitted extends LetterTile {

    int row;
    int col;

    public PlacedTileUnsubmitted(char letter, int pointValue, int row, int col) throws InvalidTileException, InvalidPointValueException {
        super(letter, pointValue);
        if (BoardConfig.validateSquare(row, col)
        this.col = col;
    }


}
