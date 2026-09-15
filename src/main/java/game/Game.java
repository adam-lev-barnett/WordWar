package game;

import board.GameBoard;
import exceptions.GameInitializationException;
import exceptions.InvalidTileException;
import exceptions.NoTilesPlayedException;
import player.Player;
import tiles.LetterBag;
import tiles.LetterTile;

import java.util.List;

public class Game {

    private final GameBoard board;
    private final LetterBag letterBag = new LetterBag();
    private Player p1;
    private Player p2;
    private int p1Score;
    private int p2Score;

    public Game(Player p1, Player p2) {
        setPlayers(p1, p2);
        this.board = new GameBoard();
        this.p1Score = 0;
        this.p2Score = 0;
    }

    public GameBoard getBoard() {
        return board;
    }

    public LetterBag getLetterBag() {
        return letterBag;
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    private void setPlayers(Player player1, Player player2) {
        if (player1 == null || player2 == null) throw new GameInitializationException("Player cannot be null");
        this.p1 = player1;
        this.p2 = player2;
    }

    public int getP1Score() {
        return p1Score;
    }

    public void addP1Points(int points) {
        this.p1Score += p1Score;
    }

    public int getP2Score() {
        return p2Score;
    }

    public void addP2Points(int points) {
        this.p2Score += p2Score;
    }

    public void placeTiles(List<LetterTile> letters, int row, int col) {
        if (letters == null || letters.isEmpty()) throw new NoTilesPlayedException();

        // Validates submitted letters form an accepted word
        if (board.)
    }




}
