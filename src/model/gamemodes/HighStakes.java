package model.gamemodes;

import model.Model;
import model.questions.Question;
import model.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;

/**
 * This class represents the gamemode High Stakes.
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 * @version 29.11.2020
 * */
public class HighStakes extends Gamemode {
    int betAmount;
    final List<Integer> availableBets;

    /**
     * Default constructor.
     */
    public HighStakes() {
        super("At first the category of the question is shown.\nEach player places his bet.\n" +
                "Then, the question is shown, each player answers and if he answered correctly\n" +
                "he earns his bet, otherwise he loses his bet.\n" +
                "Available bets: 250,500,750,1000\n" +
                "If player's points get under 250 automatically the game bets all of his points.\n",5);
        this.betAmount=0;
        availableBets=new ArrayList<>(List.of(250,500,750,1000));
    }
    /**
     * @see Gamemodable
     */
    @Override
    public String toString() { return "High Stakes";}

    /**
     * @see Gamemodable
     */
    @Override
    public void actionIfCorrectAnswer(Model model,int secondsTookToAnswer) {
        model.updateScore(betAmount);
    }

    /**
     * @see Gamemodable
     */
    @Override
    public String getQuestionFormat(Model model,Question currentQuestion, int roundId) {
        StringBuilder questionFormat = new StringBuilder();
        questionFormat.append("Your bet is : ");
        questionFormat.append(this.betAmount);
        questionFormat.append(System.lineSeparator());
        questionFormat.append(super.getQuestionFormat(model,currentQuestion,roundId));

        return questionFormat.toString();
    }

    @Override
    public String getPreQuestionFormat(Model model,Question currentQuestion) {
        StringBuilder preQuestionFormat = new StringBuilder();
        this.checkZeroScoreAndUpdate(model);
        preQuestionFormat.append("Your score : ");
        preQuestionFormat.append(model.getScore());
        preQuestionFormat.append(System.lineSeparator());
        preQuestionFormat.append("Question's category : ");
        preQuestionFormat.append(currentQuestion.getCategory());
        preQuestionFormat.append(System.lineSeparator());

        return preQuestionFormat.toString();
    }

    public void checkZeroScoreAndUpdate(Model model) {
        if (model.getScore()==0) {
            model.updateScore(250);
        }
    }

    /**
     * @see Gamemodable
     */
    @Override
    public boolean hasPreQuestionFormat() {
        return true;
    }

    /**
     * @see Gamemodable
     */
    @Override
    public void actionsPreQuestionsPhase(Model model,Question currentQuestion) throws NumberFormatException,InputMismatchException{
        try {
            this.betAmount = Util.readIntInput();

            if (this.availableBets.contains(betAmount)){
                if (this.betAmount>model.getScore()) {
                    this.betAmount= Collections.min(availableBets);
                    throw new RuntimeException("You tried to bet more than your score so the game automatically bets " + this.betAmount + ".");
                }
            }
            else
                throw new InputMismatchException("No such betting amount.");
        } catch (NumberFormatException exception) { /* handling the case that user did not type an integer with
                                                        requesting to print the corresponding message from view */
            throw new NumberFormatException("Not a number!");
        }
    }

    @Override
    public String getPreQuestionAskMessage() {
        StringBuilder preQuestionAskMessage = new StringBuilder();
        preQuestionAskMessage.append("Place your bet ");
        preQuestionAskMessage.append(this.availableBets.toString());
        preQuestionAskMessage.append(": ");
        return preQuestionAskMessage.toString();
    }

    @Override
    public void actionIfWrongAnswer(Model model) {
        model.updateScore(-betAmount);
    }
}
