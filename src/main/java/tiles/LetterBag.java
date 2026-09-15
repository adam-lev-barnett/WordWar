package tiles;

import exceptions.EmptyBagException;
import exceptions.InvalidPointValueException;
import exceptions.InvalidTileException;
import player.Player;

import java.util.*;

/**
 * Randomized bag of letters populated with customizable quantities of tiles
 * Relies on queue interacting with arrayList for tile population, randomization, and removal from bag when played to board
 */
public class LetterBag {
    private static final Map<Character, Integer> lettersDistribution = new HashMap<Character, Integer>();
    private final Queue<LetterTile> bag = new ArrayDeque<>();
    private final List<LetterTile> tiles = new ArrayList<>();

    /** Maximum number of tiles allowed in a given player's hand*/
    private final static int MAX_TILES = 7;


    // Counts of each letter for populating bag of tiles
    static {
        lettersDistribution.put('A', 9);
        lettersDistribution.put('B', 2);
        lettersDistribution.put('C', 2);
        lettersDistribution.put('D', 4);
        lettersDistribution.put('E', 12);
        lettersDistribution.put('F', 2);
        lettersDistribution.put('G', 3);
        lettersDistribution.put('H', 3);
        lettersDistribution.put('I', 8);
        lettersDistribution.put('J', 1);
        lettersDistribution.put('K', 1);
        lettersDistribution.put('L', 4);
        lettersDistribution.put('M', 2);
        lettersDistribution.put('N', 5);
        lettersDistribution.put('O', 8);
        lettersDistribution.put('P', 2);
        lettersDistribution.put('Q', 1);
        lettersDistribution.put('R', 6);
        lettersDistribution.put('S', 5);
        lettersDistribution.put('T', 6);
        lettersDistribution.put('U', 3);
        lettersDistribution.put('V', 2);
        lettersDistribution.put('W', 2);
        lettersDistribution.put('X', 1);
        lettersDistribution.put('Y', 2);
        lettersDistribution.put('Z', 1);
        lettersDistribution.put(' ', 3);
    }

    // Upon instantiation, letter tiles are automatically generated, and the bag is filled
    public LetterBag () {
        populateLetterTiles();
        fillBag();
    }

    // Create each letter tile based on quantities in letter distribution map via TileFactory by passing in the associated char
    private void populateLetterTiles() {
        for (char c : lettersDistribution.keySet()) {
            for (int i = lettersDistribution.get(c) - 1; i >= 0; i--) {
                try {
                    tiles.add(TileFactory.createLetterTile(c));
                } catch (InvalidTileException | InvalidPointValueException e) {
                    throw new RuntimeException("Illegal letter tile found");
                }
            }
        }
    }

    private void fillBag() {
        // Shuffle letters before adding to bag
        bag.addAll(tiles);

        // Empty the list so it can be populated again and shuffled with remaining tiles if needed
        tiles.clear();
    }

    public void shuffleTiles() {
        // Needed if letters are returned to the bag so they don't just end up at the back of the queue
        // Tile shouldn't just go to the back of the queue; we need to maintain randomization!
        while (!bag.isEmpty()) {
            tiles.add(bag.poll());
        }
        Collections.shuffle(tiles);
        fillBag();
    }

    public void drawTiles(Player player) {
        if (tiles.isEmpty()) throw new EmptyBagException("Cannot draw from empty bag");
        List<LetterTile> hand = player.getHand();
        int toDraw = MAX_TILES - hand.size();
        while (!bag.isEmpty() && toDraw > 0) {
            hand.add(bag.poll());
            toDraw--;
        }
    }

    public void returnTile(List<LetterTile> tiles) throws InvalidTileException {
        if (tiles == null) throw new InvalidTileException();
        bag.addAll(tiles);
        shuffleTiles();
    }

    /** Queried as players draw tiles from the bag to prevent exceptions.
     * Abstraction of the actual tile deque because draw tile logic will be in player turn class.*/
    public boolean isEmpty() {
        return bag.isEmpty();
    }


}
