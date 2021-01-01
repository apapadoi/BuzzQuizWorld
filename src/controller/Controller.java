package controller;

import model.fileHandler.FileHandler;
import model.Model;
import model.questions.Question;
import model.round.Round;
import model.util.Util;
import view.cli.Cli;
import java.util.InputMismatchException;

/**
 * This class controls the flow of the program and the {@code Model} and {@code View} components. Objects of this class
 * behave as {@code Runnable} so it can be calculated how many seconds took the user to answer a question.
 * Creating a thread using an object of this class and calling {@code start} method calls the {@code run} method and starts the
 * game.
 * @author Tasos Papadopoulos
 * @version 5.12.2020
 * */
public class Controller implements Runnable {
    private final Model model;
    private final Cli view;
    private int secondsTookToAnswer;
    private final FileHandler fileHandler;

    /**
     * Create a controller using the given {@code Model},{@code View} and {@code fileHandler} components.
     * @param model An instance of {@code Model} class that stores the data of the app.
     * @param view An instance of {@code Cli} class that will show the info to the player.
     * @param fileHandler An instance of {@code fileHandler} class that handles the files of the app.
     */
    public Controller(Model model,Cli view,FileHandler fileHandler) {
        this.view = view;
        this.model = model;
        this.secondsTookToAnswer = 0;
        this.fileHandler = fileHandler;
    }

    /**
     * Method that is called when a thread is created using an instance of this class and then thread's {@code start} method is called.
     * When invoked, it starts the flow of the main program.
     */
    @Override
    public void run() {
        // TODO
        // Starting the gameplay.
        this.startGameplay();

        // Clearing the screen after the gameplay has ended and then printing the finish screen which contains the username of the current player
        // and his score.
        view.clearScreen();
        //view.printFinishPage(model.getUsername(), model.getScore());
    }

    /**
     * Starts the gameplay and starts each round.
     */
    private void startGameplay() {
        for (int i = 0; i < model.getNumOfRounds(); i++) { // iterate as many rounds as the user chose
            this.startRound(i);
        }
    }

    /**
     * Does the actions that need to be done before each question is shown and an answer request is asked to the user.
     * If the current type of gamemode { e.g. Point Builder } does not have something to do before the question is shown then this method will not do
     * anything.
     * @param currentQuestion The current question that will be shown
     * @param currentRound The current round
     */
    @Deprecated
    private void actionsPreQuestionPhase(Question currentQuestion, Round currentRound) {
        if (currentRound.hasPreQuestionFormat()) { // if any action needs to be performed before the question is shown
                                                    // e.g High Stakes betting phase has to be shown before the question will be controlled by this method
            view.printStringWithoutLineSeparator(currentRound.getPreQuestionFormat(this.model,currentQuestion)); // getting the info that needs to be shown
            // to the user depending the current gamemode ( e.g High Stakes gamemode shows the available bets ) and showing it using the view component

            // iterating until the user types a valid input
            boolean validInput = false;
            while (!validInput) {
                try {
                    view.printStringWithoutLineSeparator(currentRound.getPreQuestionAskMessage()); // showing the request message depending the gamemode
                    currentRound.actionsPreQuestionsPhase(this.model);   // and calling the actionsPreQuestionsPhase method of the current round
                    validInput = true;                                                  // the method throws exceptions depending the invalid input the user
                                                                                        // typed and then these exceptions are handled from the controller
                                                                                        // component

                // each catch block handles the exception with showing the corresponding message and stopping the execution so the player can read what
                // he typed wrong
                } catch (InputMismatchException |NumberFormatException exception) {
                    view.printStringWithoutLineSeparator(exception.getMessage() + System.lineSeparator());
                    Util.stopExecution(1L);
                // if a RuntimeException is thrown then it means that the user typed a correct input but with logical mistakes
                // e.g. in the HighStakes gamemode if the user tried to bet more than his score although he typed an integer he does not have enough
                // points to bet so the input is valid but external actions need to be done by the gamemode itself ( e.g. betting a default amount )
                }catch (RuntimeException exception) {
                    view.printStringWithoutLineSeparator(exception.getMessage()+System.lineSeparator());
                    Util.stopExecution(2L);
                    validInput = true;
                }
            }
        }
    }

    /**
     * Checks if the user answered correct to the current question
     * @param choice The answer of the user as {@code String}
     * @param currentQuestion The current question
     * @return If the user answered correct as {@code boolean}
     * @throws NumberFormatException if the user did not type an integer
     */
    private boolean userAnsweredCorrect(String choice,Question currentQuestion) throws NumberFormatException{
        int choiceInt = Integer.parseInt(choice); // convert string choice to integer
        choiceInt--; // convert choice to offset 0

        // check if user's answer is correct
        return currentQuestion.getAnswers().get(choiceInt).equals(currentQuestion.getCorrectAnswer());
    }

    /**
     * Does the actions that need to be done for each question of the round with id {@code i}
     * @param i the id of the round that is about to start with offset 0
     */
    private void startRound(int i) {
        for (Question currentQuestion : model.getRound(i).getQuestions()) { // iterate as many questions as the round has

            // print the loading screen depending the current gamemode
            view.printLoadingScreen(model.getRound(i).getGamemodeString(), model.getRound(i).getGamemodeDescription());
            // and stop the execution so the player can read the gamemode's description
            Util.stopExecution(2L);
            view.clearScreen();

            // do the actions that need to be done before the current questions is shown
            this.actionsPreQuestionPhase(currentQuestion, model.getRound(i));
            view.clearScreen();

            // iterate while the user has not typed a valid input
            boolean validInput = false;
            while (!validInput) {
                try {
                 // TODO CHECK  // this.showQuestionFormat(currentQuestion, model.getRound(i), i); // show the current question depending the gamemode
                // TODO CHECK   // String choice = this.readAnswer(); // read user's choice
                // TODO CHECK   // validInput = this.processAnswer(choice, currentQuestion, model.getRound(i)); // and then process it
                } catch (NumberFormatException exception) { // if the user did not type a valid answer then
                    // print the corresponding message of the exception processAnswer method has thrown
                    view.printStringWithoutLineSeparator(exception.getMessage());
                    Util.stopExecution(2L);
                }
                view.clearScreen();
            }
        }
    }
}
