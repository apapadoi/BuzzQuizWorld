package model.gamemodes;

import model.Model;
import model.questions.Question;
import view.cli.Cli;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the gamemode Point Builder.
 *
 * @author Tasos Papadopoulos
 * @version 17.11.2020
 */
public class PointBuilder implements Gamemodable {
    final int availableTime;
    final String description;
    int skipsAvailable;

    /**
     * Default Constructor.
     */
    public PointBuilder() {
        this.description = "Each player that answers correctly earns 1000 points.";
        this.availableTime = 5;
        this.skipsAvailable = 3;
    }

    /**
     * Returns the gamemode as {@code String}.
     */
    @Override
    public String toString() {
        return "Point Builder";
    }

    /**
     * @see Gamemodable
     */
    @Override
    public int getSkipsAvailable() {
        return this.skipsAvailable;
    }

    /**
     * @see Gamemodable
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * @see Gamemodable
     */
    public void decreaseSkips() {
        (this.skipsAvailable)--;
    }

    /**
     * @see Gamemodable
     */
    @Override
    public void actionIfCorrectAnswer(Model model) {
        model.updateScore(1000);
    }

    /**
     * @see Gamemodable
     */
    @Override
    public int getAvailableTime() {
        return this.availableTime;
    }

    /**
     * @see Gamemodable
     */
    @Override
    public void showQuestionFormat(Model model, Cli view, Question currentQuestion, int roundId) {
        view.printCurrentGamemode(model.getCurrentGamemodeString());
        view.printCurrentPlayersUsername(model.getUsername());
        view.printPlayersScore(model.getScore());
        view.printRoundId(roundId);
        view.printSkipsAvailable(model.getSkipsAvailable());
        view.printQuestionsCategory(currentQuestion.getCategory());
        view.printQuestionsDifficulty(currentQuestion.getDifficulty());
        view.printAvailableTime(model.getAvailableTime());
        view.printQuestionsText(currentQuestion.getQuestionText());
        view.printQuestionsAnswers(currentQuestion.getAnswers());
        view.printChooseAnswerText();
    }

    /**
     * @see Gamemodable
     */
    @Override
    public boolean actionWhenAnswered(String choice, Question currentQuestion, int secondsTookToAnswer, Cli view, Model model) throws NumberFormatException {
        int choiceInt = Integer.parseInt(choice);
        if (choiceInt < 1 || choiceInt > 4)
            throw new NumberFormatException();
        choiceInt--;
        List<String> possibleAnswers = new ArrayList<>(currentQuestion.getAnswers());
        for (String currentPossibleAnswer : possibleAnswers) {
            boolean currentPossibleAnswerIsCorrect = currentPossibleAnswer.equals(currentQuestion.getCorrectAnswer());
            boolean userAnsweredCorrect = choiceInt == possibleAnswers.indexOf(currentQuestion.getCorrectAnswer());
            boolean userAnsweredOnTime = secondsTookToAnswer <= model.getAvailableTime();

            if (currentPossibleAnswerIsCorrect && userAnsweredCorrect) {
                if (userAnsweredOnTime)
                    model.actionIfCorrectAnswer();
                else {
                    view.printTimeEndedMessage();
                    model.stopExecution(2L);
                }
            }
        }
        return true;
    }

    /**
     * @see Gamemodable
     */
    @Override
    public void showPreQuestionFormat(Model model, Cli view) {
    }

    /**
     * @see Gamemodable
     */
    @Override
    public boolean hasPreQuestionFormat() {
        return false;
    }
}
