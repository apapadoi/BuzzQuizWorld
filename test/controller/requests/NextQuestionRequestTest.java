package controller.requests;

import controller.FrontController;
import model.Model;
import model.fileHandler.FileHandler;
import model.gamemodes.factories.OnePlayerGamemodeFactory;
import org.junit.jupiter.api.Test;
import view.gui.GUI;
import view.gui.UI;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NextQuestionRequestTest {
    private boolean isVisible;

    @Test
    void execute() {
        FileHandler fileHandler = new FileHandler(new ArrayList<>(),
                Paths.get("test/resources/data/questions/textQuestions/textQuestions.txt"),
                Paths.get("test/resources/data/questions/imagedQuestions/imagedQuestions.txt"));
        FrontController.getInstance().setFileHandler(fileHandler);
        FrontController.getInstance().dispatchRequest(new SetGamemodeFactoryRequest(
                OnePlayerGamemodeFactory.getInstance()
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

        UI gamemodeFrame = new GUI() {
            @Override
            public void setVisible(boolean b) {
                isVisible = b;
            }

            @Override
            public UI getPreQuestionFrame() {
                return new GUI() {
                    @Override
                    public void setVisible(boolean b) {
                    }
                };
            }
        };
        gamemodeFrame.setVisible(true);
        FrontController.getInstance().setView(gamemodeFrame);
        FrontController.getInstance().dispatchRequest(new SetMaximumPlayersRequest(1));
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(-1,
                -1,0));
        FrontController.getInstance().dispatchRequest(new NextQuestionRequest(gamemodeFrame));
        if(Model.getInstance().getRound(0).hasPreQuestionFormat())
            assertFalse(isVisible);
        else
            assertTrue(isVisible);
    }

    @Test
    void execute1() {
        FileHandler fileHandler = new FileHandler(new ArrayList<>(),
                Paths.get("test/resources/data/questions/textQuestions/textQuestions.txt"),
                Paths.get("test/resources/data/questions/imagedQuestions/imagedQuestions.txt"));
        FrontController.getInstance().setFileHandler(fileHandler);
        FrontController.getInstance().dispatchRequest(new SetGamemodeFactoryRequest(
                OnePlayerGamemodeFactory.getInstance()
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

        UI gamemodeFrame = new GUI() {
            @Override
            public void setVisible(boolean b) {
                isVisible = b;
            }

            @Override
            public UI getPreQuestionFrame() {
                return new GUI() {
                    @Override
                    public void setVisible(boolean b) {
                    }
                };
            }
        };
        gamemodeFrame.setVisible(true);
        Model.getInstance().getPlayersAnswered().put(0,true);
        FrontController.getInstance().setView(gamemodeFrame);
        FrontController.getInstance().dispatchRequest(new SetMaximumPlayersRequest(1));
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(-1,
                -1,0));
        FrontController.getInstance().dispatchRequest(new NextQuestionRequest(gamemodeFrame));
        if(Model.getInstance().getRound(0).hasPreQuestionFormat())
            assertFalse(isVisible);
        else
            assertTrue(isVisible);
    }

    @Test
    void execute2() {
        FileHandler fileHandler = new FileHandler(new ArrayList<>(),
                Paths.get("test/resources/data/questions/textQuestions/textQuestions.txt"),
                Paths.get("test/resources/data/questions/imagedQuestions/imagedQuestions.txt"));
        FrontController.getInstance().setFileHandler(fileHandler);
        FrontController.getInstance().dispatchRequest(new SetGamemodeFactoryRequest(
                OnePlayerGamemodeFactory.getInstance()
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

        UI gamemodeFrame = new GUI() {
            @Override
            public void setVisible(boolean b) {
                isVisible = b;
            }

            @Override
            public UI getPreQuestionFrame() {
                return new GUI() {
                    @Override
                    public void setVisible(boolean b) {
                    }
                };
            }
        };
        gamemodeFrame.setVisible(true);
        FrontController.getInstance().setView(gamemodeFrame);
        FrontController.getInstance().dispatchRequest(new SetMaximumPlayersRequest(1));
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(-1,
                -1,0));
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0,
                0,5000));
        FrontController.getInstance().dispatchRequest(new NextQuestionRequest(gamemodeFrame));
        if(Model.getInstance().getRound(0).hasPreQuestionFormat())
            assertFalse(isVisible);
        else
            assertTrue(isVisible);
    }
}