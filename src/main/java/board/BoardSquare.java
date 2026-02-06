package board;

import exceptions.InvalidBoardSquareException;

public class BoardSquare {
    boolean filled = false;
    BoardSquareEffect effect;
    Coordinate coordinate;
    LetterTile tile = null;

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public BoardSquareEffect getEffect() {
        return effect;
    }

    public void setEffect(BoardSquareEffect effect) throws InvalidBoardSquareException {
        if (effect == null) throw new InvalidBoardSquareException("Board square must have BoardSquareEffect");
        this.effect = effect;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    // Only two characters, "{letter}{number}"
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public LetterTile getTile() {
        return tile;
    }

    public void setTile(LetterTile tile) {
        this.tile = tile;
    }



}
