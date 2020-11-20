package controller;

import model.Model;
import model.gamemodes.HighStakes;
import model.gamemodes.PointBuilder;
import model.questions.Question;
import model.util.Util;
import view.View;
import view.cli.Cli;

public class Controller implements Runnable {
    final Model model;
    final View view;

    public Controller(/*NumerablePlayersGamemode availableGamemodes*/) {
        view = new Cli();
        this.model = new Model(/*availableGamemodes*/);
    }

    @Override
    public void run() {
        // Printing the intro page.
        view.printIntroPage(model.getVersion());
        // Printing the available number of players that can play the game.
        view.printNumOfPlayersAvailablePage();
        // Printing the available gamemodes.
        view.printAvailableGamemodeChoices(model.getAvailableGamemodes());
        // Asking from the user to type his username.
        view.printUsernameChoiceText();
        model.setUsername(Util.readStringInput());
        // Asking from the user to choose what gamemode he wants to play.
        this.readGamemodeChoice();
        // Asking from the user to choose the number of rounds he wants to play.
        this.readNumOfRoundsChoice();
        // Stopping the execution to create a "loading" effect and then clearing the screen.
        Util.stopExecution(2L);
        view.clearScreen();
        // Printing the loading screen and then stopping the execution to create a "loading" effect and then clearing the screen.
        view.printLoadingScreen(model.getCurrentGamemodeString(), model.getCurrentGamemodeDescription());
        Util.stopExecution(3L);
        view.clearScreen();
        // Starting the gameplay.
        this.startGameplay();
        // Clearing the screen after the gameplay has ended and then printing the finish screen.
        view.clearScreen();
        view.printFinishPage(model.getUsername(), model.getScore());
    }

    /**
     * This method reads the user's gamemode choice.
     */
    public void readGamemodeChoice() {
        int choice = model.readValidIntInput(this.view,1,model.getAvailableGamemodes().size(),
                "Not a number!",
                "Choose a gamemode with typing a number from the available gamemodes list: ",
                "No such gamemode!");

        if (choice == 1) // creating an instance of the corresponding gamemode the user chose
            model.setCurrentGamemode(new PointBuilder());
        else
            model.setCurrentGamemode(new HighStakes());
    }

    /**
     * This method reads the user's number of rounds choice.
     */
    public void readNumOfRoundsChoice() {
        int choice = model.readValidIntInput(this.view,1,10,
                "Choose the number of rounds you want to play from[1-10]: ",
                "Not a number!","There is no such number of rounds!");

        model.setNumOfRoundsChoice(choice);
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
                        String choice = Util.readStringInput();
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
                        Util.stopExecution(2L);
                    }
                    view.clearScreen();
                }
            }
        }
    }
}
