package model.gamemodes;

import model.Model;
import model.questions.Question;
import model.util.Util;
import view.View;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the gamemode Point Builder.
 *
 * @author Tasos Papadopoulos
 * @version 17.11.2020
 */
public class PointBuilder extends Gamemode {
    /**
     * Default Constructor.
     */
    public PointBuilder() {
        super("Each player that answers correctly earns 1000 points.",5,3);
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
    public void actionIfCorrectAnswer(Model model) {
        model.updateScore(1000);
    }

    /**
     * @see Gamemodable
     */
    @Override
    public boolean actionWhenAnswered(String choice, Question currentQuestion, int secondsTookToAnswer, View view, Model model) throws NumberFormatException {
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
                    this.actionIfCorrectAnswer(model);
                else {
                    view.printTimeEndedMessage();
                    Util.stopExecution(2L);
                }
            }
        }
        return true;
    }

    /**
     * @see Gamemodable
     */
    @Override
    public void actionsPreQuestionsPhase(Model model, View view, Question currentQuestion) { }

    /**
     * @see Gamemodable
     */
    @Override
    public boolean hasPreQuestionFormat() {
        return false;
    }
}
