package board.tiles;

import exceptions.InvalidPointValueException;
import exceptions.InvalidTileException;

import java.util.Objects;

/** Tile representing a capital letter with a point value determined by the TileFactory*/
public class LetterTile {

    private char letter;
    private int pointValue;
    /** Only becomes true when the tile is part of an accepted submission.
     * Still false if player is placing the tile before submitting their move  */
    private boolean inPlay;


    public LetterTile(char letter, int pointValue) throws InvalidTileException, InvalidPointValueException {
        setLetter(letter);
        setPointValue(pointValue);
        inPlay = false;
    }

    public int getPointValue() {
        return pointValue;
    }

    // Package protected so that only TileFactory has access
    void setPointValue(int pointValue) throws InvalidPointValueException {
        if (pointValue < 0 || pointValue > 10) throw new InvalidPointValueException();
        this.pointValue = pointValue;
    }

    public char getLetter() {
        return letter;
    }

    // By default, letters will be uppercase
    public void setLetter(char letter) throws InvalidTileException {
        letter = Character.toUpperCase(letter);
        if ((letter < 'A' || letter > 'Z') && letter != ' ') {
            throw new InvalidTileException();
        }
        this.letter = letter;
    }

    public boolean isInPlay() {
        return inPlay;
    }

    /** True when tile is submitted and accepted;
     * only toggled off if tile is removed from the board.
     * Package-protected because the {@link board.GameBoard} needs to change the status*/
    void toggleInPlay(boolean inPlay) {
        this.inPlay = inPlay;
    }

    /** Equality doesn't account for a tile being in play*/
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LetterTile that = (LetterTile) o;
        return letter == that.letter && pointValue == that.pointValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, pointValue);
    }
}




