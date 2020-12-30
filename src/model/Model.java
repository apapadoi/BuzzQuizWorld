package model;

import model.fileHandler.FileHandler;
import model.gamemodes.NumerablePlayersGamemode;
import model.gamemodes.OnePlayerGamemodes;
import model.player.Player;
import model.round.Round;
import java.util.*;

/**
 * This class represents the model of the app that groups the data of the app.
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 * @version 29.11.2020
 */
public class Model{
    private final List<Player> players;
    private List<Round> rounds;
    private final List<String> validAnswers;

    /**
     * Default constructor.
     * */
    public Model() {
        this.validAnswers = new ArrayList<>(List.of("1","2","3","4"));
        players = new ArrayList<>();
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Returns the valid answers that the player can type.
     * @return the valid answers that the player can type as {@code List<String>}
     */
    public List<String> getValidAnswers() {
        return this.validAnswers;
    }

    /**
     * Sets the player's username
     * @param username the new username of the player
     * @see Player
     */
    public void setUsername(String username,int index) {
        players.get(index).setUsername(username);
    }

    /**
     * Returns the version of the game as {@code String}.
     * @return String
     */
    public String getVersion() {
        return "6.12.2020";
    }

    /**
     * Initializes as many as number of rounds the player chose.
     * @param choice the number of rounds the player chose
     * @param fileHandler the file handler that will provide 5 random questions for each round
     */
    public void setNumOfRoundsChoice(int choice, FileHandler fileHandler) {
        rounds = new ArrayList<>(choice);
        for (int i = 0; i < choice; i++) {
            rounds.add(new Round(this.chooseGamemodesForCurrentNumOfPlayers(),fileHandler));
        }
    }

    /**
     * Returns an object depending the number of players that can play the game. In this version the game can be player by one player only so
     * an object of {@code OnePlayerGamemodes} is returned. If new number of players support needs to be added then this method must be updated
     * with the new class implementing the {@code NumerablePlayersGamemode} interface.
     * @return an object that will provide random gamemodes depending the number of player as {@code NumerablePlayersGamemode}
     */
    private NumerablePlayersGamemode chooseGamemodesForCurrentNumOfPlayers() {
        return new OnePlayerGamemodes();
    }

    /**
     * Returns the number of rounds.
     * @return the number of rounds as {@code int}
     */
    public int getNumOfRounds() {
        return this.rounds.size();
    }

    /**
     * Returns the player's name
     * @return the player's name as {@code String}.
     */
    public String getUsername(int index) {
        return this.players.get(index).getUsername();
    }

    /**
     * Returns the player's score
     * @return the player's score as {@code int}}
     */
    public int getScore(int index) {
        return this.players.get(index).getScore();
    }

    /**
     * Returns the round that has index {@code i} as {@code Round}.
     * @param i the index of the round with offset 0
     * @return the round that has index {@code i} as {@code Round}
     */
    public Round getRound(int i) {
        return this.rounds.get(i);
    }

    /**
     * Method that updates player's score with adding the {@code amount} value.
     * No checking is done on the {@code amount} parameter's value.
     * @param amount The amount we want to add to the player's score.
     */
    public void updateScore(int amount,int index) {
        this.players.get(index).addScore(amount);
    }

    @Deprecated
    public void updateScore(int amount) { }

    @Deprecated
    public int getScore() { return 0; }

    @Deprecated
    public String getUsername() { return null; }

    public void setUsernames(List<String> usernames) {
        for(String username : usernames)
            this.players.add(new Player(username,0,0));
    }
}
