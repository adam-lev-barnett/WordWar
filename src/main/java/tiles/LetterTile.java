package tiles;

import exceptions.InvalidPointValueException;
import exceptions.InvalidTileException;

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
        letter = Character.toUpperCase(letter);
        if ((letter < 'A' || letter > 'Z') && letter != ' ') {
            throw new InvalidTileException();
        }
        this.letter = letter;
    }

}




