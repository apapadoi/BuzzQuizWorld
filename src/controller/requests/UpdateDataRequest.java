package controller.requests;

import controller.Dispatcher;
import model.player.Player;
import model.questions.Question;
import model.round.Round;
import view.gui.FinishFrame;
import view.gui.UI;
import java.util.HashMap;
/**
 * Represents a request for updating the data of the {@code Model component}.
 * @author Tasos Papadopoulos
 * @version 14.1.2021
 */
public class UpdateDataRequest extends Request{
    private final int answerIndex;
    private UI view;

    /**
     * Creates a {@code UpdateDataRequest} using the provided player index, answer index and ms left the moment the
     * player answered a question.
     * @param playerIndex the player index that caused the request as {@code int}
     * @param answerIndex the answer index that the player chose as {@code int} <b>with 0 offset</b>
     * @param msLeft the ms left at the moment the player answered as {@code int}
     */
    public UpdateDataRequest(int playerIndex, int answerIndex, int msLeft) {
        super(playerIndex);
        this.answerIndex = answerIndex;
        model.putMillisLeft(playerIndex, msLeft);
    }

    /**
     * @see Request
     */
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
        // TODO REMOVE THIS if probably
        if(playersAnswered.get(0).equals(false)) // there are no players that have answered
            return;

        model.restartMaps();
        this.updateQuestionId();
        if(this.RoundsHaveFinished()) {
            for(Player player:model.getPlayers())
                if(player.getScore()<0)
                    player.setScore(0);
            view.setHasTimer(false);
            this.finishGame();
            return;
        }
        this.updateGameplayFrameData(roundId, questionId);
    }

    /**
     * Returns whether or not the current game session has finished.
     * @return whether or not the current game session has finished as {@code boolean}
     */
    private boolean RoundsHaveFinished() {
        return roundId == model.getNumOfRounds();
    }

    /**
     * Does the actions that need to be done when the current game session finishes.
     */
    private void finishGame() {
        view.setVisible(true);
        new FinishFrame(view);
    }

    /**
     * Updates the question id for moving on to the next question.
     */
    private void updateQuestionId() {
        questionId++;
        if(questionId==questionsPerRound) {
            roundId++;
            questionId = 0;
        }
    }

    /**
     * Updates the data in the {@code View} component that the dispatcher stores.
     * @param roundIndex the next round index as {@code int}
     * @param questionIndex the next question index as {@code int}
     */
    private void updateGameplayFrameData(int roundIndex,int questionIndex) {
        Round currentRound = model.getRound(roundIndex);
        Question currentQuestion = currentRound.getQuestions().get(questionIndex);
        view.updateAnswers(currentQuestion.getAnswers());
        view.updateCategory(currentQuestion.getCategory());
        view.updateGamemode(currentRound.getGamemodeString());
        view.updateQuestion(currentQuestion.getQuestionText());
        view.updatePlayerData(model.getPlayers());
        view.updateRoundId(String.valueOf(roundIndex+1));
        view.updateDifficulty(currentQuestion.getDifficulty());
        view.updatePlayerData(model.getPlayers());
        if(currentQuestion.hasContent())
            view.updateQuestionsImage(currentQuestion.getContent());
        else // TODO NULL OBJECT DESIGN PATTERN
            view.updateQuestionsImage(null);
    }
}
