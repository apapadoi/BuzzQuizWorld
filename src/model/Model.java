package model;

import model.gamemodes.*;
import model.player.Player;
import model.questions.Question;
import model.round.Round;
import model.util.MyTimer;
import model.util.Util;
import view.cli.Cli;

import java.util.*;

/**
 * This class represents the model of the app that handles the data.
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 * @version 23.11.2020
 */
public class Model{
    final Player currentPlayer;
    Gamemodable currentGamemode;
    List<Round> rounds;
    final MyTimer timer;
    final Thread timerThread;

    /**Default constructor. Initializes the player of the game.*/
    public Model() {
        currentPlayer = new Player();
        timer = new MyTimer(this);
        timerThread = new Thread(timer);
    }

    public String getCurrentGamemodeDescription() {
        return this.currentGamemode.getDescription();
    }

    public List<String> getAvailableGamemodes() {
        return new OnePlayerGamemodes().getAvailableGamemodes();
    }

    public void setUsername(String username) {
        currentPlayer.setUsername(username);
    }

    public void setCurrentGamemode(Gamemodable currentGamemode) {
        this.currentGamemode = currentGamemode;
    }

    /**
     * Returns the version of the game as {@code String}.
     * @return String
     */
    public String getVersion() {
        return "18.11.2020";
    }

    /** Setter for the number of rounds.
     */
    public void setNumOfRoundsChoice(int choice) {
        rounds = new ArrayList<>(choice);
        for (int i = 0; i < choice; i++) {
            rounds.add(new Round());
        }
    }

    public int getNumOfRounds() {
        return this.rounds.size();
    }

    /** Returns the user's name as {@code String}.
     * @return String
     */
    public String getUsername() {
        return this.currentPlayer.getUsername();
    }

    /**Returns the user's score as {@code int}}
     * @return int
     */
    public int getScore() {
        return this.currentPlayer.getScore();
    }

    /**Returns the number of available skips as {@code int}.
     * @return int
     */
    public int getSkipsAvailable() {
        return this.currentGamemode.getSkipsAvailable();
    }

    /** Returns the round that has index {@code i} as {@code Round}.
     * @param i The index of the round we want to get.
     * @return Round
     */
    public Round getRound(int i) {
        return this.rounds.get(i);
    }

    /** Returns the available time as {@code int}.
     * @return int
     */
    public int getAvailableTime() {
        return this.currentGamemode.getAvailableTime();
    }

    /** Method that updates user's score with adding the {@code amount} value.
     * No checking is done on the {@code amount} parameter's value.
     * @param amount The amount we want to add to the user's score.
     */
    public void updateScore(int amount) {
        this.currentPlayer.addScore(amount);
    }

    /** Method that shows the current question depending the gamemode using the {@code view} parameter.
     * @param view            The object that handles User Interface.
     * @param currentQuestion The current question of a round.
     * @param roundId         The id of the current round.
     */
    public void showQuestionFormat(Cli view, Question currentQuestion, int roundId) {
        this.currentGamemode.showQuestionFormat(this, view, currentQuestion, roundId);
    }

    /** Method that starts a timer that is used to count how long took the user to answer the question.
     * Implemented using multithreading.
     */
    public void startTimer() {
        timerThread.start();
    }

    /** Method that stops the timer.
     */
    public void stopTimer() {
        timerThread.interrupt();
    }

    public String getCurrentGamemodeString() {
        return this.currentGamemode.toString();
    }

    public int getSecondsCounted() {
        return this.timer.getSecondsCounted();
    }

    public boolean hasPreQuestionFormat() {
        return this.currentGamemode.hasPreQuestionFormat();
    }

    public void actionsPreQuestionsPhase(Cli view,Question currentQuestion) {
        this.currentGamemode.actionsPreQuestionsPhase(this,view,currentQuestion);
    }

    public void decreaseSkips() {
        this.currentGamemode.decreaseSkips();
    }

    public boolean actionWhenAnswered(String choice,Question currentQuestion,int secondsTookToAnswer,Cli view) {
        return this.currentGamemode.actionWhenAnswered(choice,currentQuestion,secondsTookToAnswer,view,this);
    }

    public int readValidIntInput(Cli view, int lowValidValue, int maxValidValue, String numberFormatExceptionMessage, String askInputMessage,
                                 String notANumberFromTheListMessage) {
        boolean validInput = false;
        int choice =0;

        while (!validInput) { // asking from user continuously to choose a number of rounds until a valid choice is made
            try {
                view.printStringWithoutLineSeparator(askInputMessage);
                choice = Util.readIntInput();

                if (Util.isInsideLimits(choice, lowValidValue, maxValidValue))
                    validInput = true;
                else
                    view.printStringWithoutLineSeparator(notANumberFromTheListMessage+System.lineSeparator());
            } catch (NumberFormatException exception) {
                view.printStringWithoutLineSeparator(numberFormatExceptionMessage);
            }

        }
        return choice;
    }
}
