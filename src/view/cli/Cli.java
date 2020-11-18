package view.cli;

import model.questions.Category;
import model.questions.Difficulty;

import java.util.List;

/**
 * This class handles the UI of the app.
 *
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 * @version 18.11.2020
 */
public class Cli {
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
     * This method prints the number of players that the game supports in the current version.
     * */
    public void printNumOfPlayersAvailablePage() {
        System.out.println("The game in the current version can be played from one player only.");
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
    public void printGamemodeChoiceText() {
        //Print the message that asks from the user to choose a gamemode
        System.out.print("Choose gamemode with typing a number from the available gamemodes list: ");
    }

    /**
     * This method prints a message when input is out of bounds.
     */
    public void printInputOutOfBoundsMessage() {
        System.out.println("There is no such gamemode!");
    }

    /**
     * This method prints a message when input is not a number.
     */
    public void printNumberFormatExceptionMessage() {
        System.out.println("Not a number!");
    }

    /**
     * This method prints a message that asks the player to choose a number of rounds.
     */
    public void printNumOfRoundsChoiceText() {
        //the message that asks from the user to choose a number of rounds
        System.out.print("Choose the number of rounds you want to play from[1-10]: ");
    }

    /**
     * This method prints a message when input of rounds is out of bounds.
     */
    public void printNoSuchNumOfRoundsMessage() {
        System.out.println("There is no such number of rounds!");
    }

    /**
     * This method prints current gamemode and its description with a loading screen after.
     * @param currentGamemodeString The current gamemode {@code String}
     * @param currentGamemodeDescription The current gamemode's description {@code String}
     */
    public void printLoadingScreen(String currentGamemodeString, String currentGamemodeDescription) {
        System.out.println(currentGamemodeString);
        System.out.println();
        System.out.println(currentGamemodeDescription);
        System.out.println();
        System.out.print("Loading...");
    }

    /**
     * This method clears the screen.
     */
    public void clearScreen() {
        for (int i = 0; i < 50; i++)
            System.out.println();
    }

    /**
     * This method prints current gamemode.
     * @param currentGamemode The current gamemode {@code String}
     */
    public void printCurrentGamemode(String currentGamemode) {
        System.out.println("Gamemode: " + currentGamemode);
    }

    /**
     * This method prints a message to ask a player to type his username.
     */
    public void printUsernameChoiceText() {
        System.out.print("Type your username: ");
    }

    /**
     * This method prints the player's username
     * @param username Players username {@code String}
     */
    public void printCurrentPlayersUsername(String username) {
        System.out.println("Current player: " + username);
    }

    /**
     * This method prints current player's score
     * @param score Player's score {@code int}
     */
    public void printPlayersScore(int score) {
        System.out.println("Score: " + score);
    }

    /**
     * This method prints current round.
     * @param id Current round {@code int}
     */
    public void printRoundId(int id) {
        System.out.println("Round : " + (id + 1));
    }

    /**
     * This method prints player's available skips.
     * @param skips Player's available skips {@code int}
     */
    public void printSkipsAvailable(int skips) {
        System.out.println("Available skips : " + skips);
    }

    /**
     * This method prints question's category.
     * @param category Question's category {@code Category}
     */
    public void printQuestionsCategory(Category category) {
        System.out.println("Category : " + category.toString());
    }

    /**
     * This method prints question's difficulty.
     * @param difficulty Question's difficulty {@code Difficulty}
     */
    public void printQuestionsDifficulty(Difficulty difficulty) {
        System.out.println("Difficulty : " + difficulty.toString());
    }

    /**
     * This method prints available time.
     * @param availableTime available time{@code int}
     */
    public void printAvailableTime(int availableTime) {
        System.out.println("Available Time : " + availableTime);
    }

    /**
     * This method prints question's text.
     * @param questionsText Question's text{@code String}
     */
    public void printQuestionsText(String questionsText) {
        System.out.println(questionsText);
    }

    /**
     * This method prints question's answers.
     * @param answers Question's answers {@code List<String>}
     */
    public void printQuestionsAnswers(List<String> answers) {
        System.out.println("1. " + answers.get(0));
        System.out.println("2. " + answers.get(1));
        System.out.println("3. " + answers.get(2));
        System.out.println("4. " + answers.get(3));
    }

    /**
     * This method prints a message that asks the player to choose his answer or skip.
     */
    public void printChooseAnswerText() {
        System.out.println("Choose your answer or type 'skip' if you want to skip the question!");
        System.out.print(">");
    }

    /**
     * This method prints a message when the answer is not valid.
     */
    public void printNotValidAnswerChoiceText() {
        System.out.print("Not a valid answer!");
    }

    /**
     * This method prints a message when there is no more skips available for the player.
     */
    public void printOutOfSkipsMessage() {
        System.out.println("There are no more skips available!");
        System.out.println("You have to answer the question!");
    }

    /**
     * This method prints finish screen and shows player's username and his final score.
     * @param username Player's username {@code String}
     * @param score Player's final score {@code int}
     */
    public void printFinishPage(String username, int score) {
        System.out.println("Game Finished!");
        System.out.println();
        System.out.println("Username : " + username);
        System.out.println();
        System.out.println("Score : " + score);
        System.out.println();
        System.out.println("Thank you for playing!");
    }

    /**
     * This method prints a message when the player ran out of time.
     */
    public void printTimeEndedMessage() {
        System.out.println("Unfortunately, available time has ended!");
        System.out.println("So you don't earn any points!");
    }

    /**
     * This method prints a message (Gamemode: HighStakes) that asks the player to place his bet among available bet values.
     */
    public void printBettingPhaseAmount() {
        System.out.print("Place your bet [250,500,750,1000]: ");
    }

    /**
     * This method prints a message (Gamemode: HighStakes) when the bet value is out of bounds.
     */
    public void printNoSuchBettingAmount() {
        System.out.println("No such betting amount.");
    }

    /**
     * This method prints a message (Gamemode: HighStakes) to tell the player that he has no points > 250 so the game bets automatically his available points.
     * @param score Player's score {@code int}
     */
    public void printBetDoneAutomatically(int score) {
        System.out.println("Your score is under 250 so game bets automatically " + score + ".");
    }

    /**
     * This method prints player's bet choice.
     * @param bet Player's bet choice {@code int}
     */
    public void printPlayersBet(int bet) {
        System.out.println("Bet : " + bet);
    }
}