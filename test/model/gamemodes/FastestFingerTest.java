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

class FastestFingerTest {
    private FastestFinger fastestFinger;

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
        fastestFinger = new FastestFinger();
        FileHandler fileHandler = new FileHandler(new ArrayList<>(),
                Paths.get("test/resources/data/questions/textQuestions/textQuestions.txt"),
                Paths.get("test/resources/data/questions/imagedQuestions/imagedQuestions.txt"));
        FrontController.getInstance().setFileHandler(fileHandler);
        FrontController.getInstance().dispatchRequest(new SetGamemodeFactoryRequest(
                new GamemodeFactory() {
                    @Override
                    public Gamemodable getRandomGamemode() {
                        return fastestFinger;
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
        assertEquals("The first player that answers correct wins 1000 points while the second one 500 points.",
                fastestFinger.getDescription());
    }

    @Test
    void hasPreQuestionPhase() {
        assertFalse(fastestFinger.hasPreQuestionPhase());
    }

    @Test
    void testToString() {
        assertEquals("Fastest Finger", fastestFinger.toString());
    }

    @Test
    void hasTimer() {
        assertTrue(fastestFinger.hasTimer());
    }

    /**
     * Test case if the player 1 answers correct first and player 2 answers also correct.
     */
    @Test
    void answerActionsTest() {
        int correctAnswerIndex = Model.getInstance().getRound(0).getQuestions().get(0).getAnswers().
                indexOf(Model.getInstance().getRound(0).getQuestions().
                        get(0).getCorrectAnswer());
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0, correctAnswerIndex, 1100));
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(1,correctAnswerIndex, 1000));
        assertEquals(1000, Model.getInstance().getPlayers().get(0).getScore());
        assertEquals(500, Model.getInstance().getPlayers().get(1).getScore());
    }

    /**
     * Test case if the player 1 answers wrong first and player 2 answers second correct.
     */
    @Test
    void answerActionsTest1() {
        int correctAnswerIndex = Model.getInstance().getRound(0).getQuestions().get(0).getAnswers().
                indexOf(Model.getInstance().getRound(0).getQuestions().
                        get(0).getCorrectAnswer());
        int wrongAnswerIndex;
        if (correctAnswerIndex == 0)
            wrongAnswerIndex = correctAnswerIndex + 1;
        else
            wrongAnswerIndex = correctAnswerIndex - 1;
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0, wrongAnswerIndex, 1500));
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(1,correctAnswerIndex, 1000));
        assertEquals(0, Model.getInstance().getPlayers().get(0).getScore());
        assertEquals(1000, Model.getInstance().getPlayers().get(1).getScore());
    }

    /**
     * Test case if the player 1 answers correct second and player 2 answers first wrong.
     */
    @Test
    void answerActionsTest2() {
        int correctAnswerIndex = Model.getInstance().getRound(0).getQuestions().get(0).getAnswers().
                indexOf(Model.getInstance().getRound(0).getQuestions().
                        get(0).getCorrectAnswer());
        int wrongAnswerIndex;
        if (correctAnswerIndex == 0)
            wrongAnswerIndex = correctAnswerIndex + 1;
        else
            wrongAnswerIndex = correctAnswerIndex - 1;
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0, correctAnswerIndex, 1500));
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(1,wrongAnswerIndex, 1000));
        assertEquals(1000, Model.getInstance().getPlayers().get(0).getScore());
        assertEquals(0, Model.getInstance().getPlayers().get(1).getScore());
    }

    /**
     * Test case if both players answer wrong.
     */
    @Test
    void answerActionsTest3() {
        int correctAnswerIndex = Model.getInstance().getRound(0).getQuestions().get(0).getAnswers().
                indexOf(Model.getInstance().getRound(0).getQuestions().
                        get(0).getCorrectAnswer());
        int wrongAnswerIndex;
        if (correctAnswerIndex == 0)
            wrongAnswerIndex = correctAnswerIndex + 1;
        else
            wrongAnswerIndex = correctAnswerIndex - 1;
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0, wrongAnswerIndex, 1500));
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(1,wrongAnswerIndex, 1000));
        assertEquals(0, Model.getInstance().getPlayers().get(0).getScore());
        assertEquals(0, Model.getInstance().getPlayers().get(1).getScore());
    }

}