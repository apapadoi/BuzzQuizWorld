package controller.requests;

import controller.Dispatcher;
import model.player.Player;
import view.gui.ErrorFrame;
import view.gui.UI;
import java.io.IOException;
import java.util.List;

/**
 * Represents a request for loading the high scores of the players who have played the game.
 * @author Tasos Papadopoulos
 * @version 14.1.2021
 */
public class LoadScoresRequest extends Request{
    private final UI scoresFrame;

    /**
     * Creates a {@code LoadScoresRequest} which will use the {@code UI scoresFrame} provided for invoking
     * {@code updatePlayers(List<Player>} method for showing the {@code Player} objects loaded.
     * @param scoresFrame the {@code UI} object whose {@code updatePlayers(List<Player>)} method will be invoked
     */
    public LoadScoresRequest(UI scoresFrame) {
        this.scoresFrame = scoresFrame;
    }

    /**
     * @see Request
     */
    @Override
    public void execute(Dispatcher dispatcher) {
        List<Player> players=null;
        try {
            players = dispatcher.getFileHandler().readPlayers();
        }catch(IOException|ClassNotFoundException e) {
            new ErrorFrame();
        }

        scoresFrame.updatePlayerData(players);
    }
}
