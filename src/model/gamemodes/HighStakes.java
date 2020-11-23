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
    public void actionIfCorrectAnswer(Model model) {
        model.updateScore(betAmount);
    }

    @Override
    public void showQuestionFormat(Model model, Cli view, Question currentQuestion, int roundId) {
        view.printPlayersBet(this.betAmount);
        super.showQuestionFormat(model,view,currentQuestion,roundId);
    }

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

            if (currentPossibleAnswerIsCorrect) {
                if (userAnsweredCorrect) {
                    if (userAnsweredOnTime)
                        this.actionIfCorrectAnswer(model);
                    else {
                        this.actionIfWrongAnswer(model);
                        view.printStringWithoutLineSeparator("Unfortunately, available time has ended!%nSo you don't earn any points!");
                        Util.stopExecution(2L);
                    }
                }
                else{
                    this.actionIfWrongAnswer(model);
                }
            }
        }
        return true;
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
                view.printStringWithoutLineSeparator("Not a number!%n");
            }
        }
    }

    public void actionIfWrongAnswer(Model model) {
        model.updateScore(-betAmount);
    }
}
