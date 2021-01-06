package model.player;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class represents a player with his username,his points and the number of rounds he chosen.
 *
 * @author Thodwrhs Myridis
 * @author Tasos Papadopoulos
 * @version 17.11.2020
 */
public class Player implements Serializable {
    private static final long serialVersionUID = 1148183335575561098L;
    private String username;
    private int score; // one player gamemodes high score
    private int wins; // two players gamemodes wins

    /**
     * Default constructor. Create a player with empty string as username and score equal to 0.
     */
    public Player() {
        this.username = "";
        this.score = 0;
        this.wins = 0;
    }

    /**
     * Constructs a player with username,score and wins with the username,score and wins provided.
     *
     * @param username Player's username as {@code String}
     * @param score Player's score as {@code int}
     * @param wins Player's wins as {@code int}
     */
    public Player(String username,int score,int wins) {
        this.username = username;
        this.score = score;
        this.wins = wins;
    }

    /**
     * Returns the username of the player
     *
     * @return The username of the player as {@code String}
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Setter for the username of the player.
     *
     * @param username The new username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the score of the player
     *
     * @return The player's score as {@code int}
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Updates user's score with adding the {@code amount} value.
     * No checking is done on the {@code amount} parameter's value.
     *
     * @param amount The amount that will be added to player's score.
     */
    public void addScore(int amount) {
        this.score += amount;
    }

    /**
     * Updates player's wins with adding the {@code amount} value.
     * No checking is done on the {@code amount} parameter's value.
     *
     * @param amount The amount that will be added to player's wins.
     */
    public void addWins(int amount) {
        this.wins += amount;
    }

    /**
     *  Returns the wins of the player
     *
     * @return The player's wins as {@code int}
     */
    public int getWins() {
        return this.wins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return getScore() == player.getScore() &&
                getWins() == player.getWins() &&
                Objects.equals(getUsername(), player.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getScore(), getWins());
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    @Override
    public String toString() {
        return "Player{" +
                "username='" + username + '\'' +
                ", score=" + score +
                ", wins=" + wins +
                '}';
    }
}
