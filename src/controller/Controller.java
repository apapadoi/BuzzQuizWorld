package controller;

import model.fileHandler.FileHandler;
import model.Model;
import model.questions.Question;
import model.round.Round;
import model.util.UserAnswerTimer;
import model.util.Util;
import view.cli.Cli;

import java.io.IOException;
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
        // Printing the intro page
        view.printIntroPage(model.getVersion());

        // Reading the questions from the file using the fileHandler attribute
        try{
            fileHandler.readQuestions();
        } catch (IOException exception) {
            view.printStringWithoutLineSeparator("Could not find text file containing the questions.");
            return;
        }

        // Printing the available number of players that can play the game.
        view.printStringWithoutLineSeparator("The game in the current version can be played from one player only."+System.lineSeparator());

        // Asking from player to type his username.
        view.printStringWithoutLineSeparator("Type your username: ");
        // TODO
        //model.setUsername(Util.readStringInput()); // and then store his username in the model component

        // Asking from the user to choose the number of rounds he wants to play.
        this.readNumOfRoundsChoice();

        // Stopping the execution to create a "loading" effect and then clearing the screen.
        Util.stopExecution(2L);
        view.clearScreen();

        // Starting the gameplay.
        this.startGameplay();

        // Clearing the screen after the gameplay has ended and then printing the finish screen which contains the username of the current player
        // and his score.
        view.clearScreen();
        //view.printFinishPage(model.getUsername(), model.getScore());
    }

    /**
     * Reads the player's choice about the number of rounds using the {@code readValidIntInput} method of this class and stores the choice to the
     * model component.
     */
    private void readNumOfRoundsChoice() {
        int choice = this.readValidIntInput(1,10,
                "Not a number!"+System.lineSeparator(),
                "Choose the number of rounds you want to play from[1-10]: ",
                "There is no such number of rounds!"+System.lineSeparator());

        model.setNumOfRoundsChoice(choice,this.fileHandler);
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
                    this.showQuestionFormat(currentQuestion, model.getRound(i), i); // show the current question depending the gamemode
                    String choice = this.readAnswer(); // read user's choice
                    validInput = this.processAnswer(choice, currentQuestion, model.getRound(i)); // and then process it
                } catch (NumberFormatException exception) { // if the user did not type a valid answer then
                    // print the corresponding message of the exception processAnswer method has thrown
                    view.printStringWithoutLineSeparator(exception.getMessage());
                    Util.stopExecution(2L);
                }
                view.clearScreen();
            }
        }
    }

    /**
     * Prints the current question and additional information needed depending the type of current gamemode using the {@code view} component
     * @param currentQuestion The current question
     * @param currentRound The current round
     * @param roundId The current round id with offset 0
     */
    private void showQuestionFormat(Question currentQuestion,Round currentRound,int roundId) {
        view.printStringWithoutLineSeparator(currentRound.getQuestionFormat(this.model,currentQuestion,roundId));
    }

    /**
     * Reads the player's answer and also counts how many seconds took him to answer using {@code UserAnswerTimer} class from {@code util}
     * package.
     * @return The player's answer as {@code String}
     */
    private String readAnswer() {
        UserAnswerTimer timer = new UserAnswerTimer();
        Thread timerThread = new Thread(timer);
        timerThread.start();
        String choice = Util.readStringInput();
        secondsTookToAnswer = timer.getSecondsCounted();
        timerThread.interrupt();
        return choice;
    }

    /**
     * Processes the player's answer and does the corresponding actions depending if the user answer on time, answered correct or answered wrong.
     * @param choice The player's answer
     * @param currentQuestion The current question
     * @param currentRound The current round
     * @return If the user typed a VALID answer as {@code boolean}
     * @throws NumberFormatException if the user did not type a valid answer
     */
    private boolean processAnswer(String choice,Question currentQuestion,Round currentRound) throws NumberFormatException{
        if(!model.getValidAnswers().contains(choice)) // if the player did not type a valid answer throw an exception
            throw new NumberFormatException("Not a valid answer!");

        if( secondsTookToAnswer > currentRound.getAvailableTime() ) { // if the player did not answer on time
            view.printStringWithoutLineSeparator("Unfortunately, available time has ended!"+System.lineSeparator()+"Correct answer: "+currentQuestion.getCorrectAnswer());
            Util.stopExecution(2L);
            currentRound.actionIfWrongAnswer(this.model);
            return true;
        }else if(this.userAnsweredCorrect(choice,currentQuestion)) { // if the player answered correct
            currentRound.actionIfCorrectAnswer(this.model);
            view.printStringWithoutLineSeparator("Correct Answer!");
            Util.stopExecution(1L);
            return true;
        } else { // if the player answered wrong
            view.printStringWithoutLineSeparator("Unfortunately, your answer was not correct!"+System.lineSeparator()+"Correct answer: "+currentQuestion.getCorrectAnswer());
            Util.stopExecution(2L);
            currentRound.actionIfWrongAnswer(this.model);
            return true;
        }
    }

    /**
     * Reads an int input from the player that is considered valid if the interval [{@code lowValidValue},{@code maxValidValue}] contains this int
     * @param lowValidValue the low value of the valid interval
     * @param maxValidValue the max value of the valid interval
     * @param numberFormatExceptionMessage the message that is shown when the player does not type an integer
     * @param askInputMessage the message that is shown to the player before the answer request
     * @param notANumberFromTheListMessage the message that is shown to the player if he typed an integer but not from the valid interval
     * @return the int choice of the player as {@code int}
     */
    private int readValidIntInput(int lowValidValue, int maxValidValue, String numberFormatExceptionMessage, String askInputMessage,
                                 String notANumberFromTheListMessage) {
        boolean validInput = false;
        int choice =0;

        // iterate while the player does not type a valid input
        while (!validInput) {
            try {
                // ask from the user to type an integer
                view.printStringWithoutLineSeparator(askInputMessage);
                choice = Util.readIntInput();

                // check if the choice is inside the valid interval and print the corresponding message
                if (Util.isInsideLimits(choice, lowValidValue, maxValidValue))
                    validInput = true;
                else
                    view.printStringWithoutLineSeparator(notANumberFromTheListMessage);
            } catch (NumberFormatException exception) { // if the user did not type an integer at all, method Util.readIntInput throws an exception
                                                        // and this catch block handles the exception with printing the corresponding message
                view.printStringWithoutLineSeparator(numberFormatExceptionMessage);
            }

        }
        return choice;
    }
}
