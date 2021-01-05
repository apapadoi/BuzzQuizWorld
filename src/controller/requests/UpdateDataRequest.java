package controller.requests;

import controller.Dispatcher;
import model.Model;
import model.questions.Question;
import model.round.Round;
import view.gui.FinishFrame;
import view.gui.GUI;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;

public class UpdateDataRequest extends Request{
    private final int answerIndex;
    private static final HashMap<Integer,Boolean> playersAnswered;
    private static final HashMap<Integer, Integer> responseTimes;
    private static int maxPlayers = 2;

    static {
        playersAnswered = new HashMap<>(maxPlayers);
        for(int i=0;i<maxPlayers;i++)
            playersAnswered.put(i, false);
        responseTimes = new HashMap<>(maxPlayers);
        for(int i=0;i<maxPlayers;i++)
            responseTimes.put(i, 0);
    }

    public UpdateDataRequest(int playerIndex, int answerIndex, int msLeft) {
        super(playerIndex);
        this.answerIndex = answerIndex;
        responseTimes.put(playerIndex, msLeft);
    }

    public static void setMaxPlayers(int newMaxPlayers) {
        maxPlayers = newMaxPlayers;
        for(int i=maxPlayers;i<playersAnswered.size();i++)
            playersAnswered.put(i, true);
    }

    public static boolean allAnswered() {
        return playersAnswered.values().stream().distinct().count()<=1;
    }

    public static int getMsLeft(int playerIndex) {
        return responseTimes.get(playerIndex);
    }

    @Override
    public void execute(Dispatcher dispatcher) {
        Model model = dispatcher.getModel();
        GUI view = dispatcher.getView();
        if(answerIndex==-1 || playerIndex==-1) {
            view.updateAnswers(model.getRound(0).getQuestions().get(0).getAnswers());
            view.updateCategory(model.getRound(0).getQuestions().get(0).getCategory());
            view.updateGamemode(model.getRound(0).getGamemodeString());
            view.updateQuestion(model.getRound(0).getQuestions().get(0).getQuestionText());
            view.updateScore(model.getPlayers());
            view.updateRoundId(String.valueOf(1));
            return;
        }

        if(playersAnswered.get(playerIndex).equals(true))
            return;

        playersAnswered.put(playerIndex, true);
        Round currentRound = model.getRound(roundId);
        Question currentQuestion = currentRound.getQuestions().get(questionId);
        if(currentQuestion.getAnswers().get(answerIndex).equals(currentQuestion.getCorrectAnswer()))
            currentRound.actionIfCorrectAnswer(model, this.playerIndex);
        else
            currentRound.actionIfWrongAnswer(model, this.playerIndex);

        if(playersAnswered.values().stream().distinct().count()<=1) {
            if(playersAnswered.get(0).equals(true)) {
                for(int i=0;i<maxPlayers;i++)
                    playersAnswered.put(i, false);
                for(int i=0;i<maxPlayers;i++)
                    responseTimes.put(i, 0);
            } else { // TODO remove this
                return;
            }
        } else {
            return;
        }

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
