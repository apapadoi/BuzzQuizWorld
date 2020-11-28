package controller;

import model.Model;
import model.gamemodes.HighStakes;
import model.gamemodes.PointBuilder;
import model.questions.Question;
import model.util.InputOutOfBoundsException;
import model.util.UserAnswerTimer;
import model.util.Util;
import view.cli.Cli;

/**
 * @author Tasos Papadopoulos
 * @version 28.11.2020
 * */
public class Controller implements Runnable {
    private final Model model;
    private final Cli view;
    private int secondsTookToAnswer;

    public Controller(/*NumerablePlayersGamemode availableGamemodes*/) {
        view = new Cli();
        this.model = new Model(/*availableGamemodes*/);
        this.secondsTookToAnswer = 0;
    }

    @Override
    public void run() {
        // Printing the intro page.
        view.printIntroPage(model.getVersion());
        // Printing the available number of players that can play the game.
        view.printStringWithoutLineSeparator("The game in the current version can be played from one player only."+System.lineSeparator());
        // Printing the available gamemodes.
        view.printAvailableGamemodeChoices(model.getAvailableGamemodes());
        // Asking from players to type their username.
        view.printStringWithoutLineSeparator("Type your username: ");
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
        view.printFinishPage(model.getUsername(),model.getScore());
    }

    /**
     * This method reads the user's gamemode choice.
     */
    public void readGamemodeChoice() {
        int choice = this.readValidIntInput(this.view,1,model.getAvailableGamemodes().size(),
                "Not a number!"+System.lineSeparator(),
                "Choose a gamemode with typing a number from the available gamemodes list: ",
                "No such gamemode!"+System.lineSeparator());

        if (choice == 1) // creating an instance of the corresponding gamemode the user chose
            model.setCurrentGamemode(new PointBuilder());
        else
            model.setCurrentGamemode(new HighStakes());
    }

    /**
     * This method reads the user's number of rounds choice.
     */
    public void readNumOfRoundsChoice() {
        int choice = this.readValidIntInput(this.view,1,10,
                "Not a number!"+System.lineSeparator(),
                "Choose the number of rounds you want to play from[1-10]: ",
                "There is no such number of rounds!"+System.lineSeparator());

        model.setNumOfRoundsChoice(choice);
    }

    /**
     * This method starts the gameplay of the corresponding gamemode the user chose.
     */
    public void startGameplay() {
        for (int i = 0; i < model.getNumOfRounds(); i++) { // loop as many rounds as the user chose
            this.startRound(i);
        }
    }

    public void actionsPreQuestionPhase(Question currentQuestion) {
        if (model.hasPreQuestionFormat()) { // if any action needs to be performed before the question is shown
            // e.g High Stakes betting phase has to be shown before the question
            // is shown then these methods complete these actions
            view.printStringWithoutLineSeparator(model.getPreQuestionFormat(currentQuestion));

            boolean validInput = false;
            while (!validInput) {
                try {
                    view.printStringWithoutLineSeparator(model.getPreQuestionAskMessage());
                    model.actionsPreQuestionsPhase(currentQuestion);
                    validInput = true;
                } catch (InputOutOfBoundsException|NumberFormatException exception) {
                    view.printStringWithoutLineSeparator(exception.getMessage() + System.lineSeparator());
                }catch (RuntimeException exception) {
                    view.printStringWithoutLineSeparator(exception.getMessage()+System.lineSeparator());
                    validInput = true;
                }
            }
        }
    }

    public boolean userAnsweredCorrect(String choice,Question currentQuestion) throws NumberFormatException{
        int choiceInt = Integer.parseInt(choice);
        choiceInt--;

        return currentQuestion.getAnswers().get(choiceInt).equals(currentQuestion.getCorrectAnswer());
    }

    public boolean userSkipped(String choice) {
        return choice.equals("skip");
    }

    public boolean decreaseSkips(){
        if (model.getSkipsAvailable() > 0) {
            model.decreaseSkips();
            return true;
        }
        else {
            view.printStringWithoutLineSeparator("There are no more skips available!"+System.lineSeparator()+"You have to answer the question!");
            return false;
        }
    }

    public void startRound(int i) {
        for (Question currentQuestion : model.getRound(i).getQuestions()) { // loop as many questions as the round has
            boolean validInput = false;
            while (!validInput) {
                try {
                    this.actionsPreQuestionPhase(currentQuestion);
                    this.showQuestionFormat(currentQuestion,i);// showing the question depending the gamemode
                    String choice = this.readAnswer();
                    validInput = this.processAnswer(choice,currentQuestion);
                } catch (NumberFormatException exception) { // if the user did not type a valid answer then print
                    // print the corresponding message of not valid input
                    view.printStringWithoutLineSeparator(exception.getMessage());
                    Util.stopExecution(2L);
                }
                view.clearScreen();
            }
        }
    }

    public void showQuestionFormat(Question currentQuestion,int roundId) {
        view.printStringWithoutLineSeparator(model.getQuestionFormat(currentQuestion,roundId));
    }

    public String readAnswer() {
        UserAnswerTimer timer = new UserAnswerTimer();
        Thread timerThread = new Thread(timer);
        timerThread.start();
        String choice = Util.readStringInput();
        secondsTookToAnswer = timer.getSecondsCounted();
        timerThread.interrupt();
        return choice;
    }

    public boolean processAnswer(String choice,Question currentQuestion) throws NumberFormatException{
        if(!model.getValidAnswers().contains(choice))
            throw new NumberFormatException("Not a valid answer!");

        if(this.userSkipped(choice)) {
            return this.decreaseSkips();
        }else if( secondsTookToAnswer > model.getAvailableTime() ) {
            view.printStringWithoutLineSeparator("Unfortunately, available time has ended!"+System.lineSeparator()+"Correct answer: "+currentQuestion.getCorrectAnswer());
            Util.stopExecution(1L);
            return true;
        }else if(this.userAnsweredCorrect(choice,currentQuestion)) {
            model.actionIfCorrectAnswer(secondsTookToAnswer);
            return true;
        } else {
            view.printStringWithoutLineSeparator("Unfortunately, your answer was not correct!"+System.lineSeparator()+"Correct answer: "+currentQuestion.getCorrectAnswer());
            Util.stopExecution(1L);
            model.actionIfWrongAnswer();
            return true;
        }
    }

    public int readValidIntInput(Cli view, int lowValidValue, int maxValidValue, String numberFormatExceptionMessage, String askInputMessage,
                                 String notANumberFromTheListMessage) {
        boolean validInput = false;
        int choice =0;

        while (!validInput) { // asking from user continuously to choose a number of rounds until a valid choice is made
            try {
                view.printStringWithoutLineSeparator(askInputMessage);
                choice = Util.readIntInput();

                if (Util.isInsideLimits(choice, lowValidValue, maxValidValue))
                    validInput = true;
                else
                    view.printStringWithoutLineSeparator(notANumberFromTheListMessage);
            } catch (NumberFormatException exception) {
                view.printStringWithoutLineSeparator(numberFormatExceptionMessage);
            }

        }
        return choice;
    }
}
