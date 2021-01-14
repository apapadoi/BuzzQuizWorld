package model;

import model.fileHandler.FileHandler;
import model.gamemodes.factories.OnePlayerGamemodeFactory;
import model.gamemodes.factories.TwoPlayersGamemodeFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests Model class.
 * @author Tasos Papadopoulos
 * @version 14.1.2021
 */
class ModelTest {

    /**
     * Test for gamemode factory setter.
     */
    @Test
    void setGamemodeFactory() {
        Model.getInstance().setGamemodeFactory(OnePlayerGamemodeFactory.getInstance());
        assertEquals(OnePlayerGamemodeFactory.getInstance(), Model.getInstance().getGamemodeFactory());
        Model.getInstance().setGamemodeFactory(TwoPlayersGamemodeFactory.getInstance());
        assertEquals(TwoPlayersGamemodeFactory.getInstance(), Model.getInstance().getGamemodeFactory());
    }

    /**
     * Test for gamemode factory getter.
     */
    @Test
    void getGamemodeFactory() {
        Model.getInstance().setGamemodeFactory(TwoPlayersGamemodeFactory.getInstance());
        assertEquals(TwoPlayersGamemodeFactory.getInstance(), Model.getInstance().getGamemodeFactory());
        Model.getInstance().setGamemodeFactory(OnePlayerGamemodeFactory.getInstance());
        assertEquals(OnePlayerGamemodeFactory.getInstance(), Model.getInstance().getGamemodeFactory());
    }

    /**
     * Test for players getter.
     */
    @Test
    void getPlayers() {
        Model.getInstance().clearData();
        Model.getInstance().setUsernames(new ArrayList<>(List.of("testUsername1","testUsername2")));
        assertEquals("testUsername1", Model.getInstance().getPlayers().get(0).getUsername());
        assertEquals("testUsername2", Model.getInstance().getPlayers().get(1).getUsername());
    }

    /**
     * Test for usernames setter.
     */
    @Test
    void setUsername() {
        Model.getInstance().setUsernames(new ArrayList<>(List.of("testUsername1","testUsername2")));
        Model.getInstance().setUsername("new test username",0);
        assertEquals("new test username", Model.getInstance().getPlayers().get(0).getUsername());
        Model.getInstance().setUsername("new mock test username",1);
        assertEquals("new mock test username", Model.getInstance().getPlayers().get(1).getUsername());
    }

    /**
     * Test for number of rounds setter.
     */
    @Test
    void setNumOfRoundsChoice() {
        FileHandler fileHandler = new FileHandler(new ArrayList<>(), Paths.get(
                "test/resources/data/questions/textQuestions/textQuestions.txt"),
                Paths.get("test/resources/data/questions/imagedQuestions/imagedQuestions.txt"));
        try {fileHandler.readQuestions();}
        catch(IOException e) {
            System.out.println("IO EXCEPTION "+e.getMessage());
        }
        Model.getInstance().setNumOfRoundsChoice(1, fileHandler);
        assertEquals(1, Model.getInstance().getNumOfRounds());
    }

    /**
     * Test for username getter.
     */
    @Test
    void getUsername() {
        Model.getInstance().setUsernames(new ArrayList<>(List.of("testUsername1","testUsername2")));
        Model.getInstance().setUsername("new test username",0);
        assertEquals("new test username", Model.getInstance().getUsername(0));
        Model.getInstance().setUsername("new mock test username",1);
        assertEquals("new mock test username", Model.getInstance().getUsername(1));
    }

    /**
     * Test for score getter.
     */
    @Test
    void getScore() {
        Model.getInstance().setUsernames(new ArrayList<>(List.of("testUsername1","testUsername2")));
        Model.getInstance().addScore(1500,0);
        assertEquals(1500, Model.getInstance().getScore(0));
        Model.getInstance().addScore(-1500,1);
        assertEquals(-1500, Model.getInstance().getScore(1));
    }

    /**
     * Test for score adder.
     */
    @Test
    void addScore() {
        Model.getInstance().clearData();
        Model.getInstance().setUsernames(new ArrayList<>(List.of("testUsername1","testUsername2")));
        Model.getInstance().addScore(1500,0);
        assertEquals(1500, Model.getInstance().getScore(0));
        Model.getInstance().addScore(2500, 1);
        assertEquals(2500, Model.getInstance().getScore(1));
        Model.getInstance().addScore(-2500,0);
        assertEquals(-1000, Model.getInstance().getScore(0));
    }

    /**
     * Test for set usernames.
     */
    @Test
    void setUsernames() {
        Model.getInstance().clearData();
        Model.getInstance().getPlayers().clear();
        Model.getInstance().setUsernames(new ArrayList<>(List.of("testUsername1","testUsername2")));
        assertEquals("testUsername1",Model.getInstance().getUsername(0));
        assertEquals("testUsername2",Model.getInstance().getUsername(1));
    }

    /**
     * Test for set max players.
     */
    @Test
    void setMaxPlayers() {
        Model.getInstance().clearData();
        Model.getInstance().setMaxPlayers(1);
        assertEquals(1, Model.getInstance().getMaxPlayers());
        Model.getInstance().setMaxPlayers(3);
        assertEquals(3, Model.getInstance().getMaxPlayers());
    }

    /**
     * Test for putMillisLeft
     */
    @Test
    void putMillisLeft() {
        Model.getInstance().clearData();
        Model.getInstance().putMillisLeft(0,1500);
        Model.getInstance().putMillisLeft(1,3000);
        assertEquals(1500, Model.getInstance().getMillisLeft(0));
        assertEquals(3000, Model.getInstance().getMillisLeft(1));
    }

    /**
     * Test for millis left getter.
     */
    @Test
    void getMillisLeft() {
        Model.getInstance().clearData();
        Model.getInstance().putMillisLeft(0,1500);
        Model.getInstance().putMillisLeft(1,3500);
        assertEquals(1500, Model.getInstance().getMillisLeft(0));
        assertEquals(3500, Model.getInstance().getMillisLeft(1));
    }

    @Test
    void getPlayersAnswered1() {
        Model.getInstance().clearData();
        Model.getInstance().getPlayersAnswered().put(0,true);
        assertTrue(Model.getInstance().getPlayersAnswered().get(0));
        Model.getInstance().getPlayersAnswered().put(0,false);
        assertFalse(Model.getInstance().getPlayersAnswered().get(0));
    }

    @Test
    void getPlayersAnswered2() {
        Model.getInstance().clearData();
        Model.getInstance().getPlayersAnswered().put(1,true);
        assertTrue(Model.getInstance().getPlayersAnswered().get(1));
        Model.getInstance().getPlayersAnswered().put(1,false);
        assertFalse(Model.getInstance().getPlayersAnswered().get(1));
    }

    @Test
    void getMaxPlayers() {
        Model.getInstance().clearData();
        Model.getInstance().setMaxPlayers(3);
        assertEquals(3, Model.getInstance().getMaxPlayers());
        Model.getInstance().setMaxPlayers(5);
        assertEquals(5, Model.getInstance().getMaxPlayers());
    }
}