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

class HighStakesTest {
    private HighStakes highStakes;

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
        highStakes = new HighStakes();
        FileHandler fileHandler = new FileHandler(new ArrayList<>(),
                Paths.get("test/resources/data/questions/textQuestions/textQuestions.txt"),
                Paths.get("test/resources/data/questions/imagedQuestions/imagedQuestions.txt"));
        FrontController.getInstance().setFileHandler(fileHandler);
        FrontController.getInstance().dispatchRequest(new SetGamemodeFactoryRequest(
                new GamemodeFactory() {
                    @Override
                    public Gamemodable getRandomGamemode() {
                        return highStakes;
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
        assertEquals("At first the category of the question is shown.\nEach player places his bet.\n" +
                        "Then, the question is shown, each player answers and if he answered correctly\n" +
                        "he earns his bet, otherwise he loses his bet.\n" +
                        "Available bets: 250,500,750,1000\n" +
                        "If player's points get under 250 automatically the game bets all of his points.\n",
                highStakes.getDescription());
    }

    @Test
    void getAvailableTime() {
        assertEquals(15, highStakes.getAvailableTime());
    }

    @Test
    void hasPreQuestionPhase() {
        assertTrue(highStakes.hasPreQuestionPhase());
    }

    @Test
    void hasTimer() {
        assertFalse(highStakes.hasTimer());
    }

    @Test
    void testToString() {
        assertEquals("High Stakes", highStakes.toString());
    }

    /**
     * Test case if the player answers correct and has enough score to bet and then answers wrong but again with enough bet.
     */
    @Test
    void answerActions() {
        int correctAnswerIndex = Model.getInstance().getRound(0).getQuestions().get(0).getAnswers().
                indexOf(Model.getInstance().getRound(0).getQuestions().
                        get(0).getCorrectAnswer());
        Model.getInstance().getPlayers().get(0).setScore(500);
        Model.getInstance().getRound(0).getGamemode().setBetAmount(250,0);
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0, correctAnswerIndex, 1000));
        assertEquals(750, Model.getInstance().getPlayers().get(0).getScore());
        correctAnswerIndex = Model.getInstance().getRound(0).getQuestions().get(1).getAnswers().
                indexOf(Model.getInstance().getRound(0).getQuestions().
                        get(1).getCorrectAnswer());
        int wrongAnswerIndex;
        if (correctAnswerIndex == 0)
            wrongAnswerIndex = correctAnswerIndex + 1;
        else
            wrongAnswerIndex = correctAnswerIndex - 1;
        Model.getInstance().getRound(0).getGamemode().setBetAmount(750,0);
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0,wrongAnswerIndex,1500));
        assertEquals(0, Model.getInstance().getPlayers().get(0).getScore());
    }

    /**
     * Test case if the player answers wrong and has not enough score to bet and then answers correct but again without enough bet.
     */
    @Test
    void answerActions1() {
        int correctAnswerIndex = Model.getInstance().getRound(0).getQuestions().get(0).getAnswers().
                indexOf(Model.getInstance().getRound(0).getQuestions().
                        get(0).getCorrectAnswer());
        Model.getInstance().getPlayers().get(0).setScore(0);
        Model.getInstance().getRound(0).getGamemode().setBetAmount(250,0);
        int wrongAnswerIndex;
        if (correctAnswerIndex == 0)
            wrongAnswerIndex = correctAnswerIndex + 1;
        else
            wrongAnswerIndex = correctAnswerIndex - 1;
        Model.getInstance().getPlayers().get(0).setScore(250); // pre question request gives enough points to the player so he can bet
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0, wrongAnswerIndex, 1000));
        assertEquals(0, Model.getInstance().getPlayers().get(0).getScore());
        correctAnswerIndex = Model.getInstance().getRound(0).getQuestions().get(1).getAnswers().
                indexOf(Model.getInstance().getRound(0).getQuestions().
                        get(1).getCorrectAnswer());
        if (correctAnswerIndex == 0)
            wrongAnswerIndex = correctAnswerIndex + 1;
        else
            wrongAnswerIndex = correctAnswerIndex - 1;
        Model.getInstance().getRound(0).getGamemode().setBetAmount(750,0);
        Model.getInstance().getPlayers().get(0).setScore(250); // pre question request gives enough points to the player so he can bet
        Model.getInstance().getRound(0).getGamemode().setBetAmount(250,0); // if the player bets more than his score,
                                                                                            // game automatically bets 250
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0,correctAnswerIndex,1500));
        assertEquals(500, Model.getInstance().getPlayers().get(0).getScore());
    }
}