package model;

import model.gamemodes.Gamemodable;
import model.gamemodes.OnePlayerGamemodes;
import model.player.Player;
import model.questions.Question;
import model.round.Round;
import model.util.MyTimer;
import view.cli.Cli;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * This class represents the model of the app.
 *
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 * @version 18.11.2020
 */
public class Model {
    final Player currentPlayer;
    Gamemodable currentGamemode;
    List<Round> rounds;
    MyTimer timer;
    Thread timerThread;
    Date version;
    DateFormat versionFormat;

    /**
     * Default constructor. Initializes one player and the version.
     */
    public Model() {
        currentPlayer = new Player();
        versionFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = Calendar.getInstance();
        version = calendar.getTime();
    }

    /**
     * Returns the version of the game as {@code String}.
     *
     * @return String
     */
    public String getVersion() {
        return versionFormat.format(version);
    }

    /**
     * Returns the current gamemode as {@code String}.
     *
     * @return String
     */
    public String getCurrentGamemodeString() {
        return currentGamemode.toString();
    }

    /**
     * Returns the current gamemode's description as {@code String}.
     *
     * @return String
     */
    public String getCurrentGamemodeDescription() {
        return currentGamemode.getDescription();
    }

    /**
     * Returns the number of rounds the user chose as {@code int}.
     *
     * @return int
     */
    public int getNumOfRounds() {
        return this.rounds.size();
    }

    /**
     * Setter for the current gamemode.
     */
    public void setGamemode(Gamemodable gamemode) {
        this.currentGamemode = gamemode;
    }

    /**
     * Returns the available gamemodes as {@code List<String>}
     *
     * @return {@code List} of {@code String}
     */
    public List<String> getAvailableGamemodesAsString() {
        List<String> gamemodes = new ArrayList<>();

        for (OnePlayerGamemodes gamemode : OnePlayerGamemodes.values())
            gamemodes.add(gamemode.toString());

        return gamemodes;
    }

    /**
     * Setter for the number of rounds.
     */
    public void setNumOfRoundsChoice(int choice) {
        rounds = new ArrayList<>(choice);
        for (int i = 0; i < choice; i++) {
            rounds.add(new Round());
        }
    }

    /**
     * Setter for the user's name.
     */
    public void setPlayersUsername(String username) {
        currentPlayer.setUsername(username);
    }

    /**
     * Returns the user's name as {@code String}.
     *
     * @return String
     */
    public String getUsername() {
        return this.currentPlayer.getUsername();
    }

    /**
     * Returns the user's score as {@code int}.
     *
     * @return int
     */
    public int getScore() {
        return this.currentPlayer.getScore();
    }

    /**
     * Returns the number of available skips as {@code int}.
     *
     * @return int
     */
    public int getSkipsAvailable() {
        return this.currentGamemode.getSkipsAvailable();
    }

    /**
     * Returns the round that has index {@code i} as {@code Round}.
     *
     * @param i The index of the round we want to get.
     * @return Round
     */
    public Round getRound(int i) {
        return this.rounds.get(i);
    }

    /**
     * Returns the available time as {@code int}.
     *
     * @return int
     */
    public int getAvailableTime() {
        return this.currentGamemode.getAvailableTime();
    }

    /**
     * Method that performs the actions that must be done when user answered correctly.
     */
    public void actionIfCorrectAnswer() {
        this.currentGamemode.actionIfCorrectAnswer(this);
    }

    /**
     * Method that decreases the number of skips available.
     */
    public void decreaseSkips() {
        this.currentGamemode.decreaseSkips();
    }

    /**
     * Method that updates user's score with adding the {@code amount} value.
     * No checking is done on the {@code amount} parameter's value.
     *
     * @param amount The amount we want to add to the user's score.
     */
    public void updateScore(int amount) {
        this.currentPlayer.addScore(amount);
    }

    /**
     * Method that shows the current question depending the gamemode using the {@code view} parameter.
     *
     * @param view            The object that handles User Interface.
     * @param currentQuestion The current question of a round.
     * @param roundId         The id of the current round.
     */
    public void showQuestionFormat(Cli view, Question currentQuestion, int roundId) {
        this.currentGamemode.showQuestionFormat(this, view, currentQuestion, roundId);
    }

    /**
     * Method that starts a timer that is used to count how long took the user to answer the question.
     * Implemented using multithreading.
     */
    public void startTimer() {
        timer = new MyTimer(this);
        timerThread = new Thread(timer);
        timerThread.start();
    }

    /**
     * Method that returns the seconds the timer counted until the invoke was done as {@code int}
     *
     * @return int
     */
    public int getSecondsCounted() {
        return timer.getSecondsCounted();
    }

    /**
     * Method that stops the timer.
     */
    public void stopTimer() {
        timerThread.interrupt();
    }

    /**
     * Method that performs the actions that must be done when user decides to not skip a question and answer it(correctly or not).
     *
     * @param choice              The user's choice as {@code String}
     * @param currentQuestion     The current question of a round.
     * @param secondsTookToAnswer The seconds that was needed for the user to answer the current question.
     * @param view                The object that handles User Interface.
     */
    public boolean actionWhenAnswered(String choice, Question currentQuestion, int secondsTookToAnswer, Cli view) throws NumberFormatException {
        return this.currentGamemode.actionWhenAnswered(choice, currentQuestion, secondsTookToAnswer, view, this);
    }

    /**
     * Method that returns if the current gamemode has pre question page to show.
     */
    public boolean hasPreQuestionFormat() {
        return this.currentGamemode.hasPreQuestionFormat();
    }

    /**
     * This method completes the actions that need to be done in the pre question's page..
     *
     * @param view The object that handles User Interface.
     */
    public void actionsPreQuestionsPhase(Cli view, Question currentQuestion) {
        this.currentGamemode.actionsPreQuestionsPhase(this, view,currentQuestion);
    }

    /**
     * Method that stops the execution for {@code seconds} seconds.
     *
     * @param seconds The amount of seconds we want to stop the execution.
     */
    public void stopExecution(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Method that tries to read from the user an integer input and returns it as {@code int}.
     *
     * @return The integer the user typed as {@code int}
     * @throws NumberFormatException if the user did not type an integer at all.
     */
    public int readIntInput() throws NumberFormatException {
        return Integer.parseInt(new Scanner(System.in).nextLine());
    }

    /**
     * Method that checks if the {@code value} is inside the interval [{@code lower},{@code upper}]
     *
     * @param value The value we want to check if it inside the interval
     * @param lower The minimum value of the interval.
     * @param upper The maximum value of the interval.
     * @return True if the value is inside the interval.
     * @throws ArithmeticException if the value is not inside the interval
     */
    public boolean isInsideLimits(int value, int lower, int upper) throws ArithmeticException {
        if (value >= lower && value <= upper) //checking if the user typed an integer that corresponds to a valid gamemode
            return true;

        throw new ArithmeticException();// if not then throw an arithmetic exception
    }

    /**
     * Method that reads a string from the user and returns it as {@code String}.
     *
     * @return The string the user typed as {@code String}.
     */
    public String readStringInput() {
        return new Scanner(System.in).nextLine();
    }

    /**
     * Method that checks if the betting amount is inside its available values.
     */
    public boolean betIsInsideLimits(int choice,List<Integer> availablePage) {
        for (Integer choices:availablePage)
            if (choices.equals(choice))
                return true;

        return false;
    }
}
