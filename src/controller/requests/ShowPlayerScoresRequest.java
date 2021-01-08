package controller.requests;

import controller.Dispatcher;
import model.player.Player;
import view.gui.UI;

import java.util.ArrayList;
import java.util.List;

public class ShowPlayerScoresRequest extends Request{
    private final UI finishFrame;

    public ShowPlayerScoresRequest(UI finishFrame) {
        this.finishFrame = finishFrame;
    }

    @Override
    public void execute(Dispatcher dispatcher) {
        List<Player> players = new ArrayList<>(model.getPlayers());
        finishFrame.updateScores(players);
    }
}
