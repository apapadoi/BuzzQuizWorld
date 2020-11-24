package model.gamemodes;

import model.Model;
import model.questions.Question;
import view.cli.Cli;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents the gamemode High Stakes.
 *
 * @author Thodwrhs Myridis
 * @version 18.11.2020
 */
public class HighStakes implements Gamemodable {
    final String description;
    final int availableTime;
    int skipsAvailable;
    int betAmount;
    List<Integer> availableBets;

    /**
     * Default constructor.
     */
    public HighStakes() {
        this.description = "At first the category of the question is shown.\nEach player places his bet.\n" +
                "Then, the question is shown, each player answers and if he answered correctly\n" +
                "he earns his bet, otherwise he loses his bet.\n" +
                "Available bets: 250,500,750,1000\n" +
                "If player's points get under 250 automatically the game bets all of his points.\n";
        this.availableTime=5;
        this.skipsAvailable=3;
        this.betAmount=0;
        availableBets=new ArrayList<Integer>(List.of(250,500,750,1000));
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
    public int getSkipsAvailable() { return this.skipsAvailable; }

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
    @Override
    public void decreaseSkips() {
        this.skipsAvailable--;
    }

    /**
     * @see Gamemodable
     */
    @Override
    public void actionIfCorrectAnswer(Model model) {model.updateScore(betAmount);}

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
        view.printPlayersBet(this.betAmount);
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

            if (currentPossibleAnswerIsCorrect) {
                if (userAnsweredCorrect) {
                    if (userAnsweredOnTime)
                        model.actionIfCorrectAnswer();
                    else {
                        this.actionIfWrongAnswer(model);
                        view.printTimeEndedMessage();
                        model.stopExecution(2L);
                    }
                }
                else{
                    this.actionIfWrongAnswer(model);
                }
            }
        }
        return true;
    }

    /**
     * @see Gamemodable
     */
    @Override
    public void actionsPreQuestionsPhase(Model model, Cli view, Question currentQuestion) {
        if (model.getScore()==0){
            model.updateScore(250);
        }
        view.printPlayersScore(model.getScore());
        view.printQuestionsCategory(currentQuestion.getCategory());
        this.readBettingAmount(view,model);
    }

    /**
     * @see Gamemodable
     */
    @Override
    public boolean hasPreQuestionFormat() {
        return true;
    }

    public void readBettingAmount(Cli view,Model model) {
        boolean validInput = false;
        while (!validInput) { // asking from user continuously to choose a number of rounds until a valid choice is made
            try {
                view.printBettingPhaseAmount();
                this.betAmount = model.readIntInput();
                if (model.betIsInsideLimits(this.betAmount,this.availableBets)){
                    if (this.betAmount>model.getScore()) {
                        this.betAmount= Collections.min(availableBets);
                        view.printBetDoneAutomatically(this.betAmount);
                    }
                    validInput=true;
                }
                else
                    view.printNoSuchBettingAmount();

            } catch (NumberFormatException exception) { /* handling the case that user did not type an integer with
                                                        requesting to print the corresponding message from view */
                view.printNumberFormatExceptionMessage();
            }
        }
    }

    /**
     * Method that updates player's score if he selected wrong answer.
     */
    public void actionIfWrongAnswer(Model model) {model.updateScore(-betAmount);}
}
