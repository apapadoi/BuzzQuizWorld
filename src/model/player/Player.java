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

    public Player() {
        this.username = "";
        this.score = 0;
    }

    /**
     * This method returns the username of the player
     *
     * @return The username of the player as {@code String}
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Setter for the username attribute of the player.
     *
     * @param username The new username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method returns the points of the player
     *
     * @return The score of the player as {@code int}
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Method that updates user's score with adding the {@code amount} value.
     * No checking is done on the {@code amount} parameter's value.
     *
     * @param amount The amount we want to add to the user's score.
     */
    public void addScore(int amount) {
        this.score += amount;
    }
}
