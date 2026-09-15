package tiles;

import exceptions.InvalidPointValueException;
import exceptions.InvalidTileException;

public abstract class TileFactory {

    public static LetterTile createLetterTile(char letter) throws InvalidTileException, InvalidPointValueException {
        letter = Character.toUpperCase(letter);
        switch (letter) {
            case 'A' -> {return new LetterTile('A', 1);}
            case 'B' -> {return new LetterTile('B', 3);}
            case 'C' -> {return new LetterTile('C', 3);}
            case 'D' -> {return new LetterTile('D', 2);}
            case 'E' -> {return new LetterTile('E', 1);}
            case 'F' -> {return new LetterTile('F', 4);}
            case 'G' -> {return new LetterTile('G', 2);}
            case 'H' -> {return new LetterTile('H', 4);}
            case 'I' -> {return new LetterTile('I', 1);}
            case 'J' -> {return new LetterTile('J', 8);}
            case 'K' -> {return new LetterTile('K', 5);}
            case 'L' -> {return new LetterTile('L', 1);}
            case 'M' -> {return new LetterTile('M', 3);}
            case 'N' -> {return new LetterTile('N', 1);}
            case 'O' -> {return new LetterTile('O', 1);}
            case 'P' -> {return new LetterTile('P', 3);}
            case 'Q' -> {return new LetterTile('Q', 10);}
            case 'R' -> {return new LetterTile('R', 1);}
            case 'S' -> {return new LetterTile('S', 1);}
            case 'T' -> {return new LetterTile('T', 1);}
            case 'U' -> {return new LetterTile('U', 1);}
            case 'V' -> {return new LetterTile('V', 4);}
            case 'W' -> {return new LetterTile('W', 4);}
            case 'X' -> {return new LetterTile('X', 8);}
            case 'Y' -> {return new LetterTile('Y', 4);}
            case 'Z' -> {return new LetterTile('Z', 10);}
            case ' ' -> {return new LetterTile(' ', 0);}
            default -> {throw new InvalidTileException();}
        }
    }

}
