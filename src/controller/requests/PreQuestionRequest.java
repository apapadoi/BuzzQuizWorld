package controller.requests;

import controller.Dispatcher;
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

        if(!currentRound.hasPreQuestionFormat()) {
            OnePlayerFrame.getInstance().setVisible(true);
            dispatcher.getView().updateExtraJLabel("");
            return;
        }
        HighStakes currentGamemode = (HighStakes)currentRound.getGamemode();
        currentGamemode.checkZeroScoreAndUpdate(model);
        // TODO THE PRE QUESTION ACTIONS
        new OnePlayerBettingFrame();
    }
}
