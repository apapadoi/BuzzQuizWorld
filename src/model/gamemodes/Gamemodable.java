package model.gamemodes;

import model.Model;
import java.util.InputMismatchException;

/**
 * This interface contains all the methods that a new gamemode class must implement so it can be added to the game.
 *
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 * @version 4.1.2021
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
     */
    void actionIfCorrectAnswer(Model model, int playerIndex);

    /**
     * Performs the actions that must be done when user answers correct.
     * Uses the milliseconds it took the player to answer.
     *
     * @param model  instance of {@code Model} class
     * @param millis the time it took the player to answer in milliseconds
     */
    void actionIfCorrectAnswer(Model model, int millis, int playerIndex);

    /**
     * Performs the actions that must be done before the question is shown.
     *
     * @param model instance of {@code Model} class
     * @throws NumberFormatException  if the user did not type an integer at all
     * @throws InputMismatchException if the user typed a valid type of input but not a valid logical input
     */
    void actionPreQuestionsPhase(Model model);

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
     */
    void actionIfWrongAnswer(Model model, int playerIndex);

    /**
     * Returns a gamemode to string representation
     *
     * @return the current gamemode as {@code String}
     */
    String toString();

    boolean hasTimer();

    int getMinBet();

    void setBetAmount(int amount, int playerIndex);

    void checkZeroScoreAndUpdate(Model model, int playerIndex);
}
