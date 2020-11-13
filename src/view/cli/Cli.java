package view.cli;

import model.gamemodes.PlayableNumOfPlayersGamemode;
import model.gamemodes.OnePlayerGamemodes;

/**
 * This class handles the UI of the app using the classes {@code package model } contains.
 * @author Tasos Papadopoulos
 * @version 10.11.2020
 * */
public class Cli {
    public static final String version = "10.11.2020";

    /**
     * This method prints the intro section in the command line.
     * */
    public static void printIntro() {
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
     * */
    public static void showAvailableChoices() {
        System.out.println("The game in the current version can be played from one player only.");
        System.out.println("Available gamemodes: ");
        for(PlayableNumOfPlayersGamemode currentGamemodeId : OnePlayerGamemodes.values())
            System.out.println(( currentGamemodeId.ordinal() + 1) + ". " + currentGamemodeId.toString());
    }
}
