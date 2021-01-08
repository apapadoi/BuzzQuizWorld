package controller.requests;

import controller.Dispatcher;
import model.player.Player;
import view.gui.UI;
import java.io.IOException;
import java.util.List;

public class LoadScoresRequest extends Request{
    private final UI scoresFrame;

    public LoadScoresRequest(UI scoresFrame) {
        this.scoresFrame = scoresFrame;
    }

    @Override
    public void execute(Dispatcher dispatcher) {
        List<Player> players=null;
        try {
            players = dispatcher.getFileHandler().readPlayers();
        }catch(IOException|ClassNotFoundException e) {
            // TODO ADD ERROR FRAME
            System.exit(-5);
        }

        scoresFrame.updatePlayers(players);
    }
}
