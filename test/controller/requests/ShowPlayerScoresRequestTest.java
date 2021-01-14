package controller.requests;

import controller.FrontController;
import model.Model;
import model.player.Player;
import org.junit.jupiter.api.Test;
import view.gui.GUI;
import view.gui.UI;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShowPlayerScoresRequestTest {
    private List<Player> showList;

    @Test
    void execute() {
        Model.getInstance().clearData();
        Model.getInstance().setUsernames(new ArrayList<>(List.of("test_name_1", "test_name_3")));
        List<Player> modelPlayersList = Model.getInstance().getPlayers();
        UI finishFrame = new GUI() {
            @Override
            public void updatePlayerData(List<Player> players) {
                showList = new ArrayList<>(players);
            }
        };
        FrontController.getInstance().dispatchRequest(new ShowPlayerScoresRequest(finishFrame));

        int samePlayersFoundCount = 0;
        for(Player showPlayer: showList) {
            for(Player modelPlayer:modelPlayersList)
                if(modelPlayer.equals(showPlayer)) {
                    samePlayersFoundCount++;
                    break;
                }
        }
        assertEquals(2, samePlayersFoundCount);
    }
}