package controller.requests;

import controller.Dispatcher;
import view.gui.UI;

public class NextQuestionRequest extends Request{
    private final UI gamemodeFrame;

    public NextQuestionRequest(UI gamemodeFrame) {
        this.gamemodeFrame = gamemodeFrame;
    }

    @Override
    public void execute(Dispatcher dispatcher) {
        if(model.getPlayersAnswered().values().stream().distinct().count()>1)
            return;

        if(roundId==model.getNumOfRounds())
            return;

        gamemodeFrame.setVisible(false);
        dispatcher.dispatch(new PreQuestionRequest(gamemodeFrame));
    }
}
