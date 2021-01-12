package controller.requests;

import controller.FrontController;
import model.Model;
import model.fileHandler.FileHandler;
import model.gamemodes.factories.OnePlayerGamemodeFactory;
import model.gamemodes.factories.TwoPlayersGamemodeFactory;
import org.junit.jupiter.api.Test;
import view.gui.GUI;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddUsernamesRequestTest {

    @Test
    void execute() {
        FileHandler fileHandler = new FileHandler(new ArrayList<>(), Paths.get("test/resources/data/questions" +
                "/textQuestions/textQuestions.txt"),Paths.get("test/resources/data/questions/imagedQuestions" +
                "/imagedQuestions.txt"));
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
            public List<String> getUsernames() {
                return new ArrayList<String>(List.of("TestUsername1"));
            }
        });
        FrontController.getInstance().dispatchRequest(new AddUsernamesRequest());
        assertEquals("TestUsername1",Model.getInstance().getUsername(0));

        Model.getInstance().clearData();
         Model.getInstance().setGamemodeFactory(TwoPlayersGamemodeFactory.getInstance());
         Model.getInstance().setMaxPlayers(2);
         FrontController.getInstance().setFileHandler(fileHandler);
         FrontController.getInstance().setView(new GUI() {
             @Override
             public List<String> getUsernames() {
                 return new ArrayList<>(List.of("Test2","Test0"));
             }
         });
         FrontController.getInstance().dispatchRequest(new AddUsernamesRequest());
         assertEquals("Test2",Model.getInstance().getUsername(0));
         assertEquals("Test0",Model.getInstance().getUsername(1));
    }

    @Test
    void execute1() {
        FileHandler fileHandler = new FileHandler(new ArrayList<>(), Paths.get("test/resources/data/questions" +
                "/textQuestions/textQuestions.txt"),Paths.get("test/resources/data/questions/imagedQuestions" +
                "/imagedQuestions.txt"));
        try {fileHandler.readQuestions();}
        catch(IOException e) {
            System.out.println("IO EXCEPTION "+e.getMessage());
        }
        Model.getInstance().clearData();
        Model.getInstance().setGamemodeFactory(TwoPlayersGamemodeFactory.getInstance());
        Model.getInstance().setMaxPlayers(3);
        FrontController.getInstance().setFileHandler(fileHandler);
        FrontController.getInstance().setView(new GUI() {
            @Override
            public List<String> getUsernames() {
                return new ArrayList<>(List.of("Test3","Test10","Test5"));
            }
        });
        FrontController.getInstance().dispatchRequest(new AddUsernamesRequest());
        assertEquals("Test3",Model.getInstance().getUsername(0));
        assertEquals("Test5",Model.getInstance().getUsername(2));
        assertEquals("Test10",Model.getInstance().getUsername(1));
    }
}