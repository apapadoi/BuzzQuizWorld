package controller;

import model.Model;
import model.fileHandler.FileHandler;
import model.questions.Question;
import view.gui.GUI;
import view.gui.IntroFrame;

import java.io.IOException;
import java.util.List;

public class Dispatcher {
    private final Model model;
    private GUI view;
    private final FileHandler fileHandler;

    public Dispatcher(Model model, FileHandler fileHandler) {
        this.model = model;
        this.fileHandler = fileHandler;
        view = null;
    }

    public void setView(GUI view) {
        this.view = view;
    }

    public void dispatch(String request) {
        if(request.equalsIgnoreCase("addUsernames")) {
            List<String> usernames = view.getUsernames();
            if(usernames==null) {
                System.out.println("skata");
                return;
            }
            for(String string:usernames)
                System.out.println(string);
            model.setUsernames(usernames);
        }
        else if (request.equalsIgnoreCase("load")) {
            try {
                fileHandler.readQuestions();

            } catch (IOException exception) {
                // new ErrorFrame
                System.exit(-5);
            }
        }
        else if (request.equalsIgnoreCase("addNumOfRounds")) {
            this.model.setNumOfRoundsChoice(view.getNumOfRoundsChoice(), this.fileHandler);
        } else if(request.equalsIgnoreCase("updateData")) {
            Question currentQuestion = model.getRound(0).getQuestions().get(0);
            view.updateAnswers(currentQuestion.getAnswers());
            view.updateCategory(currentQuestion.getCategory());
            view.updateGamemode(model.getRound(0).getGamemodeString());
            view.updateQuestion(currentQuestion.getQuestionText());
            view.updateScore(model.getPlayers());
            view.updateRoundId("Round "+"0");
        }
    }
}
