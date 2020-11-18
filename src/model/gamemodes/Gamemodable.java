package model.gamemodes;

import model.Model;
import model.questions.Question;
import view.cli.Cli;

/**
 * This interface contains all the methods that a new Gamemode class must implement so it can be added to the game.
 *
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 * @version 18.11.2020
 */
public interface Gamemodable {
    /**
     * This method returns the description of the gamemode as {@code String}.
     */
    String getDescription();

    /**
     * This method returns the available skips of the gamemode as {@code int}.
     */
    int getSkipsAvailable();

    /**
     * This method returns the available time of the gamemode for eqch question as {@code int}.
     */
    int getAvailableTime();

    /**
     * This method decreases the available skips of the gamemode.
     */
    void decreaseSkips();

    /**
     * This method performs the actions that must be done when user answers correctly the question depending the gamemode.
     */
    void actionIfCorrectAnswer(Model model);

    /**
     * This method shows the current question depending the format each gamemode wants to implement.
     */
    void showQuestionFormat(Model model, Cli view, Question currentQuestion, int roundId);

    /**
     * This method performs the actions that must be done when user decides to answer the question depending the gamemode.
     */
    boolean actionWhenAnswered(String choice, Question currentQuestion, int secondsTookToAnswer, Cli view, Model model) throws NumberFormatException;

    /**
     * This method completes the actions that need to be done in the pre question's page.
     */
    void actionsPreQuestionsPhase(Model model, Cli view, Question currentQuestion);

    /**
     * This method returns if the current gamemode has pre question page or not as {@code boolean}.
     */
    boolean hasPreQuestionFormat();
}
