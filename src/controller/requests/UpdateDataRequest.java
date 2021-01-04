package controller.requests;

import controller.Dispatcher;
import model.Model;
import model.questions.Question;
import model.round.Round;
import view.gui.FinishFrame;
import view.gui.GUI;
import view.gui.OnePlayerFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UpdateDataRequest extends Request{
    private final ActionEvent e;

    public UpdateDataRequest(ActionEvent e) {
        this.e = e;
    }

    @Override
    public void execute(Dispatcher dispatcher) {
        Model model = dispatcher.getModel();
        GUI view = dispatcher.getView();
        if(this.e==null) {
            view.updateAnswers(model.getRound(0).getQuestions().get(0).getAnswers());
            view.updateCategory(model.getRound(0).getQuestions().get(0).getCategory());
            view.updateGamemode(model.getRound(0).getGamemodeString());
            view.updateQuestion(model.getRound(0).getQuestions().get(0).getQuestionText());
            view.updateScore(model.getPlayers());
            view.updateRoundId(String.valueOf(1));
            return;
        }

        Round currentRound = model.getRound(roundId);
        Question currentQuestion = currentRound.getQuestions().get(questionId);
        JButton buttonPressed = (JButton)e.getSource();
        if(buttonPressed.getText().equals(currentQuestion.getCorrectAnswer()))
            currentRound.actionIfCorrectAnswer(model);
        else
            currentRound.actionIfWrongAnswer(model);

        questionId++;
        if(questionId==questionsPerRound) {
            roundId++;
            questionId = 0;
        }

        if(roundId == model.getNumOfRounds()) {
            view.setVisible(true);
            new FinishFrame(view);
            return;
        }

        currentRound = model.getRound(roundId);
        currentQuestion = currentRound.getQuestions().get(questionId);
        view.updateAnswers(currentQuestion.getAnswers());
        view.updateCategory(currentQuestion.getCategory());
        view.updateGamemode(model.getRound(roundId).getGamemodeString());
        view.updateQuestion(currentQuestion.getQuestionText());
        view.updateScore(model.getPlayers());
        view.updateRoundId(String.valueOf(roundId + 1));

    }
}
