package controller.requests;

import controller.Dispatcher;
import model.Model;
import model.questions.Question;
import model.round.Round;
import view.gui.FinishFrame;
import view.gui.UI;
import java.util.HashMap;

public class UpdateDataRequest extends Request{
    private final int answerIndex;

    public UpdateDataRequest(int playerIndex, int answerIndex, int msLeft) {
        super(playerIndex);
        this.answerIndex = answerIndex;
        Model.getInstance().putResponseTime(playerIndex, msLeft);
    }

    @Override
    public void execute(Dispatcher dispatcher) {
        Model model = dispatcher.getModel();
        UI view = dispatcher.getView();
        if(answerIndex==-1 || playerIndex==-1) {
            view.updateAnswers(model.getRound(0).getQuestions().get(0).getAnswers());
            view.updateCategory(model.getRound(0).getQuestions().get(0).getCategory());
            view.updateGamemode(model.getRound(0).getGamemodeString());
            view.updateQuestion(model.getRound(0).getQuestions().get(0).getQuestionText());
            view.updateScores(model.getPlayers());
            view.updateRoundId(String.valueOf(1));
            view.updateDifficulty(model.getRound(0).getQuestions().get(0).getDifficulty());
            view.updateUsernames(model.getPlayers());
            return;
        }

        HashMap<Integer, Boolean> playersAnswered = Model.getInstance().getPlayersAnswered();
        int maxPlayers = Model.getInstance().getMaxPlayers();
        HashMap<Integer, Integer> responseTimes = Model.getInstance().getResponseTimes();

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
            } else {
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
        view.updateScores(model.getPlayers());
        view.updateRoundId(String.valueOf(roundId + 1));
        view.updateDifficulty(currentQuestion.getDifficulty());
        view.updateUsernames(model.getPlayers());
    }
}
