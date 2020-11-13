package backend.questions;

/**
 * This class represents a player with his username,his points and the number of rounds he chosen.
 * @author Thodwrhs Myridis
 * @version 11.11.2020
 * */
public class Player {
    private String username;
    private int score;
    private int numOfRoundsChoosed;

    /**
     * Creates a new player.
     * @param username the {@code String} that it is the username of the player
     * @param score the {@code int} that it is the current score points of the player
     * @param numOfRoundsChoosed the {@code int} that it is the number of the rounds the player chosen
     * */

    /**
     * This method returns the username of the player
     * @return The username of the player as {@code String}
     * */
    public String getUsername(){ return this.username;}

    /**
     * This method returns the points of the player
     * @return The score of the player as {@code int}
     * */
    public int getScore(){ return this.score;}
}
