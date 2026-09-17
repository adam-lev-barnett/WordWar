package tiles;

import exceptions.InvalidPointValueException;
import exceptions.InvalidTileException;

public abstract class TileFactory {

    public static LetterTile createLetterTile(char letter) throws InvalidTileException, InvalidPointValueException {
        letter = Character.toUpperCase(letter);
        // PointMap throws InvalidTileException for anything that isn't a playable letter or the blank
        return new LetterTile(letter, PointMap.get(letter));
    }

}
