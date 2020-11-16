package controller;

import controller.util.Util;
import model.gameSessions.GameSession;
import model.gamemodes.Gamemode;
import model.gamemodes.OnePlayerGamemodes;
import view.cli.Cli;

/**
 * This class handles the flow of the program.
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 * @version 16.11.2020
 * */
public class Controller {
    private Cli view;
    private GameSession model;

    public Controller() {
        view = new Cli();
        model = new GameSession();
    }

    public Cli getView() {
        return new Cli();
    }

    public GameSession getGameSession () {
        return new GameSession();
    }

    public void readGamemodeChoice() {
        boolean validInput = false;

        while(!validInput) { // asking from user continuously to choose a gamemode until a valid choice is made
            try {
                view.printGamemodeChoiceText();
                int choice = Util.readIntInput();
                if (Util.isInsideLimits(choice, 1, OnePlayerGamemodes.values().length)) {
                    String idString=null;
                    for (OnePlayerGamemodes gamemode:OnePlayerGamemodes.values()){
                        if (gamemode.ordinal()==(--choice)){
                            idString=gamemode.toString();
                        }
                    }
                    Gamemode gamemode= new Gamemode(idString);
                }
                validInput = true;
            } catch (NumberFormatException exception) { /* handling the case that user did not type an integer with
                                                        requesting to print the corresponding message from view */
                view.printNumberFormatExceptionMessage();
            } catch (ArithmeticException exception) { /* handling the case that user typed an integer that does not correspond
                                                         to a valid gamemode with requesting to print the corresponding message
                                                         from view */
                view.printInputOutOfBoundsMessage();
            }
        }
    }

    public void readNumOfRoundsChoice() {
        boolean validInput = false;

        while(!validInput) { // asking from user continuously to choose a number of rounds until a valid choice is made
            try {
                view.printNumOfRoundsChoiceText();
                int choice = Util.readIntInput();
                if(Util.isInsideLimits(choice,1,10)) {
                    model.addNumOfRounds(choice);
                    model.initializeQuestions();
                }
                validInput = true;
            } catch (NumberFormatException exception) { /* handling the case that user did not type an integer with
                                                        requesting to print the corresponding message from view */
                view.printNumberFormatExceptionMessage();
            } catch (ArithmeticException exception) { /* handling the case that user typed an integer that does not correspond
                                                         to a valid number of rounds with requesting to print the corresponding
                                                         message from view */
                view.printNoSuchNumOfRoundsMessage();
            }
        }
    }
}
