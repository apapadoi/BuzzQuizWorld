package controller.requests;

import controller.Dispatcher;
import model.player.Player;
import view.gui.GUI;

import java.io.IOException;
import java.util.List;

public class LoadScoresRequest extends Request{
    private GUI scoresFrame;

    public LoadScoresRequest(GUI scoresFrame) {
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
