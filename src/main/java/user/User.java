package user;

import game.Game;
import jakarta.persistence.*;
import player.GameRecord;

import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @Embedded
    private GameRecord record;
    private Set<Game> activeGames;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.record = new GameRecord();
        this.activeGames = new HashSet<>();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Set<Game> getActiveGames() {
        return activeGames;
    }

    public void setActiveGames(Set<Game> activeGames) {
        this.activeGames = activeGames;
    }

    public GameRecord getRecord() {
        return this.record;
    }

    public String  getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




}
