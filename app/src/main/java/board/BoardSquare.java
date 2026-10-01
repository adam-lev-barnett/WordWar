package board;

import androidx.annotation.NonNull;

import exceptions.InvalidBoardSquareException;
import tiles.LetterTile;

/** Main component that makes up the game board.
 *  Each square has an associated effect (or 'plain' effect) that affects the turn.
 *  List of effects is represented by the BoardSquareEffect enum*/
public class BoardSquare {
    private boolean filled = false;
    private BoardSquareEffect effect;
    private LetterTile tile = null;

    public BoardSquare(BoardSquareEffect effect) {
        this.effect = (effect != null) ? effect : BoardSquareEffect.PLAIN;
    }

    public boolean isFilled() {
        return filled || tile != null;
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
        this.filled = (tile != null);
    }

    @NonNull
    @Override
    public String toString() {
        if (tile != null) {
            return tile.getLetter() + "[" + effect + "]";
        }
        return this.effect.toString();
    }

}
