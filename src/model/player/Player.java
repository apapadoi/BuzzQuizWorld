package model.player;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class represents a player with his username,his points and the number of rounds he chosen.
 *
 * @author Thodwrhs Myridis
 * @author Tasos Papadopoulos
 * @version 13.1.2021
 */
public class Player implements Serializable {
    private static final long serialVersionUID = 1148183335575561098L;
    private String username;
    private int score; // one player game sessions high score
    private int wins; // two players game sessions wins

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
     * <b>No checking is done on the {@code amount} parameter's value.</b>
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

    /**
     * Compares a {@code Player} object with another object.
     * @param o object of type {@code Object} that will be compared with a {@code Player} object
     * @return true/false depending the comparison value
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return getScore() == player.getScore() &&
                getWins() == player.getWins() &&
                Objects.equals(getUsername(), player.getUsername());
    }

    /**
     * Hash Function for {@code Player} objects.
     * @return hash code for a {@code Player} object
     */
    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getScore(), getWins());
    }

    /**
     * Sets the score of the player
     * <b>No checking is done in the {@code score} parameter's value.</b>
     * @param score the new score value
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Sets the wins of the player
     * <b>No checking is done in the {@code wins} parameter's value.</b>
     * @param wins the new wins value
     */
    public void setWins(int wins) {
        this.wins = wins;
    }
}
