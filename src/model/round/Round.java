package model.round;

import model.Model;
import model.gamemodes.Gamemodable;
import model.gamemodes.NumerablePlayersGamemode;
import model.questions.Category;
import model.questions.Difficulty;
import model.questions.Question;
import java.util.ArrayList;
import java.util.Collections;
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
    public Round(NumerablePlayersGamemode gamemodesForCurrentNumOfPlayers) {
        questions = new ArrayList<>(5);

        Question currentQuestion = new Question();

        currentQuestion.setQuestionText("The famous computer Deep Blue was created by which company?");
        currentQuestion.setCorrectAnswer("IBM");
        currentQuestion.setCategory(Category.Science);
        currentQuestion.setDifficulty(Difficulty.Medium);

        List<String> answers = new ArrayList<>(4);
        answers.add("IBM");
        answers.add("Amazon");
        answers.add("Gateway");
        answers.add("Oracle");
        Collections.shuffle(answers);

        currentQuestion.setAnswers(answers);

        for (int i = 0; i < 5; i++) {
            questions.add(currentQuestion);
        }

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
