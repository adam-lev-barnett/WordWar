package tiles;

import androidx.annotation.NonNull;
import exceptions.InvalidPointValueException;
import exceptions.InvalidTileException;
import java.util.Objects;

/** Tile representing a capital letter with a point value determined by the TileFactory*/
public class LetterTile {

    private char letter;
    private int pointValue;


    public LetterTile(char letter, int pointValue) throws InvalidTileException, InvalidPointValueException {
        setLetter(letter);
        setPointValue(pointValue);
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
        char upper = Character.toUpperCase(letter);
        if ((upper < 'A' || upper > 'Z') && letter != ' ') {
            throw new InvalidTileException();
        }
        this.letter = upper;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LetterTile that = (LetterTile) o;
        return letter == that.letter && pointValue == that.pointValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, pointValue);
    }

    @NonNull
    @Override
    public String toString() {
        return letter + "(" + pointValue + ")";
    }

}
