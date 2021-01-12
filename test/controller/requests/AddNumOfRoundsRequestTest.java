package controller.requests;

import controller.FrontController;
import model.Model;
import model.fileHandler.FileHandler;
import model.gamemodes.factories.OnePlayerGamemodeFactory;
import org.junit.jupiter.api.Test;
import resources.utilResources.Constants;
import view.gui.GUI;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
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
        FrontController.getInstance().setFileHandler(fileHandler);

        FrontController.getInstance().setView(new GUI() {
            @Override
            public int getNumOfRoundsChoice() {
                return 5;
            }
        });
        FrontController.getInstance().dispatchRequest(new AddNumOfRoundsRequest());
        assertEquals(5, Model.getInstance().getNumOfRounds());

        FrontController.getInstance().setView(new GUI() {
            @Override
            public int getNumOfRoundsChoice() {
                return 3;
            }
        });
        try {fileHandler.readQuestions();}
        catch(IOException e) {
            System.out.println("IO EXCEPTION "+e.getMessage());
        }
        FrontController.getInstance().dispatchRequest(new AddNumOfRoundsRequest());
        assertEquals(3, Model.getInstance().getNumOfRounds());

        FrontController.getInstance().setView(new GUI() {
            @Override
            public int getNumOfRoundsChoice() {
                return 10;
            }
        });

        try {fileHandler.readQuestions();}
        catch(IOException e) {
            System.out.println("IO EXCEPTION "+e.getMessage());
        }
        FrontController.getInstance().dispatchRequest(new AddNumOfRoundsRequest());
        assertEquals(10, Model.getInstance().getNumOfRounds());
    }
}