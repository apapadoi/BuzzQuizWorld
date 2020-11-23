package view.cli;

import model.questions.Category;
import model.questions.Difficulty;

import java.util.List;

/**
 * This class handles the UI of the app.
 *
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 * @version 23.11.2020
 */
public class Cli{
    /**
     * This method prints the intro page in the command line.
     * @param version The version of the app
     */
    public void printIntroPage(String version) {
        System.out.println("Welcome to Buzz!: Quiz World Remaster");
        System.out.println("Developed by Tasos Papadopoulos");
        System.out.println("             Thodwris Myridhs");
        System.out.println("Version " + version + System.lineSeparator());
        System.out.println("A quiz game with several gamemodes to choose from and players answering trivial questions for the " +
                "ultimate goal...what else winning!");
        System.out.println("Choose the gamemode and the rounds you want to play and let the game begin!");
    }

    /**
     * This method prints the available gamemodes section in the command line.
     * Gamemodes' are shown in the same sequence as the enum they are saved.
     * Also gamemodes' corresponding integer shown is the number that ordinal() method returns plus 1 so
     * the user does not see the choice 0 for better experience.
     * */
    public void printAvailableGamemodeChoices(List<String> availableGamemodes) {
        System.out.println("Available gamemodes: ");
        int i = 1;
        for (String currentGamemode : availableGamemodes) {
            System.out.println((i++) + ". " + currentGamemode);
        }
    }

    /**
     * This method reads the user's choice about which gamemode he wants to play.
     * The choice is read in the same interval as {@code showAvailableChoices} method shows the available gamemodes.
     * With that being said choice belongs to an interval [1,<b>n</b>] with <b>n</b> being the amount of available
     * gamemodes.So after choice is read, it must be changed to be 0-indexed according to the way {@code ordinal()}
     * method returns the corresponding integers for values from an enum-type.
     */
    public void printStringWithoutLineSeparator(String text) {
        System.out.print(text);
    }

    public void printLoadingScreen(String currentGamemodeString, String currentGamemodeDescription) {
        System.out.println(currentGamemodeString);
        System.out.println();
        System.out.println(currentGamemodeDescription);
        System.out.println();
        System.out.print("Loading...");
    }

    public void clearScreen() {
        for (int i = 0; i < 50; i++)
            System.out.println();
    }

    public void printCurrentGamemode(String currentGamemode) {
        System.out.println("Gamemode: " + currentGamemode);
    }

    public void printCurrentPlayersUsername(String username) {
        System.out.println("Current player: " + username);
    }

    public void printPlayersScore(int score) {
        System.out.println("Score: " + score);
    }

    public void printRoundId(int id) {
        System.out.println("Round : " + (id + 1));
    }

    public void printSkipsAvailable(int skips) {
        System.out.println("Available skips : " + skips);
    }

    public void printQuestionsCategory(Category category) {
        System.out.println("Category : " + category.toString());
    }

    public void printQuestionsDifficulty(Difficulty difficulty) {
        System.out.println("Difficulty : " + difficulty.toString());
    }

    public void printAvailableTime(int availableTime) {
        System.out.println("Available Time : " + availableTime);
    }

    public void printQuestionsText(String questionsText) {
        System.out.println(questionsText);
    }

    public void printQuestionsAnswers(List<String> answers) {
        System.out.println("1. " + answers.get(0));
        System.out.println("2. " + answers.get(1));
        System.out.println("3. " + answers.get(2));
        System.out.println("4. " + answers.get(3));
    }

    public void printFinishPage(String username, int score) {
        System.out.println("Game Finished!");
        System.out.println();
        System.out.println("Username : " + username);
        System.out.println();
        System.out.println("Score : " + score);
        System.out.println();
        System.out.println("Thank you for playing!");
    }

    public void printBettingPhaseAmount() {
        System.out.print("Place your bet [250,500,750,1000]: ");
    }

    public void printBetDoneAutomatically(int score) {
        System.out.println("Your score is under 250 so game bets automatically " + score + ".");
    }

    public void printPlayersBet(int bet) {
        System.out.println("Bet : " + bet);
    }
}