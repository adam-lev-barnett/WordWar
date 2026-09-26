package player;

import jakarta.persistence.Embeddable;

@Embeddable
public class GameRecord {

    int wins;
    int losses;
    int draws;

    public GameRecord() {
        this.wins = 0;
        this.losses = 0;
        this.draws = 0;
    }

    public int getWins() {
        return wins;
    }

    public void addWin() {
        this.wins++;
    }

    public int getLosses() {
        return losses;
    }

    public void addLoss() {
        this.losses++;
    }

    public int getDraws() {
        return draws;
    }

    public void addDraw() {
        this.draws++;
    }

    public void resetRecord() {
        this.wins = 0;
        this.losses = 0;
        this.draws = 0;
    }


}
