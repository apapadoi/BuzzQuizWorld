package view.cli;

import java.util.List;

/**
 * This class handles the UI of the app using the classes {@code package model } contains.
 * @author Tasos Papadopoulos
 * @version 16.11.2020
 * */
public class Cli {
    public final String version = "16.11.2020";

    /**
     * This method prints the intro page in the command line.
     * */
    public void printIntroPage() {
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
        System.out.println("The game in the current version can be played from one player only.");
        System.out.println("Available gamemodes: ");
        for(String currentGamemode : availableGamemodes) {
            System.out.println(( availableGamemodes.indexOf(currentGamemode)+1) + ". " + currentGamemode);
        }
    }

    /**
     * This method reads the user's choice about which gamemode he wants to play.
     * The choice is read in the same interval as {@code showAvailableChoices} method shows the available gamemodes.
     * With that being said choice belongs to an interval [1,<b>n</b>] with <b>n</b> being the amount of available
     * gamemodes.So after choice is read, it must be changed to be 0-indexed according to the way {@code ordinal()}
     * method returns the corresponding integers for values from an enum-type.
     * */
    public void printGamemodeChoiceText() {
        //Print the message that asks from the user to choose a gamemode
        System.out.print("Choose gamemode with typing a number from the available gamemodes list: ");
    }

    public void printInputOutOfBoundsMessage() {
        System.out.println("There is no such gamemode!");
    }

    public void printNumberFormatExceptionMessage() {
        System.out.println("Not a number!");
    }

    public void printNumOfRoundsChoiceText() {
        //the message that asks from the user to choose a number of rounds
        System.out.print("Choose the number of rounds you want to play from[1-10]: ");
    }

    public void printNoSuchNumOfRoundsMessage() {
        System.out.println("There is no such number of rounds!");
    }
}