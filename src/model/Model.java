package model;

import model.gamemodes.NumerablePlayersGamemode;
import model.gamemodes.OnePlayerGamemodes;
import model.player.Player;
import model.round.Round;
import java.util.*;

/**
 * This class represents the model of the app that handles the data.
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 * @version 29.11.2020
 */
public class Model{
    private final Player player;
    private List<Round> rounds;
    private final List<String> validAnswers;

    /**Default constructor. Initializes the player of the game.*/
    public Model() {
        this.validAnswers = new ArrayList<>(List.of("1","2","3","4"));
        player = new Player();
    }

    public List<String> getValidAnswers() {
        return this.validAnswers;
    }

    public void setUsername(String username) {
        player.setUsername(username);
    }

    /**
     * Returns the version of the game as {@code String}.
     * @return String
     */
    public String getVersion() {
        return "29.11.2020";
    }

    /** Setter for the number of rounds.
     */
    public void setNumOfRoundsChoice(int choice) {
        rounds = new ArrayList<>(choice);
        for (int i = 0; i < choice; i++) {
            rounds.add(new Round(this.chooseGamemodesForCurrentNumOfPlayers()));
        }
    }

    public NumerablePlayersGamemode chooseGamemodesForCurrentNumOfPlayers() {
        return new OnePlayerGamemodes();
    }

    public int getNumOfRounds() {
        return this.rounds.size();
    }

    /** Returns the user's name as {@code String}.
     * @return String
     */
    public String getUsername() {
        return this.player.getUsername();
    }

    /**Returns the user's score as {@code int}}
     * @return int
     */
    public int getScore() {
        return this.player.getScore();
    }

    /** Returns the round that has index {@code i} as {@code Round}.
     * @param i The index of the round we want to get.
     * @return Round
     */
    public Round getRound(int i) {
        return this.rounds.get(i);
    }

    /** Method that updates user's score with adding the {@code amount} value.
     * No checking is done on the {@code amount} parameter's value.
     * @param amount The amount we want to add to the user's score.
     */
    public void updateScore(int amount) {
        this.player.addScore(amount);
    }
}
