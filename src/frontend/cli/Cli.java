package frontend.cli;

import backend.general.PlayableNumOfPlayers;
import backend.onePlayer.OnePlayerGamemodes;
import backend.util.Util;

/**
 * This class handles the UI of the app using the classes {@code package backend } contains.
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
     * Gamemodes' are shown in the same sequence as the enum they are saved.
     * Also gamemodes' corresponding integer shown is the number that ordinal() method returns plus 1 so
     * the user does not see the choice 0 for better experience.
     * */
    public static void showAvailableChoices() {
        System.out.println("The game in the current version can be played from one player only.");
        System.out.println("Available gamemodes: ");
        for(PlayableNumOfPlayers currentGamemodeId : OnePlayerGamemodes.values()) /* printing the available gamemodes
                                                                                  and their corresponding integer value
                                                                                  plus 1 according to ordinal() method*/
            System.out.println(( currentGamemodeId.ordinal() + 1) + ". " + currentGamemodeId.toString());
    }

    /**
     * This method reads the user's choice about which gamemode he wants to play.
     * The choice is read in the same interval as {@code showAvailableChoices} method shows the available gamemodes.
     * With that being said choice belongs to an interval [1,<b>n</b>] with <b>n</b> being the amount of available
     * gamemodes.So after choice is read, it must be changed to be 0-indexed according to the way {@code ordinal()}
     * method returns the corresponding integers for values from an enum-type.
     * */
    public static int readGamemodeChoice() {
        String askGamemodeChoiceMessage = "Choose gamemode with typing a number from the available gamemodes list" +
                ": "; //the message that asks from the user to choose a gamemode
        String invalidInputFormatMessage = "Not a number!"; /*the message that is shown if the user does not type
                                                            an integer*/
        String inputOutOfBoundsMessage = "There is no such gamemode!";/*the message that is shown if the user
                                                                      types an integer that does not correspond to a valid
                                                                      gamemode*/
        System.out.print(askGamemodeChoiceMessage);

        int inputInt = 0;

        boolean inputIsInteger = false;
        boolean inputInsideLimits = false;
        while(!inputIsInteger || !inputInsideLimits) { // asking from user continuously to choose a gamemode until a valid choice is made
            try {
                inputInt = Util.readIntInput(); // try to read an integer from the user
                inputIsInteger = true; // if an exception is not thrown from readIntInput() then the user typed an integer

                if(inputInt >= 1 && inputInt <= OnePlayerGamemodes.values().length) //checking if the user typed an integer that corresponds to a valid gamemode
                    inputInsideLimits = true;
                else
                    throw new ArithmeticException();// if not then throw an arithmetic exception
            } catch (NumberFormatException exception) { // handling the case that user did not type an integer with printing the corresponding messages
                System.out.println(invalidInputFormatMessage);
                System.out.print(askGamemodeChoiceMessage);
            } catch (ArithmeticException exception) { // handling the case that user typed an integer that does not correspond to a valid gamemode with printing the
                                                      // corresponding messages
                System.out.println(inputOutOfBoundsMessage);
                System.out.print(askGamemodeChoiceMessage);
            }
        }
        return inputInt;
    }

    /**
     * This method reads the user's choice about how many number of rounds he wants to play.
     * */
    public static int readNumOfRoundsChoice() {
        String askNumOfRoundsChoiceMessage = "Choose the number of rounds you want to play from[1-10]: ";//the message that asks from the user to choose a number of rounds
        String invalidInputFormatMessage = "Not a number!";//the message that is shown if the user does not type an integer
        String inputOutOfBoundsMessage = "There is no such number of rounds!"; /*the message that is shown if the user
                                                                      types an integer that does not correspond to a valid
                                                                      number of rounds*/

        System.out.print(askNumOfRoundsChoiceMessage);

        int inputInt = 1;
        boolean inputIsInteger = false;
        boolean inputInsideLimits = false;
        while(!inputIsInteger || !inputInsideLimits) { // asking from user continuously to choose a number of rounds until a valid choice is made
            try {
                inputInt = Util.readIntInput(); // try to read an integer from the user
                inputIsInteger = true; // if an exception is not thrown from readIntInput() then the user typed an integer

                if(inputInt >= 1 && inputInt <= 10) //checking if the user typed an integer that corresponds to a valid number of rounds
                    inputInsideLimits = true;
                else
                    throw new ArithmeticException();// if not then throw an arithmetic exception
            } catch (NumberFormatException exception) {// handling the case that user did not type an integer with printing the corresponding messages
                System.out.println(invalidInputFormatMessage);
                System.out.print(askNumOfRoundsChoiceMessage);
            }catch (ArithmeticException exception) { // handling the case that user typed an integer that does not correspond to a valid number of rounds
                // with printing the corresponding messages
                System.out.println(inputOutOfBoundsMessage);
                System.out.print(askNumOfRoundsChoiceMessage);
        }
    }
    return inputInt;
}
}