package controller.requests;

import controller.Dispatcher;
import view.gui.UI;
import java.util.ArrayList;

public class ShowPlayerScoresRequest extends Request{
    private final UI finishFrame;

    public ShowPlayerScoresRequest(UI finishFrame) {
        this.finishFrame = finishFrame;
    }

    @Override
    public void execute(Dispatcher dispatcher) {
        finishFrame.updateScores(new ArrayList<>(model.getPlayers()));
    }
}
