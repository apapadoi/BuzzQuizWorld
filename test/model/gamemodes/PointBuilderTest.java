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

class PointBuilderTest {
    private PointBuilder pointBuilder;

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
        pointBuilder = new PointBuilder();
        FileHandler fileHandler = new FileHandler(new ArrayList<>(),
                Paths.get("test/resources/data/questions/textQuestions/textQuestions.txt"),
                Paths.get("test/resources/data/questions/imagedQuestions/imagedQuestions.txt"));
        FrontController.getInstance().setFileHandler(fileHandler);
        FrontController.getInstance().dispatchRequest(new SetGamemodeFactoryRequest(
                new GamemodeFactory() {
                    @Override
                    public Gamemodable getRandomGamemode() {
                        return pointBuilder;
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
                return new ArrayList<>(List.of("testUsername"));
            }

            @Override
            public int getNumOfRoundsChoice() {
                return 1;
            }
        });

        FrontController.getInstance().dispatchRequest(new AddUsernamesRequest());
        FrontController.getInstance().dispatchRequest(new AddNumOfRoundsRequest());
        FrontController.getInstance().dispatchRequest(new SetMaximumPlayersRequest(1));
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(-1,
                -1,0));
    }

    @Test
    void getDescription() {
        assertEquals("Each player that answers correctly earns 1000 points.",
                pointBuilder.getDescription());
    }

    @Test
    void hasPreQuestionPhase() {
        assertFalse(pointBuilder.hasPreQuestionPhase());
    }

    @Test
    void hasTimer() {
        assertFalse(pointBuilder.hasTimer());
    }

    @Test
    void testToString() {
        assertEquals("Point Builder", pointBuilder.toString());
    }

    /**
     * Test case if the player answers correct and then wrong, while he already has points.
     */
    @Test
    void answerActions() {
        int correctAnswerIndex = Model.getInstance().getRound(0).getQuestions().get(0).getAnswers().
                indexOf(Model.getInstance().getRound(0).getQuestions().
                        get(0).getCorrectAnswer());
        Model.getInstance().getPlayers().get(0).setScore(500);
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0, correctAnswerIndex, 1000));
        assertEquals(1500, Model.getInstance().getPlayers().get(0).getScore());
        correctAnswerIndex = Model.getInstance().getRound(0).getQuestions().get(1).getAnswers().
                indexOf(Model.getInstance().getRound(0).getQuestions().
                        get(1).getCorrectAnswer());
        int wrongAnswerIndex;
        if (correctAnswerIndex == 0)
            wrongAnswerIndex = correctAnswerIndex + 1;
        else
            wrongAnswerIndex = correctAnswerIndex - 1;
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0,wrongAnswerIndex,1500));
        assertEquals(1500, Model.getInstance().getPlayers().get(0).getScore());
    }

    /**
     * Test case if the player answers wrong and then correct, while he has no previous points.
     */
    @Test
    void answerActions1() {
        int correctAnswerIndex = Model.getInstance().getRound(0).getQuestions().get(0).getAnswers().
                indexOf(Model.getInstance().getRound(0).getQuestions().
                        get(0).getCorrectAnswer());
        int wrongAnswerIndex;
        if (correctAnswerIndex == 0)
            wrongAnswerIndex = correctAnswerIndex + 1;
        else
            wrongAnswerIndex = correctAnswerIndex - 1;
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0, wrongAnswerIndex, 1000));
        assertEquals(0, Model.getInstance().getPlayers().get(0).getScore());
        correctAnswerIndex = Model.getInstance().getRound(0).getQuestions().get(1).getAnswers().
                indexOf(Model.getInstance().getRound(0).getQuestions().
                        get(1).getCorrectAnswer());
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0,correctAnswerIndex,1500));
        assertEquals(1000, Model.getInstance().getPlayers().get(0).getScore());
    }
}