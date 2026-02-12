package board;

import exceptions.InvalidBoardSquareException;

public class BoardSquare {
    boolean filled = false;
    BoardSquareEffect effect;
    LetterTile tile = null;

    public BoardSquare(BoardSquareEffect effect) {
        this.effect = effect;
    }

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

    public LetterTile getTile() {
        return tile;
    }

    public void setTile(LetterTile tile) {
        this.tile = tile;
    }



}
