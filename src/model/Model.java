package model;

import model.fileHandler.FileHandler;
import model.gamemodes.factories.GamemodeFactory;
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
    private static final Model instance = new Model();
    private final List<Player> players;
    private final HashMap<Integer,Boolean> playersAnswered;
    private final HashMap<Integer, Integer> responseTimes;
    private int maxPlayers = 2;
    private List<Round> rounds;
    private GamemodeFactory gamemodeFactory;

    /**
     * Default constructor.
     * */
    private Model() {
        players = new ArrayList<>();
        playersAnswered = new HashMap<>(maxPlayers);
        for(int i=0;i<maxPlayers;i++)
            playersAnswered.put(i, false);
        responseTimes = new HashMap<>(maxPlayers);
        for(int i=0;i<maxPlayers;i++)
            responseTimes.put(i, 0);
    }

    public void setGamemodeFactory(GamemodeFactory gamemodeFactory) {
        this.gamemodeFactory = gamemodeFactory;
    }

    public GamemodeFactory getGamemodeFactory() {
        return gamemodeFactory;
    }

    public static Model getInstance() {
        return instance;
    }

    public List<Player> getPlayers() {
        return this.players;
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
     * Initializes as many as number of rounds the player chose.
     * @param choice the number of rounds the player chose
     * @param fileHandler the file handler that will provide 5 random questions for each round
     */
    public void setNumOfRoundsChoice(int choice, FileHandler fileHandler) {
        rounds = new ArrayList<>(choice);
        for (int i = 0; i < choice; i++) {
            rounds.add(new Round(fileHandler));
        }
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

    public void setUsernames(List<String> usernames) {
        for(String username : usernames)
            this.players.add(new Player(username,0,0));
    }

    public void setMaxPlayers(int newMaxPlayers) {
        maxPlayers = newMaxPlayers;
        for(int i=maxPlayers;i<playersAnswered.size();i++)
            playersAnswered.put(i, true);
    }

    public void putResponseTime(int playerIndex, int msLeft) {
        responseTimes.put(playerIndex, msLeft);
    }

    public boolean allAnswered() {
        return playersAnswered.values().stream().distinct().count()<=1;
    }

    public int getMsLeft(int playerIndex) {
        return responseTimes.get(playerIndex);
    }

    public HashMap<Integer, Boolean> getPlayersAnswered() {
        return playersAnswered;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public HashMap<Integer, Integer> getResponseTimes() {
        return responseTimes;
    }
}
