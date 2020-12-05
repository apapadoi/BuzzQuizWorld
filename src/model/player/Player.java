package model.player;

/**
 * This class represents a player with his username,his points and the number of rounds he chosen.
 *
 * @author Thodwrhs Myridis
 * @author Tasos Papadopoulos
 * @version 17.11.2020
 */
public class Player {
    private String username;
    private int score;

    /**
     * Default constructor. Create a player with empty string as username and score equal to 0.
     */
    public Player() {
        this.username = "";
        this.score = 0;
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
     * @return The score of the player as {@code int}
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
}
