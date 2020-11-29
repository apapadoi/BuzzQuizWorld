package view.cli;

/**
 * This class handles the UI of the app.
 *
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 * @version 29.11.2020
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
        System.out.println("A quiz game with several gamemodes and players answering trivial questions for the " +
                "ultimate goal...what else winning!");
        System.out.println("Choose the number of rounds you want to play and let the game begin!");
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

    public void printFinishPage(String username, int score) {
        System.out.println("Game Finished!");
        System.out.println();
        System.out.println("Username : " + username);
        System.out.println();
        System.out.println("Score : " + score);
        System.out.println();
        System.out.println("Thank you for playing!");
    }
}