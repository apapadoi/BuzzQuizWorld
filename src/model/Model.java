package model;

import model.fileHandler.FileHandler;
import model.gamemodes.factories.GamemodeFactory;
import model.player.Player;
import model.round.Round;
import java.util.*;

/**
 * This class represents the model of the app that groups the data of the app.
 * Singleton Design Pattern is used.
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 * @version 13.1.2021
 */
public class Model{
    private static final Model instance = new Model();
    private final List<Player> players;
    private HashMap<Integer,Boolean> playersAnswered;
    private HashMap<Integer, Integer> millisLeft;
    private int maxPlayers = 2;
    private List<Round> rounds;
    private GamemodeFactory gamemodeFactory;
    private CustomMediaPlayer mediaPlayer;
    private boolean hasMusic;

    /**
     * Default constructor.
     * */
    private Model() {
        hasMusic = true;
        players = new ArrayList<>();
        playersAnswered = new HashMap<>(maxPlayers);
        for(int i=0;i<maxPlayers;i++)
            playersAnswered.put(i, false);
        millisLeft = new HashMap<>(maxPlayers);
        for(int i=0;i<maxPlayers;i++)
            millisLeft.put(i, 0);
    }

    /**
     * Returns whether or not the game has music
     * @return whether or not the game has sound as {@code boolean}
     */
    public boolean hasMusic() {
        return hasMusic;
    }

    /**
     * Sets whether or not the game has music
     * @param hasMusic the new value for whether or not the game has sound
     */
    public void setHasMusic(boolean hasMusic) {
        this.hasMusic = hasMusic;
    }

    /**
     * Returns the {@code CustomMediaPlayer} object.
     * @return the {@code CustomMediaPlayer} object as {@code CustomMediaPlayer} object
     */
    public CustomMediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    /**
     * Sets a new {@code CustomMediaPlayer}
     * @param mediaPlayer the new {@code CustomMediaPlayer} object
     */
    public void setMediaPlayer(CustomMediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    /**
     * Sets the gamemodeFactory for the gamemodes
     * @param gamemodeFactory the new {@code GamemodeFactory} object
     */
    public void setGamemodeFactory(GamemodeFactory gamemodeFactory) {
        this.gamemodeFactory = gamemodeFactory;
    }

    /**
     * Returns the current gamemode factory
     * @return the current gamemode factory as {@code GamemodeFactory}
     */
    public GamemodeFactory getGamemodeFactory() {
        return gamemodeFactory;
    }

    /**
     * Returns the unique {@code Model} instance
     * @return the Model component as {@code Model} object
     */
    public static Model getInstance() {
        return instance;
    }

    /**
     * Returns the players of the current game session.
     * @return the players of the current game session as {@code List<Player>}
     */
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Sets the player's username
     * @param username the new username of the player
     * @param index the player's index
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
     * @param index the player's index
     * @return the player's name as {@code String}.
     */
    public String getUsername(int index) {
        return this.players.get(index).getUsername();
    }

    /**
     * Returns the player's score
     * @param index the player's index
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
     * @param index the player's index
     */
    public void addScore(int amount, int index) {
        this.players.get(index).addScore(amount);
    }

    /**
     * Creates the players for the current game session using the usernames provided
     * @param usernames the usernames of the players of the current game session as {@code List<String>}
     */
    public void setUsernames(List<String> usernames) {
        for(String username : usernames)
            this.players.add(new Player(username,0,0));
    }

    /**
     * Sets the max players of the current gameplay session
     * @param newMaxPlayers the new value for the max players of the current gameplay session
     */
    public void setMaxPlayers(int newMaxPlayers) {
        maxPlayers = newMaxPlayers;
        playersAnswered = new HashMap<>(maxPlayers);
        millisLeft = new HashMap<>(maxPlayers);
        restartMaps();
    }

    /**
     * Adds the milliseconds left at the moment a player answered
     * @param playerIndex the player's index
     * @param msLeft the milliseconds left
     */
    public void putMillisLeft(int playerIndex, int msLeft) {
        millisLeft.put(playerIndex, msLeft);
    }

    /**
     * Returns the milliseconds left for a specific player at the moment he answered
     * @param playerIndex the player's index
     * @return the milliseconds left as {@code int}
     */
    public int getMillisLeft(int playerIndex) {
        return millisLeft.get(playerIndex);
    }

    /**
     * Returns a hash map that contains information about whether or not a specific player answered.
     * @return whether or not players of the current gameplay session have answered as
     * {@code HashMap{@literal<}Integer, Boolean{@literal>}}
     */
    public HashMap<Integer, Boolean> getPlayersAnswered() {
        return playersAnswered;
    }

    /**
     * Returns the max players of the current gameplay session.
     * @return the max players of the current gameplay session as {@code int}
     */
    public int getMaxPlayers() {
        return maxPlayers;
    }

    /**
     * Returns the milliseconds left of the players of the current gameplay session.
     * @return the milliseconds left as {code HashMap{@literal<}Integer,Integer{@literal>}}
     */
    public HashMap<Integer, Integer> getMillisLeft() {
        return millisLeft;
    }

    /**
     * Clears the data of the {@code Model} component in order to play a new game session without restarting the game.
     */
    public void clearData() {
        if(players!=null)
            instance.players.clear();
        if(rounds!=null)
            instance.rounds.clear();
        playersAnswered = new HashMap<>(maxPlayers);
        millisLeft = new HashMap<>(maxPlayers);
        restartMaps();
    }

    /**
     * Clears the data of the hash maps. Used in clearData() and setMaxPlayers() methods.
     */
    public void restartMaps() {
        for(int i=0;i<maxPlayers;i++)
            playersAnswered.put(i, false);
        for(int i=0;i<maxPlayers;i++)
            millisLeft.put(i, 0);
    }
}
