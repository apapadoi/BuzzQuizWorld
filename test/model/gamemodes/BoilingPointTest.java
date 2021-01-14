package model.gamemodes;

import controller.FrontController;
import controller.requests.*;
import model.Model;
import model.fileHandler.FileHandler;
import model.gamemodes.factories.GamemodeFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.gui.GUI;
import view.gui.SelectionFrameUI;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoilingPointTest {
    private BoilingPoint boilingPoint;

    @BeforeEach
    void setUp() {
        boilingPoint = new BoilingPoint();
        FileHandler fileHandler = new FileHandler(new ArrayList<>(),
                Paths.get("test/resources/data/questions/textQuestions/textQuestions.txt"),
                Paths.get("test/resources/data/questions/imagedQuestions/imagedQuestions.txt"));
        FrontController.getInstance().setFileHandler(fileHandler);
        FrontController.getInstance().dispatchRequest(new SetGamemodeFactoryRequest(
                new GamemodeFactory() {
                    @Override
                    public Gamemodable getRandomGamemode() {
                        return boilingPoint;
                    }

                    @Override
                    public void clearGamemodeData() {

                    }
                }
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
                return new ArrayList<>(List.of("testUsername","testUsername2"));
            }
        };
        FrontController.getInstance().dispatchRequest(new AddUsernamesRequest(selectionFrame));
        FrontController.getInstance().dispatchRequest(new AddNumOfRoundsRequest(selectionFrame));
        FrontController.getInstance().dispatchRequest(new SetMaximumPlayersRequest(2));
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(-1,
                -1,0));
    }

    @Test
    void getDescription() {
        assertEquals("First player that answers correct 5 questions earns 5000 points",
                boilingPoint.getDescription());
    }

    @Test
    void getAvailableTime() {
        assertEquals(15, boilingPoint.getAvailableTime());
    }

    @Test
    void hasPreQuestionPhase() {
        assertFalse(boilingPoint.hasPreQuestionPhase());
    }

    @Test
    void hasTimer() {
        assertFalse(boilingPoint.hasTimer());
    }

    @Test
    void testToString() {
        assertEquals("Boiling Point", boilingPoint.toString());
    }

    /**
     * Test case if the player 1 chooses the wrong answer at the last question and player 2 answers all questions correct.
     */
    @Test
    void answerActions() {
        for(int i=0;i<4;i++) {
            int correctAnswerIndex = Model.getInstance().getRound(0).getQuestions().get(i).getAnswers().
                    indexOf(Model.getInstance().getRound(0).getQuestions().
                            get(i).getCorrectAnswer());
            FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0, correctAnswerIndex, 1000));
            FrontController.getInstance().dispatchRequest(new UpdateDataRequest(1,correctAnswerIndex, 1000));
        }
        int correctAnswerIndex = Model.getInstance().getRound(0).getQuestions().get(4).getAnswers().
                indexOf(Model.getInstance().getRound(0).getQuestions().
                        get(4).getCorrectAnswer());
        int wrongAnswerIndex;
        if (correctAnswerIndex == 0)
            wrongAnswerIndex = correctAnswerIndex + 1;
        else
            wrongAnswerIndex = correctAnswerIndex - 1;
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0, wrongAnswerIndex, 1000));
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(1,correctAnswerIndex, 1000));
        assertEquals(0, Model.getInstance().getPlayers().get(0).getScore());
        assertEquals(5000, Model.getInstance().getPlayers().get(1).getScore());
    }

    /**
     * Test case if the player 2 chooses the wrong answer at the last question and player 1 answers all questions correct.
     */
    @Test
    void answerActions1() {
        for(int i=0;i<4;i++) {
            int correctAnswerIndex = Model.getInstance().getRound(0).getQuestions().get(i).getAnswers().
                    indexOf(Model.getInstance().getRound(0).getQuestions().
                            get(i).getCorrectAnswer());
            FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0, correctAnswerIndex, 1000));
            FrontController.getInstance().dispatchRequest(new UpdateDataRequest(1,correctAnswerIndex, 1000));
        }
        int correctAnswerIndex = Model.getInstance().getRound(0).getQuestions().get(4).getAnswers().
                indexOf(Model.getInstance().getRound(0).getQuestions().
                        get(4).getCorrectAnswer());
        int wrongAnswerIndex;
        if (correctAnswerIndex == 0)
            wrongAnswerIndex = correctAnswerIndex + 1;
        else
            wrongAnswerIndex = correctAnswerIndex - 1;
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(1, wrongAnswerIndex, 1000));
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0,correctAnswerIndex, 1000));
        assertEquals(0, Model.getInstance().getPlayers().get(1).getScore());
        assertEquals(5000, Model.getInstance().getPlayers().get(0).getScore());
    }

    /**
     * Test case if the 1st player answers first the 5th question correctly.
     */
    @Test
    void answerActions2() {
        for(int i=0;i<4;i++) {
            int correctAnswerIndex = Model.getInstance().getRound(0).getQuestions().get(i).getAnswers().
                    indexOf(Model.getInstance().getRound(0).getQuestions().
                            get(i).getCorrectAnswer());
            FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0, correctAnswerIndex, 1000));
            FrontController.getInstance().dispatchRequest(new UpdateDataRequest(1,correctAnswerIndex, 1000));
        }
        int correctAnswerIndex = Model.getInstance().getRound(0).getQuestions().get(4).getAnswers().
                indexOf(Model.getInstance().getRound(0).getQuestions().
                        get(4).getCorrectAnswer());
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0,correctAnswerIndex, 1000));
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(1, correctAnswerIndex, 1000));
        assertEquals(5000, Model.getInstance().getPlayers().get(0).getScore());
        assertEquals(0, Model.getInstance().getPlayers().get(1).getScore());
    }
}