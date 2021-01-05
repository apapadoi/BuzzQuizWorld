package model.round;

import controller.requests.UpdateDataRequest;
import model.fileHandler.FileHandler;
import model.Model;
import model.gamemodes.Gamemodable;
import model.gamemodes.GamemodeFactory;
import model.questions.Question;
import view.gui.OnePlayerFrame;
import java.util.InputMismatchException;
import java.util.List;

/**
 * This class represents a single round with 5 questions and a random gamemode.
 *
 * @author Thodwrhs Myridis
 * @author Tasos Papadopoulos
 * @version 4.1.2021
 */
public class Round {
    private final List<Question> questions;
    private final Gamemodable gamemode;
    private int questionIndex;

    /**
     * Create a round with a random gamemode from the {@code NumerablePlayersGamemode} object provided and load 5 questions
     * using the {@code fileHandler}  provided.
     * @param fileHandler the file handler that loaded the questions from the data txt file.
     * */
    public Round(FileHandler fileHandler) {
        this.questions = fileHandler.getNextQuestions();
        this.gamemode = GamemodeFactory.getRandomGamemode();
        this.questionIndex = 0;
    }

    // TODO null object pattern when there are no more questions for this round
    @Deprecated
    public Question getNextQuestion() {
        Question question;
        if(questionIndex == this.questions.size() )
            question = null;
        else
            question = this.questions.get(questionIndex++);

        return question;
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
     * Returns whether or not the current gamemode has pre question phase.
     * @see Gamemodable
     * @return whether or not the current gamemode has pre question phase as {@code boolean}
     */
    public boolean hasPreQuestionFormat() {
        return this.gamemode.hasPreQuestionPhase();
    }


    /**
     * Calls the corresponding method of round's current gamemode.
     * @see Gamemodable
     * @param model an instance of {@code Model} class
     * @throws NumberFormatException if the user did not type an integer at all
     * @throws InputMismatchException if the user typed a valid type of input but not a valid logical input
     */
    @Deprecated // TODO ?
    public void actionsPreQuestionsPhase(Model model) throws NumberFormatException, InputMismatchException {
        this.gamemode.actionPreQuestionsPhase(model);
    }

    public Gamemodable getGamemode() {
        return gamemode;
    }

    /**
     * Calls the corresponding method of round's current gamemode.
     * @see Gamemodable
     * @param model an instance of {@code Model} class
     */
    public void actionIfCorrectAnswer(Model model, int playerIndex) {
        // TODO remove OnePlayerFrame.getInstance().getCount()
        this.gamemode.actionIfCorrectAnswer(model, UpdateDataRequest.getMsLeft(playerIndex), playerIndex);
    }

    /**
     * Calls the corresponding method of round's current gamemode.
     * @see Gamemodable
     * @param model an instance of {@code Model} class
     */
    public void actionIfWrongAnswer(Model model, int playerIndex) {
        this.gamemode.actionIfWrongAnswer(model, playerIndex);
    }
}
