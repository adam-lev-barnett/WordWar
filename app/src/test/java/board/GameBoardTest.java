package board;

import exceptions.InvalidMoveException;
import exceptions.InvalidPointValueException;
import exceptions.InvalidTileException;
import mechanics.dictionary.Dictionary;
import mechanics.pieceplacement.Direction;
import mechanics.turn.Submission;
import org.junit.Before;
import org.junit.Test;
import tiles.LetterTile;
import tiles.TileFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GameBoardTest {

    private GameBoard board;

    @Before
    public void setUp() {
        board = new GameBoard();
        // Ensure standard test words exist in dictionary
        Dictionary.INSTANCE.addWords(Arrays.asList("FAR", "OF", "OX", "CAT", "CATS", "AT", "TO", "DO", "IN", "PIN", "PINS"));
    }

    private List<LetterTile> makeTiles(String letters) {
        List<LetterTile> tiles = new ArrayList<>();
        for (char c : letters.toCharArray()) {
            try {
                tiles.add(TileFactory.createLetterTile(c));
            } catch (InvalidTileException | InvalidPointValueException e) {
                throw new RuntimeException(e);
            }
        }
        return tiles;
    }

    private LetterTile makeTile(char letter) {
        try {
            return TileFactory.createLetterTile(letter);
        } catch (InvalidTileException | InvalidPointValueException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testFirstMoveMustCoverCenter() {
        // Move not covering (7, 7) must fail
        Submission offCenter = new Submission(makeTiles("CAT"), 0, 1, Direction.HORIZONTAL);
        assertFalse(board.validateMove(offCenter));

        // Move covering (7, 7) should succeed
        // (7, 6)='C', (7, 7)='A', (7, 8)='T'
        Submission centerMove = new Submission(makeTiles("CAT"), 7, 6, Direction.HORIZONTAL);
        assertTrue(board.validateMove(centerMove));
    }

    @Test
    public void testFirstMoveDoubleWordScoreOnCenter() {
        // Center square (7, 7) is DOUBLE_WORD_SCORE
        // C(3) at (7, 6), A(1) at (7, 7) [2x word], T(1) at (7, 8)
        // Base sum = 3 + 1 + 1 = 5
        // Word multiplier = 2
        // Score = 10
        Submission centerMove = new Submission(makeTiles("CAT"), 7, 6, Direction.HORIZONTAL);
        int score = board.placeWord(centerMove);
        assertEquals(10, score);
        assertFalse(board.isBoardEmpty());
    }

    @Test
    public void testCrossWordDetectionAndDualBonusScoring() {
        Dictionary.INSTANCE.addWords(Arrays.asList("OR", "AT", "TO"));

        // Turn 1: Place "OR" horizontally at row 7, cols 6-7 (covering center 7,7)
        // (7, 6)=O(1), (7, 7)=R(1) [center 2x word]
        Submission move1 = new Submission(makeTiles("OR"), 7, 6, Direction.HORIZONTAL);
        board.placeWord(move1);

        // Square (6, 6) is DOUBLE_LETTER_SCORE!
        assertEquals(BoardSquareEffect.DOUBLE_LETTER_SCORE, board.getSquare(6, 6).getEffect());

        // Turn 2: Place "AT" horizontally at row 6, cols 5-6
        // (6, 5)=A(1) on PLAIN
        // (6, 6)=T(1) on DOUBLE_LETTER_SCORE
        // This forms:
        // 1. Main word: "AT" horizontally
        //    A is on PLAIN: 1
        //    T is on DOUBLE_LETTER_SCORE: 1 * 2 = 2
        //    Score of AT = 1 + 2 = 3
        // 2. Vertical cross-word: "TO" vertically at col 6!
        //    (6, 6)=T(1) on DOUBLE_LETTER_SCORE -> 1 * 2 = 2
        //    (7, 6)=O(1) existing tile on board -> face value = 1 (no multipliers)
        //    Score of TO = 2 + 1 = 3
        // Total score = 3 + 3 = 6!
        // The tile 'T' on DOUBLE_LETTER_SCORE counted in BOTH words!
        Submission move2 = new Submission(makeTiles("AT"), 6, 5, Direction.HORIZONTAL);
        assertTrue(board.validateMove(move2));

        String crossWord = board.buildCrossWord(makeTile('T'), 6, 6, Direction.HORIZONTAL);
        assertEquals("TO", crossWord);

        int score2 = board.placeWord(move2);
        assertEquals(6, score2);
    }

    @Test
    public void testExistingTilesDoNotReactivateBonuses() {
        // Place move across center
        Submission move1 = new Submission(makeTiles("CAT"), 7, 6, Direction.HORIZONTAL);
        board.placeWord(move1);

        // Turn 2: Add 'S' to make "CATS"
        // Placed tile is S at (7, 9)
        // Existing tiles C, A, T are at face value (Center double word is NOT re-triggered)
        Submission move2 = new Submission(makeTiles("S"), 7, 9, Direction.HORIZONTAL);
        assertTrue(board.validateMove(move2));
        // C(3) + A(1) + T(1) + S(1) = 6 points (no word multiplier because center was used in turn 1)
        int score2 = board.placeWord(move2);
        assertEquals(6, score2);
    }

    @Test
    public void testBingoBonus() {
        // 7-letter move covering center
        Dictionary.INSTANCE.addWord("PAINTER");
        Submission bingoMove = new Submission(makeTiles("PAINTER"), 7, 4, Direction.HORIZONTAL);
        assertTrue(board.validateMove(bingoMove));

        int totalScore = board.calculateTotalScore(bingoMove);
        // Center (7, 7) has DOUBLE_WORD_SCORE
        // Letters: P(3) + A(1) + I(1) + N(1) [at 7,7 2x word] + T(1) + E(1) + R(1) = 9
        // Multiplied by 2 = 18
        // Bingo bonus = +50
        // Total = 68
        assertEquals(68, totalScore);
    }

    @Test
    public void testVerticalMoveAndHorizontalCrossWord() {
        Dictionary.INSTANCE.addWords(Arrays.asList("CAT", "TO"));

        // Turn 1: Place "CAT" horizontally at (7, 6) through (7, 8)
        board.placeWord(new Submission(makeTiles("CAT"), 7, 6, Direction.HORIZONTAL));

        // Turn 2: Place 'O' vertically below 'T' at (8, 8)
        // Square (8, 8) is DOUBLE_LETTER_SCORE (mirrors row 6, col 8)
        // Main vertical word is "TO" at col 8, rows 7-8:
        // T(1) at (7,8) [existing, face value]
        // O(1) at (8,8) [newly placed on DOUBLE_LETTER_SCORE: 1 * 2 = 2]
        // Score = 1 + 2 = 3
        Submission move2 = new Submission(makeTiles("O"), 8, 8, Direction.VERTICAL);
        assertTrue(board.validateMove(move2));
        int score = board.placeWord(move2);
        assertEquals(3, score);
    }

    @Test
    public void testDoubleWordAppliedToBothWords() {
        Dictionary.INSTANCE.addWords(Arrays.asList("PIN", "PI"));

        // BoardSquare at (4, 4) is DOUBLE_WORD_SCORE
        assertEquals(BoardSquareEffect.DOUBLE_WORD_SCORE, board.getSquare(4, 4).getEffect());

        // Setup existing tile 'I' at (5, 4)
        board.getSquare(5, 4).setTile(makeTile('I'));

        // Play "PIN" horizontally starting at (4, 4)
        // (4, 4)=P on DOUBLE_WORD_SCORE, (4, 5)=I, (4, 6)=N
        // Forms:
        // 1. Main word: "PIN" horizontally at row 4, cols 4-6
        //    P(3) at (4, 4) on DOUBLE_WORD_SCORE
        //    I(1) at (4, 5)
        //    N(1) at (4, 6)
        //    Sum = 3 + 1 + 1 = 5. Doubled by (4, 4): 5 * 2 = 10!
        // 2. Vertical cross-word at col 4, rows 4-5: "PI"
        //    P(3) at (4, 4) on DOUBLE_WORD_SCORE
        //    I(1) at (5, 4) [existing tile]
        //    Sum = 3 + 1 = 4. Doubled by (4, 4): 4 * 2 = 8!
        // Total score = 10 + 8 = 18!
        // The newly placed 'P' on DOUBLE_WORD_SCORE doubled BOTH words!
        Submission move = new Submission(makeTiles("PIN"), 4, 4, Direction.HORIZONTAL);
        assertTrue(board.validateMove(move));
        int score = board.placeWord(move);
        assertEquals(18, score);
    }

    @Test(expected = InvalidMoveException.class)
    public void testInvalidMoveThrowsExceptionOnPlaceWord() {
        // Disconnected word on non-empty board
        board.placeWord(new Submission(makeTiles("CAT"), 7, 6, Direction.HORIZONTAL));
        // Far away disconnected word
        board.placeWord(new Submission(makeTiles("FAR"), 0, 0, Direction.HORIZONTAL));
    }
}
