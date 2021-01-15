package controller.requests;

import controller.FrontController;
import javafx.embed.swing.JFXPanel;
import model.fileHandler.FileHandler;
import model.gamemodes.Gamemodable;
import model.gamemodes.HighStakes;
import model.gamemodes.PointBuilder;
import model.gamemodes.factories.GamemodeFactory;
import model.player.Player;
import model.questions.Category;
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

class PreQuestionRequestTest {
    private boolean categoryUpdated = false;
    private boolean gamemodeUpdated = false;
    private boolean scoresUpdated = false;
    private boolean countRestarted = false;
    private boolean timerStarted = false;
    private boolean frameIsVisible = false;
    private FileHandler fileHandler;
    private UI gamemodeFrame;

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
    void execute1() {
        categoryUpdated = false;
        gamemodeUpdated = false;
        scoresUpdated = false;
        countRestarted = false;
        timerStarted = false;
        frameIsVisible = false;
        fileHandler = new FileHandler(new ArrayList<>(),
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
        gamemodeFrame = new GUI() {
            @Override
            public UI getPreQuestionFrame() {
                return new GUI() {
                    @Override
                    public void updateCategory(Category category) {
                        PreQuestionRequestTest.this.categoryUpdated = true;
                    }

                    @Override
                    public void updatePlayerData(List<Player> players) {
                        PreQuestionRequestTest.this.scoresUpdated = true;
                    }

                    @Override
                    public void updateGamemode(String gamemodeName) {
                        PreQuestionRequestTest.this.gamemodeUpdated = true;
                    }
                };
            }

            @Override
            public void restartCount() {
                PreQuestionRequestTest.this.countRestarted = true;
            }

            @Override
            public void startTimer() {
                PreQuestionRequestTest.this.timerStarted = true;
            }

            @Override
            public void setVisible(boolean b) {
                PreQuestionRequestTest.this.frameIsVisible = true;
            }
        };
        Request.roundId = 1;
        FrontController.getInstance().dispatchRequest(new PreQuestionRequest(gamemodeFrame));
        assertFalse(categoryUpdated);
        assertFalse(gamemodeUpdated);
        assertFalse(scoresUpdated);
        assertFalse(countRestarted);
        assertFalse(timerStarted);
        assertFalse(frameIsVisible);
    }

    @Test
    void execute2() {
        categoryUpdated = false;
        gamemodeUpdated = false;
        scoresUpdated = false;
        countRestarted = false;
        timerStarted = false;
        frameIsVisible = false;
        fileHandler = new FileHandler(new ArrayList<>(),
                Paths.get("test/resources/data/questions/textQuestions/textQuestions.txt"),
                Paths.get("test/resources/data/questions/imagedQuestions/imagedQuestions.txt"));
        FrontController.getInstance().setFileHandler(fileHandler);
        FrontController.getInstance().dispatchRequest(new SetGamemodeFactoryRequest(
                new GamemodeFactory() {
                    @Override
                    public Gamemodable getRandomGamemode() {
                        return new PointBuilder();
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
        gamemodeFrame = new GUI() {
            @Override
            public UI getPreQuestionFrame() {
                return new GUI() {
                    @Override
                    public void updateCategory(Category category) {
                        PreQuestionRequestTest.this.categoryUpdated = true;
                    }

                    @Override
                    public void updatePlayerData(List<Player> players) {
                        PreQuestionRequestTest.this.scoresUpdated = true;
                    }

                    @Override
                    public void updateGamemode(String gamemodeName) {
                        PreQuestionRequestTest.this.gamemodeUpdated = true;
                    }
                };
            }

            @Override
            public void restartCount() {
                PreQuestionRequestTest.this.countRestarted = true;
            }

            @Override
            public void startTimer() {
                PreQuestionRequestTest.this.timerStarted = true;
            }

            @Override
            public void setVisible(boolean b) {
                PreQuestionRequestTest.this.frameIsVisible = true;
            }
        };
        FrontController.getInstance().dispatchRequest(new PreQuestionRequest(gamemodeFrame));
        assertFalse(categoryUpdated);
        assertFalse(gamemodeUpdated);
        assertFalse(scoresUpdated);
        assertTrue(countRestarted);
        assertTrue(timerStarted);
        assertTrue(frameIsVisible);
    }

    @Test
    void execute3() {
        categoryUpdated = false;
        gamemodeUpdated = false;
        scoresUpdated = false;
        countRestarted = false;
        timerStarted = false;
        frameIsVisible = false;
        fileHandler = new FileHandler(new ArrayList<>(),
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
        gamemodeFrame = new GUI() {
            @Override
            public UI getPreQuestionFrame() {
                return new GUI() {
                    @Override
                    public void updateCategory(Category category) {
                        PreQuestionRequestTest.this.categoryUpdated = true;
                    }

                    @Override
                    public void updatePlayerData(List<Player> players) {
                        PreQuestionRequestTest.this.scoresUpdated = true;
                    }

                    @Override
                    public void updateGamemode(String gamemodeName) {
                        PreQuestionRequestTest.this.gamemodeUpdated = true;
                    }
                };
            }

            @Override
            public void restartCount() {
                PreQuestionRequestTest.this.countRestarted = true;
            }

            @Override
            public void startTimer() {
                PreQuestionRequestTest.this.timerStarted = true;
            }

            @Override
            public void setVisible(boolean b) {
                PreQuestionRequestTest.this.frameIsVisible = true;
            }
        };
        FrontController.getInstance().dispatchRequest(new PreQuestionRequest(gamemodeFrame));
        assertTrue(categoryUpdated);
        assertTrue(gamemodeUpdated);
        assertTrue(scoresUpdated);
        assertFalse(countRestarted);
        assertFalse(timerStarted);
        assertFalse(frameIsVisible);
    }

    @Test
    void execute4() {
        categoryUpdated = false;
        gamemodeUpdated = false;
        scoresUpdated = false;
        countRestarted = false;
        timerStarted = false;
        frameIsVisible = false;
        fileHandler = new FileHandler(new ArrayList<>(),
                Paths.get("test/resources/data/questions/textQuestions/textQuestions.txt"),
                Paths.get("test/resources/data/questions/imagedQuestions/imagedQuestions.txt"));
        FrontController.getInstance().setFileHandler(fileHandler);
        FrontController.getInstance().dispatchRequest(new SetGamemodeFactoryRequest(
                new GamemodeFactory() {
                    @Override
                    public Gamemodable getRandomGamemode() {
                        return new PointBuilder();
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
        gamemodeFrame = new GUI() {
            @Override
            public UI getPreQuestionFrame() {
                return new GUI() {
                    @Override
                    public void updateCategory(Category category) {
                        PreQuestionRequestTest.this.categoryUpdated = true;
                    }

                    @Override
                    public void updatePlayerData(List<Player> players) {
                        PreQuestionRequestTest.this.scoresUpdated = true;
                    }

                    @Override
                    public void updateGamemode(String gamemodeName) {
                        PreQuestionRequestTest.this.gamemodeUpdated = true;
                    }
                };
            }

            @Override
            public void restartCount() {
                PreQuestionRequestTest.this.countRestarted = true;
            }

            @Override
            public void startTimer() {
                PreQuestionRequestTest.this.timerStarted = true;
            }

            @Override
            public void setVisible(boolean b) {
                PreQuestionRequestTest.this.frameIsVisible = true;
            }
        };
        Request.roundId = 1;
        FrontController.getInstance().dispatchRequest(new PreQuestionRequest(gamemodeFrame));
        assertFalse(categoryUpdated);
        assertFalse(gamemodeUpdated);
        assertFalse(scoresUpdated);
        assertFalse(countRestarted);
        assertFalse(timerStarted);
        assertFalse(frameIsVisible);
    }
}