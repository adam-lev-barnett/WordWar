package board;

import mechanics.pieceplacement.Direction;
import tiles.LetterTile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordsOnBoard {
    private Map<Integer[], String> words = new HashMap<>();

    public WordsOnBoard() {}

    private class BoardWord {
        private final int[] startSquare = new int[2];
        private final int[] endSquare = new int[2];
        Direction direction;
        List<LetterTile> word =  new ArrayList<>();




    }


}
