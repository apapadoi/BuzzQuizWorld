package model.gamemodes;

import controller.FrontController;
import controller.requests.*;
import javafx.embed.swing.JFXPanel;
import model.Model;
import model.fileHandler.FileHandler;
import model.gamemodes.factories.GamemodeFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.gui.GUI;
import view.gui.GameplayFrame;
import view.gui.SelectionFrameUI;
import view.gui.UI;

import java.awt.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StopTheClockTest {
    private StopTheClock stopTheClock;

    @BeforeEach
    void setUp() {
        new JFXPanel();
        FrontController.getInstance().setView(new GameplayFrame() {
            @Override
            public Dimension getSize() {
                return new Dimension(150,150);
            }

            @Override
            public UI getPreQuestionFrame() {
                return new GUI() {
                    @Override
                    public Dimension getSize() {
                        return new Dimension(150,150);
                    }

                    @Override
                    public void dispose() {
                        super.dispose();
                    }
                };
            }
        });
        stopTheClock = new StopTheClock();
        FileHandler fileHandler = new FileHandler(new ArrayList<>(),
                Paths.get("test/resources/data/questions/textQuestions/textQuestions.txt"),
                Paths.get("test/resources/data/questions/imagedQuestions/imagedQuestions.txt"));
        FrontController.getInstance().setFileHandler(fileHandler);
        FrontController.getInstance().dispatchRequest(new SetGamemodeFactoryRequest(
                new GamemodeFactory() {
                    @Override
                    public Gamemodable getRandomGamemode() {
                        return stopTheClock;
                    }

                    @Override
                    public void clearGamemodeData() {

                    }
                }
        ));
        FrontController.getInstance().dispatchRequest(new LoadRequest());
        FrontController.getInstance().dispatchRequest(new ClearDataRequest());
        FrontController.getInstance().setView(new GUI() {
            @Override
            public List<String> getUsernames() {
                return new ArrayList<>(List.of("testUsername","testUsername2"));
            }

            @Override
            public int getNumOfRoundsChoice() {
                return 1;
            }
        });

        FrontController.getInstance().dispatchRequest(new AddUsernamesRequest());
        FrontController.getInstance().dispatchRequest(new AddNumOfRoundsRequest());
        FrontController.getInstance().dispatchRequest(new SetMaximumPlayersRequest(2));
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(-1,
                -1,0));
    }

    @Test
    void getDescription() {
        assertEquals("Each player has 5 seconds to answer. Depending on how fast he answered he earns more " +
                        "points.",
                stopTheClock.getDescription());
    }

    @Test
    void hasPreQuestionPhase() {
        assertFalse(stopTheClock.hasPreQuestionPhase());
    }

    @Test
    void testToString() {
        assertEquals("Stop The Clock", stopTheClock.toString());
    }

    @Test
    void hasTimer() {
        assertTrue(stopTheClock.hasTimer());
    }

    /**
     * Test case if the player answers correct while there is time left and with no previous points.
     */
    @Test
    void answerActionsTest() {
        int correctAnswerIndex = Model.getInstance().getRound(0).getQuestions().get(0).getAnswers().
                indexOf(Model.getInstance().getRound(0).getQuestions().
                        get(0).getCorrectAnswer());
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0, correctAnswerIndex, 2000));
        assertEquals(2000*0.2, Model.getInstance().getPlayers().get(0).getScore());
    }

    /**
     * Test case if the player answers correct while there is time left and with previous points.
     */
    @Test
    void answerActionsTest1() {
        int correctAnswerIndex = Model.getInstance().getRound(0).getQuestions().get(0).getAnswers().
                indexOf(Model.getInstance().getRound(0).getQuestions().
                        get(0).getCorrectAnswer());
        Model.getInstance().getPlayers().get(0).setScore(550);
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0, correctAnswerIndex, 2000));
        assertEquals(2000*0.2+550, Model.getInstance().getPlayers().get(0).getScore());
    }

    /**
     * Test case if the player answers correct while there is no time left and with previous points.
     */
    @Test
    void answerActionsTest2() {
        int correctAnswerIndex = Model.getInstance().getRound(0).getQuestions().get(0).getAnswers().
                indexOf(Model.getInstance().getRound(0).getQuestions().
                        get(0).getCorrectAnswer());
        Model.getInstance().getPlayers().get(0).setScore(550);
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0, correctAnswerIndex, 0));
        assertEquals(550, Model.getInstance().getPlayers().get(0).getScore());
    }

    /**
     * Test case if the player answers correct while there is no time left without previous points.
     */
    @Test
    void answerActionsTest3() {
        int correctAnswerIndex = Model.getInstance().getRound(0).getQuestions().get(0).getAnswers().
                indexOf(Model.getInstance().getRound(0).getQuestions().
                        get(0).getCorrectAnswer());
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0, correctAnswerIndex, 0));
        assertEquals(0, Model.getInstance().getPlayers().get(0).getScore());
    }

    /**
     * Test case if the player answers wrong while there is no time left and with no previous points.
     */
    @Test
    void answerActionsTest4() {
        int correctAnswerIndex = Model.getInstance().getRound(0).getQuestions().get(0).getAnswers().
                indexOf(Model.getInstance().getRound(0).getQuestions().
                        get(0).getCorrectAnswer());
        int wrongAnswerIndex;
        if (correctAnswerIndex == 0)
            wrongAnswerIndex = correctAnswerIndex + 1;
        else
            wrongAnswerIndex = correctAnswerIndex - 1;
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0, wrongAnswerIndex, 0));
        assertEquals(0, Model.getInstance().getPlayers().get(0).getScore());
    }

    /**
     * Test case if the player answers wrong while there is no time left and with previous points.
     */
    @Test
    void answerActionsTest5() {
        int correctAnswerIndex = Model.getInstance().getRound(0).getQuestions().get(0).getAnswers().
                indexOf(Model.getInstance().getRound(0).getQuestions().
                        get(0).getCorrectAnswer());
        Model.getInstance().getPlayers().get(0).setScore(500);
        int wrongAnswerIndex;
        if (correctAnswerIndex == 0)
            wrongAnswerIndex = correctAnswerIndex + 1;
        else
            wrongAnswerIndex = correctAnswerIndex - 1;
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0, wrongAnswerIndex, 0));
        assertEquals(500, Model.getInstance().getPlayers().get(0).getScore());
    }

    /**
     * Test case if the player answers wrong while there is time left and with previous points.
     */
    @Test
    void answerActionsTest6() {
        int correctAnswerIndex = Model.getInstance().getRound(0).getQuestions().get(0).getAnswers().
                indexOf(Model.getInstance().getRound(0).getQuestions().
                        get(0).getCorrectAnswer());
        Model.getInstance().getPlayers().get(0).setScore(500);
        int wrongAnswerIndex;
        if (correctAnswerIndex == 0)
            wrongAnswerIndex = correctAnswerIndex + 1;
        else
            wrongAnswerIndex = correctAnswerIndex - 1;
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0, wrongAnswerIndex, 1500));
        assertEquals(500, Model.getInstance().getPlayers().get(0).getScore());
    }

    /**
     * Test case if the player answers wrong while there is time left and without previous points.
     */
    @Test
    void answerActionsTest7() {
        int correctAnswerIndex = Model.getInstance().getRound(0).getQuestions().get(0).getAnswers().
                indexOf(Model.getInstance().getRound(0).getQuestions().
                        get(0).getCorrectAnswer());
        int wrongAnswerIndex;
        if (correctAnswerIndex == 0)
            wrongAnswerIndex = correctAnswerIndex + 1;
        else
            wrongAnswerIndex = correctAnswerIndex - 1;
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0, wrongAnswerIndex, 1500));
        assertEquals(0, Model.getInstance().getPlayers().get(0).getScore());
    }
}