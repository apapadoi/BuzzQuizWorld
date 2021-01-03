package controller.requests;

import controller.Dispatcher;
import controller.FrontController;
import model.Model;
import model.gamemodes.HighStakes;
import model.round.Round;
import view.gui.OnePlayerBettingFrame;
import view.gui.OnePlayerFrame;
import java.awt.event.ActionEvent;

public class PreQuestionRequest extends Request{
    private final ActionEvent e;

    public PreQuestionRequest(ActionEvent e) {
        this.e = e;
    }

    @Override
    public void execute(Dispatcher dispatcher) {
        Model model = dispatcher.getModel();

        if(roundId == model.getNumOfRounds())
            return;

        Round currentRound = model.getRound(Request.roundId);
        if(currentRound.getGamemode().hasTimer())
            OnePlayerFrame.getInstance().setHasTimer(true);
        else
            OnePlayerFrame.getInstance().setHasTimer(false);
        if(!currentRound.hasPreQuestionFormat()) {
            // TODO ADD IF HAS TIMER METHOD FOR GAMEMODES
            OnePlayerFrame.getInstance().restartCount();
            // TODO probably not needed
            dispatcher.getView().updateExtraJLabel(String.valueOf(OnePlayerFrame.getInstance().getCount()/1000.0) + "seconds" );
            OnePlayerFrame.getInstance().startTimer();
            OnePlayerFrame.getInstance().setVisible(true);
            return;
        }
        HighStakes currentGamemode = (HighStakes)currentRound.getGamemode();
        currentGamemode.checkZeroScoreAndUpdate(model);
        OnePlayerBettingFrame betFrame = new OnePlayerBettingFrame();
        FrontController.getInstance().setView(betFrame);
        dispatcher.getView().updateCategory(currentRound.getQuestions().get(questionId).getCategory());
        FrontController.getInstance().setView(OnePlayerFrame.getInstance());
        betFrame.setVisible(true);
        // TODO THE PRE QUESTION ACTIONS

    }
}
