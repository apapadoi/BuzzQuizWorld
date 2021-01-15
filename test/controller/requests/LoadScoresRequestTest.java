package controller.requests;

import controller.FrontController;
import model.Model;
import model.fileHandler.FileHandler;
import model.gamemodes.factories.OnePlayerGamemodeFactory;
import model.player.Player;
import org.junit.jupiter.api.Test;
import view.gui.GUI;
import view.gui.UI;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class LoadScoresRequestTest {
    private List<Player> loadedPlayers;

    @Test
    void execute() {
        List<Player> previousPlayers = new ArrayList<>(List.of(
                new Player("testUsername1",5000,15),
                new Player("testUsername2",2500,25),
                new Player("test4",0,0)
        ));

        String db_file = "test/resources/db/scores.bin";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(db_file))) {
            out.writeObject(previousPlayers);
        }catch(IOException e) {
            System.out.println(e.getMessage());
        }
        FileHandler fileHandler = new FileHandler(new ArrayList<>(), Paths.get("test/resources/data/questions" +
                "/textQuestions/textQuestions.txt"), Paths.get("test/resources/data/questions/imagedQuestions" +
                "/imagedQuestions.txt"));
        Model.getInstance().clearData();
        Model.getInstance().setGamemodeFactory(OnePlayerGamemodeFactory.getInstance());
        Model.getInstance().setMaxPlayers(1);
        FrontController.getInstance().setFileHandler(fileHandler);
        fileHandler.setDb_file(db_file);
        UI view = new GUI() {
            @Override
            public void updatePlayerData(List<Player> players) {
                loadedPlayers = new ArrayList<>(players);
            }
        };
        FrontController.getInstance().dispatchRequest(new LoadScoresRequest(view));
        assertEquals(previousPlayers.get(0),loadedPlayers.get(0));
        assertEquals(previousPlayers.get(1),loadedPlayers.get(1));
        assertEquals(previousPlayers.get(2),loadedPlayers.get(2));
    }
}