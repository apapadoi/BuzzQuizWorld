package model.gamemodes;

import model.Model;
import model.questions.Question;
import java.util.InputMismatchException;

/**
 * This interface contains all the methods that a new gamemode class must implement so it can be added to the game.
 *
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 * @version 5.12.2020
 */
public interface Gamemodable {
    /**
     * Returns the description of the gamemode
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
     * @param model instance of {@code Model} class
     *
     */
    void actionIfCorrectAnswer(Model model);

    /**
     * Performs the actions that must be done when user answers correct.
     * Uses the milliseconds it took the player to answer.
     * @param model instance of {@code Model} class
     * @param millis the time it took the player to answer in milliseconds
     */
    void actionsIfCorrectAnswer(Model model,int millis);

    /**
     * Returns the question format depending the gamemode.
     * @param model instance of {@code Model} class
     * @param currentQuestion the current question
     * @param roundId the id of the current round with offset 0
     * @return the question format for the current gamemode as {@code String}
     */
    String getQuestionFormat(Model model,Question currentQuestion, int roundId);

    /**
     * Returns the pre-question format depending the gamemode that needs to be shown before the current question is shown.
     * @param model instance of {@code Model} class
     * @param currentQuestion the current question
     * @return the pre-question format for the current gamemode as {@code String}
     */
    String getPreQuestionFormat(Model model,Question currentQuestion);

    /**
     * Returns the pre-question message that will be shown before an input request is asked to the user.
     * @return the pre-question message for the current gamemode as {@code String}
     */
    String getPreQuestionAskMessage();

    /**
     * Performs the actions that must be done before the question is shown.
     * @param model instance of {@code Model} class
     * @throws NumberFormatException if the user did not type an integer at all
     * @throws InputMismatchException if the user typed a valid type of input but not a valid logical input
     */
    void actionsPreQuestionsPhase(Model model) throws NumberFormatException, InputMismatchException;

    /**
     * Returns whether or not the current gamemode has pre question phase.
     * @return whether or not the current gamemode has pre question phase as {@code boolean}
     */
    boolean hasPreQuestionFormat();

    /**
     * Performs the actions that must be done when user answers wrong.
     * @param model instance of {@code Model} class
     */
    void actionIfWrongAnswer(Model model);

    /**
     * Returns a gamemode to string representation
     * @return the current gamemode as {@code String}
     * */
    String toString();
}
