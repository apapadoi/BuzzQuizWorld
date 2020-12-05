package model.gamemodes;

import model.Model;
import model.questions.Question;
import model.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;

/**
 * This class represents the gamemode High Stakes. At the start the question's category is shown and the player bets an amount of points. If he answers
 * correct he earns this amount of points, otherwise he loses them.
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 * @version 5.12.2020
 * */
public class HighStakes extends Gamemode {
    private int betAmount;
    private final List<Integer> availableBets;

    /**
     * Default constructor.
     */
    HighStakes() {
        super("At first the category of the question is shown.\nEach player places his bet.\n" +
                "Then, the question is shown, each player answers and if he answered correctly\n" +
                "he earns his bet, otherwise he loses his bet.\n" +
                "Available bets: 250,500,750,1000\n" +
                "If player's points get under 250 automatically the game bets all of his points.\n",15);
        this.betAmount=0;
        availableBets=new ArrayList<>(List.of(250,500,750,1000));
    }

    /**
     * @see Gamemode
     */
    @Override
    public String toString() { return "High Stakes";}

    /**
     * If the players answers correct update his score using the model component.
     * @see Gamemode
     */
    @Override
    public void actionIfCorrectAnswer(Model model) {
        model.updateScore(betAmount);
    }

    /**
     * Appends the default question format from {@code Gamemode} class with showing also the bet amount of the player.
     * @see Gamemode
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

    /**
     * Showing to the player his score and the current question's category.
     * @see Gamemode
     */
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

    /**
     * Checks if player has 0 score and if he has, adds 250 points so he can bet.
     * @param model instance of {@code Model} class
     */
    private void checkZeroScoreAndUpdate(Model model) {
        if (model.getScore()==0) {
            model.updateScore(250);
        }
    }

    /**
     * The High Stakes gamemode has a pre-question format so method returns true.
     * @see Gamemodable
     */
    @Override
    public boolean hasPreQuestionFormat() {
        return true;
    }

    /**
     * @see Gamemodable
     * @param model instance of {@code Model} class
     */
    @Override
    public void actionsPreQuestionsPhase(Model model) throws NumberFormatException,InputMismatchException{
        try {
            this.betAmount = Util.readIntInput(); // try to read an integer from the player

            if (this.availableBets.contains(betAmount)){ // if the player typed a bet from the available bets list
                if (this.betAmount>model.getScore()) { // if player does not has enough points
                    this.betAmount= Collections.min(availableBets); // bet automatically the minimum available bet ( here 250 points )
                    throw new RuntimeException("You tried to bet more than your score so the game automatically bets " + this.betAmount + ".");
                }
            }
            else // otherwise throw an InputMismatchException with the corresponding message.
                throw new InputMismatchException("No such betting amount.");
        } catch (NumberFormatException exception) { //user did not type an integer at all
            throw new NumberFormatException("Not a number!");
        }
    }

    /**
     * Returns the available bets and an ask message for the player.
     * @see Gamemode
     * @return the ask message as {@code String}
     */
    @Override
    public String getPreQuestionAskMessage() {
        StringBuilder preQuestionAskMessage = new StringBuilder();
        preQuestionAskMessage.append("Place your bet ");
        preQuestionAskMessage.append(this.availableBets.toString());
        preQuestionAskMessage.append(": ");
        return preQuestionAskMessage.toString();
    }

    /**
     * If the user answer wrong update his score using the {@code model} component.
     * @param model instance of {@code Model} class
     * @see Gamemode
     */
    @Override
    public void actionIfWrongAnswer(Model model) {
        model.updateScore(-betAmount);
    }

    /**
     * Returns the player's bet amount.
     * @return the bet amount as {@code int}
     */
    public int getBetAmount(){
        return this.betAmount;
    }

    /**
     * Returns the available bets.
     * @return the available bets as {@code List<Integer>}
     */
    public List<Integer> getAvailableBets(){
        return this.availableBets;
    }

    /**
     * Sets the bet amount of the player
     * @param betAmount the new bet amount
     */
    public void setBetAmount(int betAmount){
        this.betAmount=betAmount;
    }
}
