package tiles;

import exceptions.InvalidTileException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/** Point values for every playable tile, keyed by uppercase letter (' ' is the blank tile). */
public enum PointMap {
    INSTANCE;

    private static final Map<Character, Integer> pointMap;

    static {
        Map<Character, Integer> points = new HashMap<>();
        points.put('A', 1);
        points.put('B', 3);
        points.put('C', 3);
        points.put('D', 2);
        points.put('E', 1);
        points.put('F', 4);
        points.put('G', 2);
        points.put('H', 4);
        points.put('I', 1);
        points.put('J', 8);
        points.put('K', 5);
        points.put('L', 1);
        points.put('M', 3);
        points.put('N', 1);
        points.put('O', 1);
        points.put('P', 3);
        points.put('Q', 10);
        points.put('R', 1);
        points.put('S', 1);
        points.put('T', 1);
        points.put('U', 1);
        points.put('V', 4);
        points.put('W', 4);
        points.put('X', 8);
        points.put('Y', 4);
        points.put('Z', 10);
        points.put(' ', 0);
        pointMap = Collections.unmodifiableMap(points);
    }

    /** Returns the point value for the given letter, throwing if the character is not playable. */
    public static int get(char letter) throws InvalidTileException {
        Integer points = pointMap.get(Character.toUpperCase(letter));
        if (points == null) throw new InvalidTileException();
        return points;
    }

}
