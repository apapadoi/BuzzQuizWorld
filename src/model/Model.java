package model;

import model.gamemodes.*;
import model.player.Player;
import model.questions.Question;
import model.round.Round;
import java.util.*;

/**
 * This class represents the model of the app that handles the data.
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 * @version 28.11.2020
 */
public class Model{
    private final Player currentPlayer;
    private Gamemodable currentGamemode;
    private List<Round> rounds;
    private final List<String> validAnswers;

    /**Default constructor. Initializes the player of the game.*/
    public Model() {
        currentPlayer = new Player();
        this.validAnswers = new ArrayList<>(List.of("skip","1","2","3","4"));
    }

    public Gamemodable getCurrentGamemode() { return this.currentGamemode; }

    public List<String> getValidAnswers() {
        return this.validAnswers;
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
     * @param currentQuestion The current question of a round.
     * @param roundId         The id of the current round.
     */
    public String getQuestionFormat(Question currentQuestion, int roundId) {
        return currentGamemode.getQuestionFormat(this,currentQuestion,roundId);
    }

    public String getCurrentGamemodeString() {
        return this.currentGamemode.toString();
    }

    public boolean hasPreQuestionFormat() {
        return this.currentGamemode.hasPreQuestionFormat();
    }

    public String getPreQuestionFormat(Question currentQuestion) {
        return this.currentGamemode.getPreQuestionFormat(this,currentQuestion);
    }

    public String getPreQuestionAskMessage() {
        return this.currentGamemode.getPreQuestionAskMessage();
    }

    public void actionsPreQuestionsPhase(Question currentQuestion) throws NumberFormatException,ArithmeticException{
        this.currentGamemode.actionsPreQuestionsPhase(this,currentQuestion);
    }

    public void decreaseSkips() {
        this.currentGamemode.decreaseSkips();
    }

    public void actionIfCorrectAnswer(int secondsTookToAnswer) {
        this.currentGamemode.actionIfCorrectAnswer(this,secondsTookToAnswer);
    }

    public void actionIfWrongAnswer() {
        this.currentGamemode.actionIfWrongAnswer(this);
    }
}
