package model.gamemodes;

import model.Model;

/**
 * This interface contains all the methods that a new gamemode class must implement so it can be added to the game.
 *
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 * @version 13.1.2021
 */
public interface Gamemodable {
    /**
     * Returns the description of the gamemode
     *
     * @return the description of the gamemode as {@code String}.
     */
    String getDescription();

    /**
     * Returns the available time of the gamemode for each question in seconds.
     *
     * @return the available time of the gamemode for each question in seconds as {@code int}.
     */
    int getAvailableTime();

    /**
     * Performs the actions that must be done when user answers correct.
     *
     * @param model instance of {@code Model} class
     * @param playerIndex the player's index whose score will be updated depending the current gamemode if he answered
     *                    correct
     */
    void actionIfCorrectAnswer(Model model, int playerIndex);

    /**
     * Performs the actions that must be done when user answers correct.
     * Uses the milliseconds it took the player to answer.
     *
     * @param model  instance of {@code Model} class
     * @param millis the time it took the player to answer in milliseconds
     * @param playerIndex the player's index whose score will be updated depending the current gamemode if he answered
     *                    correct
     */
    void actionIfCorrectAnswer(Model model, int millis, int playerIndex);

    /**
     * Returns whether or not the current gamemode has pre question phase.
     *
     * @return whether or not the current gamemode has pre question phase as {@code boolean}
     */
    boolean hasPreQuestionPhase();

    /**
     * Performs the actions that must be done when user answers wrong.
     *
     * @param model instance of {@code Model} class
     * @param playerIndex the player's index whose score will be updated depending the gamemode if he answered wrong
     */
    void actionIfWrongAnswer(Model model, int playerIndex);

    /**
     * Returns a gamemode to string representation
     *
     * @return the current gamemode as {@code String}
     */
    String toString();

    /**
     * Returns whether or not a gamemode has timer.
     * @return whether or not a gamemode has timer as {@code boolean}
     */
    boolean hasTimer();

    /**
     * Returns the minimum bet for a gamemode.
     * @return the minimum bet for a gamemode as {@code in}
     */
    int getMinBet();

    /**
     * Sets the bet amount for a specific player.
     * @param amount the bet amount as {@code int}
     * @param playerIndex the player's index as {@code int}
     */
    void setBetAmount(int amount, int playerIndex);

    /**
     * Checks if the player for the provided index has less or equal to 0 points and gives him enough points to bet.
     * @param model instance of {@code Model} component
     * @param playerIndex the player's index
     */
    void checkZeroScoreAndUpdate(Model model, int playerIndex);
}
