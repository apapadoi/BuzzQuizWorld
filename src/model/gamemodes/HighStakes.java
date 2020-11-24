package model.gamemodes;

import model.Model;
import model.questions.Question;
import model.util.Util;
import view.cli.Cli;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Tasos Papadopoulos
 * @version 23.11.2020
 * */
public class HighStakes extends Gamemode {
    int betAmount;
    final List<Integer> availableBets;

    public HighStakes() {
        super("At first the category of the question is shown.\nEach player places his bet.\n" +
                "Then, the question is shown, each player answers and if he answered correctly\n" +
                "he earns his bet, otherwise he loses his bet.\n" +
                "Available bets: 250,500,750,1000\n" +
                "If player's points get under 250 automatically the game bets all of his points.\n",5,3);
        this.betAmount=0;
        availableBets=new ArrayList<>(List.of(250,500,750,1000));
    }

    @Override
    public String toString() {
        return "High Stakes";
    }

    @Override
    public void actionIfCorrectAnswer(Model model,int secondsTookToAnswer) {
        model.updateScore(betAmount);
    }

    @Override
    public void showQuestionFormat(Model model, Cli view, Question currentQuestion, int roundId) {
        view.printPlayersBet(this.betAmount);
        super.showQuestionFormat(model,view,currentQuestion,roundId);
    }

    @Override
    public void actionsPreQuestionsPhase(Model model, Cli view, Question currentQuestion) {
        if (model.getScore()==0){
            model.updateScore(250);
        }
        view.printPlayersScore(model.getScore());
        view.printQuestionsCategory(currentQuestion.getCategory());
        this.readBettingAmount(view,model);
    }

    @Override
    public boolean hasPreQuestionFormat() {
        return true;
    }

    private void readBettingAmount(Cli view, Model model) {
        boolean validInput = false;
        while (!validInput) { // asking from user continuously to choose a number of rounds until a valid choice is made
            try {
                view.printBettingPhaseAmount();
                this.betAmount = Util.readIntInput();

                if (this.availableBets.contains(betAmount)){
                    if (this.betAmount>model.getScore()) {
                        this.betAmount= Collections.min(availableBets);
                        view.printBetDoneAutomatically(this.betAmount);
                    }
                    validInput=true;
                }
                else
                    view.printStringWithoutLineSeparator("No such betting amount.");

            } catch (NumberFormatException exception) { /* handling the case that user did not type an integer with
                                                        requesting to print the corresponding message from view */
                view.printStringWithoutLineSeparator("Not a number!"+System.lineSeparator());
            }
        }
    }

    @Override
    public void actionIfWrongAnswer(Model model) {
        model.updateScore(-betAmount);
    }
}
