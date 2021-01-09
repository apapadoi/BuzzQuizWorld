package controller.requests;

import controller.Dispatcher;
import model.fileHandler.FileHandler;
import model.player.Player;
import view.gui.UI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaveScoresRequest extends Request{
    private final UI gamemodeFrame;

    public SaveScoresRequest(UI gamemodeFrame) {
        this.gamemodeFrame = gamemodeFrame;
    }

    @Override
    public void execute(Dispatcher dispatcher) {
        List<Integer> scores = new ArrayList<>();
        model.getPlayers().forEach(e-> scores.add(e.getScore()));
        if(scores.stream().distinct().count()<=1 && model.getPlayers().size()>1) // draw and more than one player
            return;

        if(gamemodeFrame.hasMoreThanTwoPlayers()) {
            List<Player> players = model.getPlayers();
            Player maxPlayer = players.get(0);
            for(int i=1;i< players.size();i++) {
                if(players.get(i).getScore() > maxPlayer.getScore())
                    maxPlayer = players.get(i);
            }

            for(Player player:players) {
                if (!player.equals(maxPlayer)) {
                    player.setScore(0);
                    player.setWins(0);
                } else {
                    player.setWins(player.getWins()+1);
                    player.setScore(0);
                }
            }
        } else {
            model.getPlayers().get(0).setWins(0);
        }

        FileHandler fileHandler = dispatcher.getFileHandler();
        try {
            fileHandler.savePlayers();
        } catch(IOException e) {
            // TODO ADD ERROR FRAME AND LOGGING
            System.exit(-3);
        }
    }
}
