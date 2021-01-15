package controller.requests;

import controller.FrontController;
import model.Model;
import model.fileHandler.FileHandler;
import model.gamemodes.factories.OnePlayerGamemodeFactory;
import org.junit.jupiter.api.Test;
import view.gui.GUI;
import view.gui.SelectionFrameUI;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClearDataRequestTest {

    @Test
    void execute() {
        FileHandler fileHandler = new FileHandler(new ArrayList<>(),
                Paths.get("test/resources/data/questions/textQuestions/textQuestions.txt"),
                Paths.get("test/resources/data/questions/imagedQuestions/imagedQuestions.txt"));
        FrontController.getInstance().setFileHandler(fileHandler);
        FrontController.getInstance().dispatchRequest(new SetGamemodeFactoryRequest(
                OnePlayerGamemodeFactory.getInstance()
        ));
        FrontController.getInstance().dispatchRequest(new LoadRequest());
        FrontController.getInstance().dispatchRequest(new ClearDataRequest());

        SelectionFrameUI selectionFrame = new SelectionFrameUI() {
            @Override
            public int getNumOfRoundsChoice() {
                return 1;
            }

            @Override
            public List<String> getUsernames() {
                return new ArrayList<>(List.of("testUsername"));
            }
        };
        FrontController.getInstance().dispatchRequest(new AddUsernamesRequest(selectionFrame));
        FrontController.getInstance().dispatchRequest(new AddNumOfRoundsRequest(selectionFrame));
        Model.getInstance().getPlayersAnswered().put(0,true);
        FrontController.getInstance().setView(new GUI() {
            @Override
            public void setVisible(boolean b) {
                super.setVisible(b);
            }
        });
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(-1,-1,-1));
        FrontController.getInstance().dispatchRequest(new ClearDataRequest());
        FrontController.getInstance().dispatchRequest(new SetMaximumPlayersRequest(1));
        assertEquals(0,Request.roundId);
        assertEquals(0, Request.questionId);
        assertEquals(0, Model.getInstance().getPlayers().size());
        assertEquals(0, Model.getInstance().getNumOfRounds());
    }

    @Test
    void execute1() {
        FileHandler fileHandler = new FileHandler(new ArrayList<>(),
                Paths.get("test/resources/data/questions/textQuestions/textQuestions.txt"),
                Paths.get("test/resources/data/questions/imagedQuestions/imagedQuestions.txt"));
        FrontController.getInstance().setFileHandler(fileHandler);
        FrontController.getInstance().dispatchRequest(new SetGamemodeFactoryRequest(
                OnePlayerGamemodeFactory.getInstance()
        ));
        FrontController.getInstance().dispatchRequest(new LoadRequest());
        FrontController.getInstance().dispatchRequest(new ClearDataRequest());

        SelectionFrameUI selectionFrame = new SelectionFrameUI() {
            @Override
            public int getNumOfRoundsChoice() {
                return 1;
            }

            @Override
            public List<String> getUsernames() {
                return new ArrayList<>(List.of("testUsername"));
            }
        };
        FrontController.getInstance().dispatchRequest(new AddUsernamesRequest(selectionFrame));
        FrontController.getInstance().dispatchRequest(new AddNumOfRoundsRequest(selectionFrame));
        Model.getInstance().getPlayersAnswered().put(0,true);
        FrontController.getInstance().setView(new GUI() {
            @Override
            public void setVisible(boolean b) {
                super.setVisible(b);
            }
        });
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(-1,-1,-1));
        FrontController.getInstance().dispatchRequest(new ClearDataRequest());
        FrontController.getInstance().dispatchRequest(new SetMaximumPlayersRequest(1));
        assertEquals(1,
                Model.getInstance().getPlayersAnswered().values().stream().distinct().count());
        assertFalse(Model.getInstance().getPlayersAnswered().get(0));
        assertEquals(1,
                Model.getInstance().getMillisLeft().values().stream().distinct().count());
        assertEquals(0, Model.getInstance().getMillisLeft().get(0));
    }
}