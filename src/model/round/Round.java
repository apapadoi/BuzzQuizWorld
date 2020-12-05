package model.round;

import model.fileHandler.FileHandler;
import model.Model;
import model.gamemodes.Gamemodable;
import model.gamemodes.NumerablePlayersGamemode;
import model.questions.Question;
import java.util.InputMismatchException;
import java.util.List;

/**
 * This class represents a single round with 5 questions and a random gamemode.
 *
 * @author Thodwrhs Myridis
 * @author Tasos Papadopoulos
 * @version 29.11.2020
 */
public class Round {
    private final List<Question> questions;
    private final Gamemodable gamemode;
    /**
     * Create a round with a random gamemode from the {@code NumerablePlayersGamemode} object provided and load 5 questions
     * using the {@code fileHandler}  provided.
     * @param gamemodesForCurrentNumOfPlayers an object of a class implementing {@code NumerablePlayersGamemode} interface that will provide a random gamemode for the current number of players
     * @param fileHandler the file handler that loaded the questions from the data txt file.
     * */
    public Round(NumerablePlayersGamemode gamemodesForCurrentNumOfPlayers,FileHandler fileHandler) {
        this.questions = fileHandler.getNextQuestions();
        this.gamemode = gamemodesForCurrentNumOfPlayers.getRandomGamemode();
    }

    /**
     * This method returns the questions of the current round
     * @return The questions as {@code List<Question>}
     */
    public List<Question> getQuestions() {
        return this.questions;
    }

    /**
     * Returns the round's gamemode description
     * @return the round's gamemode description as {@code String}
     */
    public String getGamemodeDescription() {
        return this.gamemode.getDescription();
    }

    /**
     * Returns the round's gamemode to a {@code String} representation.
     * @return the round's gamemode as {@code String}
     */
    public String getGamemodeString() {
        return this.gamemode.toString();
    }

    /** Returns the available time in seconds as {@code int}.
     * @return the round's gamemode available time in seconds as {@code int}
     */
    public int getAvailableTime() {
        return this.gamemode.getAvailableTime();
    }

    /**
     * Calls the corresponding method of round's current gamemode.
     * @see Gamemodable
     * @param model an instance of {@code Model} class
     * @param currentQuestion the current question
     * @param roundId the round's id with offset 0
     * @return the question format for the current gamemode as {@code String}
     */
    public String getQuestionFormat(Model model,Question currentQuestion, int roundId) {
        return gamemode.getQuestionFormat(model,currentQuestion,roundId);
    }

    /**
     * Calls the corresponding method of round's current gamemode.
     * Returns whether or not the current gamemode has pre question phase.
     * @see Gamemodable
     * @return whether or not the current gamemode has pre question phase as {@code boolean}
     */
    public boolean hasPreQuestionFormat() {
        return this.gamemode.hasPreQuestionFormat();
    }

    /**
     * Calls the corresponding method of round's current gamemode.
     * @see Gamemodable
     * @param model an instance of {@code Model} class
     * @param currentQuestion the current question
     * @return the pre-question format for the current gamemode as {@code String}
     */
    public String getPreQuestionFormat(Model model, Question currentQuestion) {
        return this.gamemode.getPreQuestionFormat(model,currentQuestion);
    }

    /**
     * Calls the corresponding method of round's current gamemode.
     * @see Gamemodable
     * @param model an instance of {@code Model} class
     * @throws NumberFormatException if the user did not type an integer at all
     * @throws InputMismatchException if the user typed a valid type of input but not a valid logical input
     */
    public void actionsPreQuestionsPhase(Model model) throws NumberFormatException, InputMismatchException {
        this.gamemode.actionsPreQuestionsPhase(model);
    }

    /**
     * Calls the corresponding method of round's current gamemode.
     * Returns the pre-question message that will be shown before an input request is asked to the user.
     * @see Gamemodable
     * @return the pre-question message for the current gamemode as {@code String}
     */
    public String getPreQuestionAskMessage() {
        return this.gamemode.getPreQuestionAskMessage();
    }

    /**
     * Calls the corresponding method of round's current gamemode.
     * @see Gamemodable
     * @param model an instance of {@code Model} class
     */
    public void actionIfCorrectAnswer(Model model) {
        this.gamemode.actionIfCorrectAnswer(model);
    }

    /**
     * Calls the corresponding method of round's current gamemode.
     * @see Gamemodable
     * @param model an instance of {@code Model} class
     */
    public void actionIfWrongAnswer(Model model) {
        this.gamemode.actionIfWrongAnswer(model);
    }
}
