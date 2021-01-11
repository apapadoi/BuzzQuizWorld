package controller.requests;

import controller.Dispatcher;
import model.questions.Question;
import model.round.Round;
import view.gui.FinishFrame;
import view.gui.UI;
import java.util.HashMap;

public class UpdateDataRequest extends Request{
    private final int answerIndex;
    private UI view;

    public UpdateDataRequest(int playerIndex, int answerIndex, int msLeft) {
        super(playerIndex);
        this.answerIndex = answerIndex;
        model.putResponseTime(playerIndex, msLeft);
    }

    @Override
    public void execute(Dispatcher dispatcher) {
        view = dispatcher.getView();

        if(answerIndex==-1 || playerIndex==-1) { // initial update data request
            this.updateGameplayFrameData(0, 0);
            return;
        }

        HashMap<Integer, Boolean> playersAnswered = model.getPlayersAnswered();
        if(playersAnswered.get(playerIndex).equals(true)) // if player try to answers more than one time
            return;

        // its the first time this specific player answers
        playersAnswered.put(playerIndex, true);
        Round currentRound = model.getRound(roundId);
        Question currentQuestion = currentRound.getQuestions().get(questionId);

        if(currentQuestion.getAnswers().get(answerIndex).equals(currentQuestion.getCorrectAnswer()))
            currentRound.actionIfCorrectAnswer(this.playerIndex);
        else
            currentRound.actionIfWrongAnswer(this.playerIndex);

        if(playersAnswered.values().stream().distinct().count()>1) // there is at least one player that has different
            return;                                                 // answer value (true/false)

        if(playersAnswered.get(0).equals(false)) // there are no players that have answered
            return;
        // TODO maybe add these restart methods to model
        this.restartPlayersAnsweredMap(playersAnswered,  model.getMaxPlayers());
        this.restartResponseTimesMap(model.getResponseTimes(),  model.getMaxPlayers());
        this.updateQuestionId();
        if(this.RoundsHaveFinished()) {
            this.finishGame();
            return;
        }
        this.updateGameplayFrameData(roundId, questionId);
    }

    private void restartResponseTimesMap(HashMap<Integer, Integer> map, int maxPlayers) {
        for(int i=0;i<maxPlayers;i++)
            map.put(i, 0);
    }

    private boolean RoundsHaveFinished() {
        return roundId == model.getNumOfRounds();
    }

    private void finishGame() {
        view.setVisible(true);
        new FinishFrame(view);
    }

    private void restartPlayersAnsweredMap(HashMap<Integer, Boolean> map, int maxPlayers) {
        for(int i=0;i<maxPlayers;i++)
            map.put(i, false);
    }

    private void updateQuestionId() {
        questionId++;
        if(questionId==questionsPerRound) {
            roundId++;
            questionId = 0;
        }
    }

    private void updateGameplayFrameData(int roundIndex,int questionIndex) {
        Round currentRound = model.getRound(roundIndex);
        Question currentQuestion = currentRound.getQuestions().get(questionIndex);
        view.updateAnswers(currentQuestion.getAnswers());
        view.updateCategory(currentQuestion.getCategory());
        view.updateGamemode(currentRound.getGamemodeString());
        view.updateQuestion(currentQuestion.getQuestionText());
        view.updateScores(model.getPlayers());
        view.updateRoundId(String.valueOf(roundIndex+1));
        view.updateDifficulty(currentQuestion.getDifficulty());
        view.updateUsernames(model.getPlayers());
        if(currentQuestion.hasContent())
            view.updateQuestionsImage(currentQuestion.getContent());
        else // TODO NULL OBJECT DESIGN PATTERN
            view.updateQuestionsImage(null);
    }
}
