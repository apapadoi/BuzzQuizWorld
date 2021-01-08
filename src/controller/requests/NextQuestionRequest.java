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
        // TODO probably remove all answered method from model
        if(model.allAnswered() && roundId!=model.getNumOfRounds()) {
            gamemodeFrame.setVisible(false);
            dispatcher.dispatch(new PreQuestionRequest(gamemodeFrame));
        }
    }
}
