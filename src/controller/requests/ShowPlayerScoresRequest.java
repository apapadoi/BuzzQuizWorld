package controller.requests;

import controller.Dispatcher;
import view.gui.UI;
import java.util.ArrayList;

/**
 * Represents a request for showing the scores of the current game session players.
 * @author Tasos Papadopoulos
 * @version 14.1.2021
 */
public class ShowPlayerScoresRequest extends Request{
    private final UI finishFrame;

    /**
     * Creates a {@code ShowPlayerScoresRequest} which will use the {@code UI finishFrame} provided for invoking
     * {@code updatePlayerData(List<Player>} method for showing the current game session players' scores.
     * @param finishFrame the {@code UI} object whose {@code updatePlayerData(List<Player>)} method will be invoked
     */
    public ShowPlayerScoresRequest(UI finishFrame) {
        this.finishFrame = finishFrame;
    }

    /**
     * @see Request
     */
    @Override
    public void execute(Dispatcher dispatcher) {
        finishFrame.updatePlayerData(new ArrayList<>(model.getPlayers()));
    }
}
