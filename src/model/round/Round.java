package model.round;

import model.fileHandler.FileHandler;
import model.Model;
import model.gamemodes.Gamemodable;
import model.questions.Question;
import java.util.List;

/**
 * This class represents a single round with 5 questions and a random gamemode.
 *
 * @author Thodwrhs Myridis
 * @author Tasos Papadopoulos
 * @version 13.1.2021
 */
public class Round {
    private static final Model model = Model.getInstance();
    private final List<Question> questions;
    private final Gamemodable gamemode;

    /**
     * Create a round with a random gamemode from the {@code GamemodeFactory} object provided by the {@code Model}
     * component and load 5 question using the {@code fileHandler} provided.
     * @param fileHandler the file handler that loaded the questions from the data txt files containing the texted and
     * imaged questions.
     * */
    public Round(FileHandler fileHandler) {
        this.questions = fileHandler.getNextQuestions();
        this.gamemode = model.getGamemodeFactory().getRandomGamemode();
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
     * Returns the gamemode of a {@code Round} object
     * @return the round's gamemode as {@code Gamemodable}
     */
    public Gamemodable getGamemode() {
        return gamemode;
    }

    /**
     * Calls the corresponding method of round's current gamemode.
     * @param playerIndex the player's index whose score will be updated depending the current gamemode if he answered
     *                    correct
     * @see Gamemodable
     */
    public void actionIfCorrectAnswer(int playerIndex) {
        this.gamemode.actionIfCorrectAnswer(model, model.getMillisLeft(playerIndex), playerIndex);
    }

    /**
     * Calls the corresponding method of round's current gamemode.
     * @param playerIndex the player's index whose score will be updated depending the current gamemode if he answered
     *                    wrong
     * @see Gamemodable
     */
    public void actionIfWrongAnswer(int playerIndex) {
        this.gamemode.actionIfWrongAnswer(model, playerIndex);
    }
}
