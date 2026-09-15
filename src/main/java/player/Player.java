package player;

import tiles.LetterTile;
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
}
