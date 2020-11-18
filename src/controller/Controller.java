package controller;

import model.Model;
import model.gamemodes.HighStakes;
import model.gamemodes.OnePlayerGamemodes;
import model.gamemodes.PointBuilder;
import model.questions.Question;
import view.cli.Cli;

/**
 * This class handles the flow of the program.
 *
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 * @version 18.11.2020
 */
public class Controller implements Runnable {
    private final Cli view;
    private final Model model;

    /**
     * Default Constructor
     */
    public Controller() {
        view = new Cli();
        model = new Model();
    }

    /**
     * This method reads the user's gamemode choice.
     */
    public void readGamemodeChoice() {
        boolean validInput = false;

        while (!validInput) { // asking from user continuously to choose a gamemode until a valid choice is made
            try {
                view.printGamemodeChoiceText();
                int choice = model.readIntInput();
                if (model.isInsideLimits(choice, 1, OnePlayerGamemodes.values().length)) {
                    if (choice == 1) // creating an instance of the corresponding gamemode the user chose
                        model.setGamemode(new PointBuilder());
                    else
                        model.setGamemode(new HighStakes());
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

    /**
     * This method reads the user's name.
     */
    public void readPlayersUsername() {
        view.printUsernameChoiceText();
        model.setPlayersUsername(model.readStringInput());
    }

    /**
     * This method reads the user's number of rounds choice.
     */
    public void readNumOfRoundsChoice() {
        boolean validInput = false;

        while (!validInput) { // asking from user continuously to choose a number of rounds until a valid choice is made
            try {
                view.printNumOfRoundsChoiceText();
                int choice = model.readIntInput();
                if (model.isInsideLimits(choice, 1, 10)) {
                    model.setNumOfRoundsChoice(choice);
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

    /**
     * This method prints the loading screen of the corresponding gamemode.
     */
    public void printLoadingScreen() {
        view.printLoadingScreen(model.getCurrentGamemodeString(), model.getCurrentGamemodeDescription());
    }

    /**
     * This method prints the available gamemode choices.
     */
    public void printAvailableGamemodeChoices() {
        view.printAvailableGamemodeChoices(this.model.getAvailableGamemodesAsString());
    }

    /**
     * This method prints the number of players that can play the game.
     */
    public void printNumOfPlayersAvailablePage() {
        view.printNumOfPlayersAvailablePage();
    }

    /**
     * This method prints the intro page of the game.
     */
    public void printIntroPage(String version) {
        view.printIntroPage(version);
    }

    /**
     * This method clears the command line.
     */
    public void clearScreen() {
        view.clearScreen();
    }

    /**
     * This method starts the gameplay of the corresponding gamemode the user chose.
     */
    public void startGameplay() {
        for (int i = 0; i < model.getNumOfRounds(); i++) { // loop as many rounds as the user chose
            for (Question currentQuestion : model.getRound(i).getQuestions()) { // loop as many questions as the round has
                boolean validInput = false;
                while (!validInput) {
                    try {
                        if (model.hasPreQuestionFormat()) { // if any action needs to be performed before the question is shown
                            // e.g High Stakes betting phase has to be shown before the question
                            // is shown then these methods complete these actions
                            model.actionsPreQuestionsPhase(view,currentQuestion);
                        }

                        model.showQuestionFormat(view, currentQuestion, i); // showing the question depending the gamemode
                        model.startTimer();
                        String choice = model.readStringInput();
                        int secondsTookToAnswer = model.getSecondsCounted();
                        model.stopTimer();

                        if (choice.equals("skip")) { // if the user chose to skip and has skips to burn decrease the amount
                            // of available skips
                            if (model.getSkipsAvailable() > 0) {
                                model.decreaseSkips();
                                validInput = true;
                            } else // if the user has no more skips then print the corresponding message
                                view.printOutOfSkipsMessage();
                        } else { // if the user chose to answer the question then do the corresponding actions for each gamemode
                            // using actionWhenAnswered method
                            validInput = model.actionWhenAnswered(choice, currentQuestion, secondsTookToAnswer, view);
                        }
                    } catch (NumberFormatException exception) { // if the user did not type a valid answer then print
                        // print the corresponding message of not valid input
                        view.printNotValidAnswerChoiceText();
                        model.stopExecution(2L);
                    }
                    view.clearScreen();
                }
            }
        }
    }

    /**
     * This method prints the finish page of the game.
     */
    public void printFinishPage() {
        view.printFinishPage(this.model.getUsername(), this.model.getScore());
    }

    /**
     * This method is called when the game starts.
     */
    @Override
    public void run() {
        this.printIntroPage(model.getVersion());
        this.printNumOfPlayersAvailablePage();
        this.printAvailableGamemodeChoices();
        this.readPlayersUsername();
        this.readGamemodeChoice();
        this.readNumOfRoundsChoice();
        this.model.stopExecution(2L);
        this.clearScreen();
        this.printLoadingScreen();
        this.model.stopExecution(3L);
        this.clearScreen();
        this.startGameplay();
        this.clearScreen();
        this.printFinishPage();
    }
}
