package model.gamemodes;

import model.Model;
import model.questions.Question;

/**
 * This interface contains all the methods that a new Gamemode class must implement so it can be added to the game.
 *
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 * @version 29.11.2020
 */
public interface Gamemodable {
    /**
     * This method returns the description of the gamemode as {@code String}.
     */
    String getDescription();

    /**
     * This method returns the available time of the gamemode for each question as {@code int}.
     */
    int getAvailableTime();

    /**
     * This method performs the actions that must be done when user answers correctly the question depending the gamemode.
     */
    void actionIfCorrectAnswer(Model model,int secondsTookToAnswer);

    /**
     * This method shows the current question depending the format each gamemode wants to implement.
     */
    String getQuestionFormat(Model model,Question currentQuestion, int roundId);

    String getPreQuestionFormat(Model model,Question currentQuestion);

    String getPreQuestionAskMessage();

    /**
     * This method completes the actions that need to be done in the pre question's page.
     */
    void actionsPreQuestionsPhase(Model model,Question currentQuestion) throws NumberFormatException,ArithmeticException;

    /**
     * This method returns if the current gamemode has pre question page or not as {@code boolean}.
     */
    boolean hasPreQuestionFormat();

    void actionIfWrongAnswer(Model model);

    /**
     * This method returns {@code Gamemodable} object as {@code String}
     * @return String
     * */
    String toString();
}
