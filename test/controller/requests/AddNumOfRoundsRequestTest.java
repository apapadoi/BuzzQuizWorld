package controller.requests;

import controller.FrontController;
import model.Model;
import model.fileHandler.FileHandler;
import model.gamemodes.factories.OnePlayerGamemodeFactory;
import org.junit.jupiter.api.Test;
import view.gui.utilResources.Constants;
import view.gui.SelectionFrameUI;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddNumOfRoundsRequestTest {

    @Test
    void execute() {
        FileHandler fileHandler = new FileHandler(new ArrayList<>(), Paths.get(Constants.QUESTIONS_FILE_URL),
                Paths.get(Constants.IMAGED_QUESTIONS_FILE_URL));
        try {fileHandler.readQuestions();}
        catch(IOException e) {
            System.out.println("IO EXCEPTION "+e.getMessage());
        }
        Model.getInstance().clearData();
        Model.getInstance().setGamemodeFactory(OnePlayerGamemodeFactory.getInstance());
        Model.getInstance().setMaxPlayers(1);
        SelectionFrameUI selectionFrame = new SelectionFrameUI() {
            @Override
            public int getNumOfRoundsChoice() {
                return 5;
            }

            @Override
            public List<String> getUsernames() {
                return new ArrayList<>(List.of("testUsername"));
            }
        };
        FrontController.getInstance().setFileHandler(fileHandler);
        FrontController.getInstance().dispatchRequest(new AddNumOfRoundsRequest(selectionFrame));
        assertEquals(5, Model.getInstance().getNumOfRounds());

        selectionFrame = new SelectionFrameUI() {
            @Override
            public int getNumOfRoundsChoice() {
                return 3;
            }

            @Override
            public List<String> getUsernames() {
                return new ArrayList<>(List.of("testUsername"));
            }
        };
        try {fileHandler.readQuestions();}
        catch(IOException e) {
            System.out.println("IO EXCEPTION "+e.getMessage());
        }
        FrontController.getInstance().dispatchRequest(new AddNumOfRoundsRequest(selectionFrame));
        assertEquals(3, Model.getInstance().getNumOfRounds());

        selectionFrame = new SelectionFrameUI() {
            @Override
            public int getNumOfRoundsChoice() {
                return 10;
            }

            @Override
            public List<String> getUsernames() {
                return new ArrayList<>(List.of("testUsername"));
            }
        };

        try {fileHandler.readQuestions();}
        catch(IOException e) {
            System.out.println("IO EXCEPTION "+e.getMessage());
        }
        FrontController.getInstance().dispatchRequest(new AddNumOfRoundsRequest(selectionFrame));
        assertEquals(10, Model.getInstance().getNumOfRounds());
    }
}