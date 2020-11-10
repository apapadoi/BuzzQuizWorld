package backend.general;

import frontend.cli.Cli;

/**
 * This class handles the flow of the program.
 * @author Tasos Papadopoulos
 * @version 10.11.2020
 * */
public class BuzzGame {
    public static void main(String[] args) {
        Cli.printIntro();
        Cli.showAvailableChoices();
    }
}
