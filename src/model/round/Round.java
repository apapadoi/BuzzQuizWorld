package model.round;

import model.FileHandler.FileHandler;
import model.Model;
import model.gamemodes.Gamemodable;
import model.gamemodes.NumerablePlayersGamemode;
import model.questions.Question;
import java.util.InputMismatchException;
import java.util.List;

/**
 * This class represents a single round with the number of the rounds, a list of the questions that are going to be picked randomly, the current question id (contains
 * correct answer, 4 possible answers, the description and the difficulty) and the countdown timer.
 *
 * @author Thodwrhs Myridis
 * @author Tasos Papadopoulos
 * @version 29.11.2020
 */
public class Round {
    private final List<Question> questions;
    private final Gamemodable gamemode;
    /**
     * Default constructor.
     * */
    public Round(NumerablePlayersGamemode gamemodesForCurrentNumOfPlayers,FileHandler fileHandler) {
        this.questions = fileHandler.getNextQuestions();
        this.gamemode = gamemodesForCurrentNumOfPlayers.getRandomGamemode();
    }

    /**
     * This method returns the questions
     *
     * @return The questions as {@code List<Question>}
     */
    public List<Question> getQuestions() {
        return this.questions;
    }

    public String getGamemodeDescription() {
        return this.gamemode.getDescription();
    }

    public String getGamemodeString() {
        return this.gamemode.toString();
    }

    /** Returns the available time as {@code int}.
     * @return int
     */
    public int getAvailableTime() {
        return this.gamemode.getAvailableTime();
    }

    /** Method that shows the current question depending the gamemode using the {@code view} parameter.
     * @param currentQuestion The current question of a round.
     * @param roundId         The id of the current round.
     */
    public String getQuestionFormat(Model model,Question currentQuestion, int roundId) {
        return gamemode.getQuestionFormat(model,currentQuestion,roundId);
    }

    public boolean hasPreQuestionFormat() {
        return this.gamemode.hasPreQuestionFormat();
    }

    public String getPreQuestionFormat(Model model, Question currentQuestion) {
        return this.gamemode.getPreQuestionFormat(model,currentQuestion);
    }

    public void actionsPreQuestionsPhase(Model model,Question currentQuestion) throws NumberFormatException, InputMismatchException {
        this.gamemode.actionsPreQuestionsPhase(model,currentQuestion);
    }

    public String getPreQuestionAskMessage() {
        return this.gamemode.getPreQuestionAskMessage();
    }

    public void actionIfCorrectAnswer(Model model,int secondsTookToAnswer) {
        this.gamemode.actionIfCorrectAnswer(model,secondsTookToAnswer);
    }

    public void actionIfWrongAnswer(Model model) {
        this.gamemode.actionIfWrongAnswer(model);
    }
}
