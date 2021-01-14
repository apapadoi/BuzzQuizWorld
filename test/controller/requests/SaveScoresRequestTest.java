package controller.requests;

import controller.FrontController;
import model.Model;
import model.fileHandler.FileHandler;
import model.player.Player;
import org.junit.jupiter.api.Test;
import view.gui.GUI;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SaveScoresRequestTest {

    @Test
    void execute() {
        Model.getInstance().getPlayers().forEach(System.out::println);
        Model.getInstance().clearData();
        Model.getInstance().getPlayers().forEach(System.out::println);
        Model.getInstance().setUsernames(new ArrayList<>(List.of("test_username")));
        Model.getInstance().getPlayers().get(0).setScore(1000);
        Model.getInstance().getPlayers().get(0).setWins(15);
        FileHandler fileHandler = new FileHandler(new ArrayList<>(),
                Paths.get("test/resources/data/questions/textQuestions/textQuestions.txt"),
                Paths.get("test/resources/data/questions/imagedQuestions/imagedQuestions.txt"));
        fileHandler.setDb_file("test/resources/db/scores.bin");
        FrontController.getInstance().setFileHandler(fileHandler);
        FrontController.getInstance().dispatchRequest(new SaveScoresRequest(new GUI() {
            @Override
            public boolean hasMoreThanTwoPlayers() {
                return false;
            }
        }));

        List<Player> loadedPlayers;
        try {
             loadedPlayers = fileHandler.readPlayers();
        }catch(IOException|ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
        for(Player player:loadedPlayers)
            if(player.getUsername().equals("test_username")) {
                assertEquals(1000, player.getScore());
                assertEquals(0, player.getWins());
            }
    }

    @Test
    void execute1() {
        Model.getInstance().clearData();
        Model.getInstance().setUsernames(new ArrayList<>(List.of("test_username_3","test_username_4")));
        Model.getInstance().getPlayers().get(0).setScore(1000);
        Model.getInstance().getPlayers().get(0).setWins(15);
        Model.getInstance().getPlayers().get(1).setScore(20000);
        Model.getInstance().getPlayers().get(1).setWins(0);
        FileHandler fileHandler = new FileHandler(new ArrayList<>(),
                Paths.get("test/resources/data/questions/textQuestions/textQuestions.txt"),
                Paths.get("test/resources/data/questions/imagedQuestions/imagedQuestions.txt"));
        fileHandler.setDb_file("test/resources/db/scores.bin");
        FrontController.getInstance().setFileHandler(fileHandler);
        int player0Score = 0;
        int player1Score = 0;
        int player0Wins = 0;
        int player1Wins = 0;
        List<Player> loadedPlayers;
        try {
            loadedPlayers = fileHandler.readPlayers();
        }catch(IOException|ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
        for(Player player:loadedPlayers)
            if(player.getUsername().equals("test_username_3")) {
                player0Score = player.getScore();
                player0Wins = player.getWins();
            }
            else if(player.getUsername().equals("test_username_4")){
                player1Score = player.getScore();
                player1Wins = player.getWins();
            }

        FrontController.getInstance().dispatchRequest(new SaveScoresRequest(new GUI() {
            @Override
            public boolean hasMoreThanTwoPlayers() {
                return true;
            }
        }));

        try {
            loadedPlayers = fileHandler.readPlayers();
        }catch(IOException|ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
        for(Player player:loadedPlayers)
            if(player.getUsername().equals("test_username_3")) {
                assertEquals(player0Score, player.getScore());
                assertEquals(player0Wins, player.getWins());
            }
            else if(player.getUsername().equals("test_username_4")){
                assertEquals(player1Score, player.getScore());
                assertEquals(player1Wins+1, player.getWins());
            }
    }
}