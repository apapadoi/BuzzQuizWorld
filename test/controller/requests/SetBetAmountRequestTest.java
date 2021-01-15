package controller.requests;

import controller.FrontController;
import javafx.embed.swing.JFXPanel;
import model.Model;
import model.fileHandler.FileHandler;
import model.gamemodes.Gamemodable;
import model.gamemodes.HighStakes;
import model.gamemodes.factories.GamemodeFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.gui.GUI;
import view.gui.GameplayFrame;
import view.gui.SelectionFrameUI;
import view.gui.UI;
import view.gui.SelectionFrameUI;

import java.awt.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SetBetAmountRequestTest {
    @BeforeEach
    void setUp() {
        new JFXPanel();
        FrontController.getInstance().setView(new GameplayFrame() {
            @Override
            public Dimension getSize() {
                return new Dimension(1,1);
            }

            @Override
            public UI getPreQuestionFrame() {
                return new GUI() {
                    @Override
                    public Dimension getSize() {
                        return new Dimension(1,1);
                    }

                    @Override
                    public void dispose() {
                        super.dispose();
                    }
                };
            }
        });
    }

    @Test
    void execute() {
        Model.getInstance().clearData();
        FileHandler fileHandler = new FileHandler(new ArrayList<>(),
                Paths.get("test/resources/data/questions/textQuestions/textQuestions.txt"),
                Paths.get("test/resources/data/questions/imagedQuestions/imagedQuestions.txt"));
        FrontController.getInstance().setFileHandler(fileHandler);
        FrontController.getInstance().dispatchRequest(new SetGamemodeFactoryRequest(
                new GamemodeFactory() {
                    @Override
                    public Gamemodable getRandomGamemode() {
                        return new HighStakes();
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
                return new ArrayList<>(List.of("testUsername"));
            }
        };
        FrontController.getInstance().dispatchRequest(new AddUsernamesRequest(selectionFrame));
        FrontController.getInstance().dispatchRequest(new AddNumOfRoundsRequest(selectionFrame));
        FrontController.getInstance().dispatchRequest(new SetMaximumPlayersRequest(1));
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(-1,
                -1,0));
        FrontController.getInstance().dispatchRequest(new SetBetAmountRequest(new ArrayList<>(List.of(1000))));
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0,
                Model.getInstance().getRound(Request.roundId).getQuestions().get(Request.questionId).getAnswers().
                        indexOf(Model.getInstance().getRound(Request.roundId).getQuestions().
                                get(Request.questionId).getCorrectAnswer())
                , 1500));
        assertEquals(250, Model.getInstance().getPlayers().get(0).getScore());
    }

    @Test
    void execute1() {
        Model.getInstance().clearData();
        FileHandler fileHandler = new FileHandler(new ArrayList<>(),
                Paths.get("test/resources/data/questions/textQuestions/textQuestions.txt"),
                Paths.get("test/resources/data/questions/imagedQuestions/imagedQuestions.txt"));
        FrontController.getInstance().setFileHandler(fileHandler);
        FrontController.getInstance().dispatchRequest(new SetGamemodeFactoryRequest(
                new GamemodeFactory() {
                    @Override
                    public Gamemodable getRandomGamemode() {
                        return new HighStakes();
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
                return new ArrayList<>(List.of("testUsername"));
            }
        };
        FrontController.getInstance().dispatchRequest(new AddUsernamesRequest(selectionFrame));
        FrontController.getInstance().dispatchRequest(new AddNumOfRoundsRequest(selectionFrame));
        FrontController.getInstance().dispatchRequest(new SetMaximumPlayersRequest(1));
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(-1,
                -1,0));
        FrontController.getInstance().dispatchRequest(new SetBetAmountRequest(new ArrayList<>(List.of(1000))));
        int correctAnswerIndex = Model.getInstance().getRound(Request.roundId).getQuestions().get(Request.questionId).getAnswers().
                indexOf(Model.getInstance().getRound(Request.roundId).getQuestions().
                        get(Request.questionId).getCorrectAnswer());
        int wrongAnswerIndex;
        if(correctAnswerIndex==0)
            wrongAnswerIndex = correctAnswerIndex + 1;
        else
            wrongAnswerIndex = correctAnswerIndex - 1;
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0,
                wrongAnswerIndex, 1500));
        assertEquals(-250, Model.getInstance().getPlayers().get(0).getScore());
    }
}