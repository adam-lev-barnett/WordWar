package player;

import exceptions.InvalidBoardSquareException;
import exceptions.UnselectableTileException;
import mechanics.boardconfig.BoardConfig;
import mechanics.pieceplacement.Direction;
import mechanics.turn.Submission;
import board.tiles.LetterTile;
import user.User;

import java.util.ArrayList;
import java.util.List;

public class Player {

    String name;
    int score;
    private final List<LetterTile> hand = new ArrayList<>();

    public Player(User user) {
        this.name = user.getUsername();
        this.score = 0;
    }

    public List<LetterTile> getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public Submission submitMove(List<LetterTile> letterTiles, int row, int col, Direction direction) {
        return new Submission(letterTiles, row, col, direction);
    }



    private Direction getSubmissionDirection()

    /** Transfers tile from hand to board; this is undone via player cancellation or invalid submission*/
    public void placeTile(int handIndex, int row, int col) throws InvalidBoardSquareException {
        if (handIndex < 0 || handIndex > hand.size()) throw new UnselectableTileException("Selected hand index is out of bounds");
        BoardConfig.validateSquare(row, col);
        LetterTile tile = hand.get(handIndex);
        hand.remove(handIndex);
        onBoard.add(tile);
    }





}
