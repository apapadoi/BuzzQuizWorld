package controller.requests;

import controller.FrontController;
import javafx.embed.swing.JFXPanel;
import model.Model;
import model.fileHandler.FileHandler;
import model.gamemodes.Gamemodable;
import model.gamemodes.PointBuilder;
import model.gamemodes.factories.GamemodeFactory;
import model.player.Player;
import model.questions.Category;
import model.questions.Difficulty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.gui.GUI;
import view.gui.GameplayFrame;
import view.gui.SelectionFrameUI;
import view.gui.UI;
import view.gui.SelectionFrameUI;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class UpdateDataRequestTest {
    private FileHandler fileHandler;
    private boolean updateUsernames = false;
    private boolean updateAnswers = false;
    private boolean updateScores = false;
    private boolean updateGamemodes = false;
    private boolean updateQuestion = false;
    private boolean updateCategory = false;
    private boolean updateRoundId = false;
    private boolean updateDifficulty = false;
    private boolean updateQuestionsImage = false;
    private boolean setHasTimer = true;
    private boolean gamemodeFrameIsVisible = false;

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

    /**
     * Test case for a player that tries to answer again with choosing the correct answer.
     */
    @Test
    void execute() {
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
        Model.getInstance().getPlayersAnswered().put(0,true);
        int correctAnswerIndex = Model.getInstance().getRound(Request.roundId).getQuestions().get(Request.questionId).getAnswers().
                indexOf(Model.getInstance().getRound(Request.roundId).getQuestions().
                        get(Request.questionId).getCorrectAnswer());
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0,
                correctAnswerIndex,500));
        assertEquals(0, Model.getInstance().getPlayers().get(0).getScore());
    }

    /**
     * Test case for a player that tries to answer again with choosing the wrong answer.
     */
    @Test
    void execute1() {
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
        Model.getInstance().getPlayersAnswered().put(0,true);
        int correctAnswerIndex = Model.getInstance().getRound(Request.roundId).getQuestions().get(Request.questionId).getAnswers().
                indexOf(Model.getInstance().getRound(Request.roundId).getQuestions().
                        get(Request.questionId).getCorrectAnswer());
        int wrongAnswerIndex;
        if(correctAnswerIndex==0)
            wrongAnswerIndex = correctAnswerIndex + 1;
        else
            wrongAnswerIndex = correctAnswerIndex - 1;
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0,wrongAnswerIndex,500));
        assertEquals(0, Model.getInstance().getPlayers().get(0).getScore());
    }

    /**
     * Test case when a player answers first time with choosing the correct answer.
     */
    @Test
    void execute2() {
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
        int correctAnswerIndex = Model.getInstance().getRound(Request.roundId).getQuestions().get(Request.questionId).getAnswers().
                indexOf(Model.getInstance().getRound(Request.roundId).getQuestions().
                        get(Request.questionId).getCorrectAnswer());
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0,correctAnswerIndex,500));
        assertEquals(1000, Model.getInstance().getPlayers().get(0).getScore());
    }

    /**
     * Test case when a player answers first time with choosing the wrong answer.
     */
    @Test
    void execute3() {
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
        int correctAnswerIndex = Model.getInstance().getRound(Request.roundId).getQuestions().get(Request.questionId).getAnswers().
                indexOf(Model.getInstance().getRound(Request.roundId).getQuestions().
                        get(Request.questionId).getCorrectAnswer());
        int wrongAnswerIndex;
        if(correctAnswerIndex==0)
            wrongAnswerIndex = correctAnswerIndex + 1;
        else
            wrongAnswerIndex = correctAnswerIndex - 1;
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0,wrongAnswerIndex,500));
        assertEquals(0, Model.getInstance().getPlayers().get(0).getScore());
    }

    /**
     * Test case when there is a player that has not answered yet.
     */
    @Test
    void execute4() {
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
        FrontController.getInstance().dispatchRequest(new SetMaximumPlayersRequest(2));
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(-1,
                -1,0));
        int correctAnswerIndex = Model.getInstance().getRound(Request.roundId).getQuestions().get(Request.questionId).getAnswers().
                indexOf(Model.getInstance().getRound(Request.roundId).getQuestions().
                        get(Request.questionId).getCorrectAnswer());
        int wrongAnswerIndex;
        if(correctAnswerIndex==0)
            wrongAnswerIndex = correctAnswerIndex + 1;
        else
            wrongAnswerIndex = correctAnswerIndex - 1;
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0,wrongAnswerIndex,500));
        assertTrue(Model.getInstance().getPlayersAnswered().get(0));
        assertFalse(Model.getInstance().getPlayersAnswered().get(1));
    }

    /**
     * Test case when the last player answers and it's not the last question.
     */
    @Test
    void execute5() {
        updateUsernames = false;
        updateAnswers = false;
        updateScores = false;
        updateGamemodes = false;
        updateQuestion = false;
        updateCategory = false;
        updateRoundId = false;
        updateDifficulty = false;
        updateQuestionsImage = false;
        setHasTimer = true;
        gamemodeFrameIsVisible = false;
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
                return new ArrayList<>(List.of("testUsername","testUsername2"));
            }
        };

        FrontController.getInstance().dispatchRequest(new AddUsernamesRequest(selectionFrame));
        FrontController.getInstance().dispatchRequest(new AddNumOfRoundsRequest(selectionFrame));
        FrontController.getInstance().dispatchRequest(new SetMaximumPlayersRequest(2));
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(-1,
                -1,0));
        FrontController.getInstance().setView(new GUI() {
            @Override
            public void updateAnswers(List<String> answers) {
                updateAnswers = true;
            }

            @Override
            public void updatePlayerData(List<Player> players) {
                updateUsernames = true;
                updateScores = true;
            }

            @Override
            public void updateGamemode(String gamemodeName) {
                updateGamemodes = true;
            }

            @Override
            public void updateQuestion(String question) {
                updateQuestion = true;
            }

            @Override
            public void updateCategory(Category category) {
                updateCategory = true;
            }

            @Override
            public void updateRoundId(String id) {
                updateRoundId = true;
            }

            @Override
            public void updateDifficulty(Difficulty difficulty) {
                updateDifficulty = true;
            }

            @Override
            public void updateQuestionsImage(ImageIcon imageIcon) {
                updateQuestionsImage = true;
            }

            @Override
            public void setHasTimer(boolean b) {
                setHasTimer = b;
            }

            @Override
            public void setVisible(boolean b) {
                gamemodeFrameIsVisible = b;
            }
        });
        int correctAnswerIndex = Model.getInstance().getRound(Request.roundId).getQuestions().get(Request.questionId).getAnswers().
                indexOf(Model.getInstance().getRound(Request.roundId).getQuestions().
                        get(Request.questionId).getCorrectAnswer());
        int wrongAnswerIndex;
        if(correctAnswerIndex==0)
            wrongAnswerIndex = correctAnswerIndex + 1;
        else
            wrongAnswerIndex = correctAnswerIndex - 1;
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0,wrongAnswerIndex,1000));
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(1,correctAnswerIndex,500));
        assertTrue(updateUsernames);
        assertTrue(updateAnswers);
        assertTrue(updateScores );
        assertTrue(updateGamemodes);
        assertTrue(updateQuestion);
        assertTrue(updateCategory);
        assertTrue(updateRoundId);
        assertTrue(updateDifficulty);
        assertTrue(updateQuestionsImage);
        assertTrue(setHasTimer);
        assertFalse(gamemodeFrameIsVisible);
    }


    /**
     * Test case when the last player answers and it's the last question.
     */
    @Test
    void execute6() {
        updateUsernames = false;
        updateAnswers = false;
        updateScores = false;
        updateGamemodes = false;
        updateQuestion = false;
        updateCategory = false;
        updateRoundId = false;
        updateDifficulty = false;
        updateQuestionsImage = false;
        setHasTimer = true;
        gamemodeFrameIsVisible = false;
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
                return new ArrayList<>(List.of("testUsername","testUsername2"));
            }
        };

        FrontController.getInstance().dispatchRequest(new AddUsernamesRequest(selectionFrame));
        FrontController.getInstance().dispatchRequest(new AddNumOfRoundsRequest(selectionFrame));
        FrontController.getInstance().dispatchRequest(new SetMaximumPlayersRequest(2));
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(-1,
                -1,0));
        Request.questionId = 4;
        FrontController.getInstance().setView(new GUI() {
            @Override
            public void updateAnswers(List<String> answers) {
                updateAnswers = true;
            }

            @Override
            public void updatePlayerData(List<Player> players) {
                updateScores = true;
                updateUsernames = true;
            }

            @Override
            public void updateGamemode(String gamemodeName) {
                updateGamemodes = true;
            }

            @Override
            public void updateQuestion(String question) {
                updateQuestion = true;
            }

            @Override
            public void updateCategory(Category category) {
                updateCategory = true;
            }

            @Override
            public void updateRoundId(String id) {
                updateRoundId = true;
            }

            @Override
            public void updateDifficulty(Difficulty difficulty) {
                updateDifficulty = true;
            }

            @Override
            public void updateQuestionsImage(ImageIcon imageIcon) {
                updateQuestionsImage = true;
            }

            @Override
            public void setHasTimer(boolean b) {
                setHasTimer = b;
            }

            @Override
            public void setVisible(boolean b) {
                gamemodeFrameIsVisible = b;
            }
        });
        int correctAnswerIndex = Model.getInstance().getRound(Request.roundId).getQuestions().get(Request.questionId).getAnswers().
                indexOf(Model.getInstance().getRound(Request.roundId).getQuestions().
                        get(Request.questionId).getCorrectAnswer());
        int wrongAnswerIndex;
        if(correctAnswerIndex==0)
            wrongAnswerIndex = correctAnswerIndex + 1;
        else
            wrongAnswerIndex = correctAnswerIndex - 1;
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0,wrongAnswerIndex,1000));
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(1,correctAnswerIndex,500));
        assertFalse(updateUsernames);
        assertFalse(updateAnswers);
        assertFalse(updateScores );
        assertFalse(updateGamemodes);
        assertFalse(updateQuestion);
        assertFalse(updateCategory);
        assertFalse(updateRoundId);
        assertFalse(updateDifficulty);
        assertFalse(updateQuestionsImage);
        assertFalse(setHasTimer);
        assertTrue(gamemodeFrameIsVisible);
    }

    /**
     * Test case when the last player answers and it's the last question while having negative score e.g. losing his
     * bet from High Stakes.
     */
    @Test
    void execute7() {
        updateUsernames = false;
        updateAnswers = false;
        updateScores = false;
        updateGamemodes = false;
        updateQuestion = false;
        updateCategory = false;
        updateRoundId = false;
        updateDifficulty = false;
        updateQuestionsImage = false;
        setHasTimer = true;
        gamemodeFrameIsVisible = false;
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
                return new ArrayList<>(List.of("testUsername","testUsername2"));
            }
        };

        FrontController.getInstance().dispatchRequest(new AddUsernamesRequest(selectionFrame));
        FrontController.getInstance().dispatchRequest(new AddNumOfRoundsRequest(selectionFrame));
        FrontController.getInstance().dispatchRequest(new SetMaximumPlayersRequest(2));
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(-1,
                -1,0));
        Request.questionId = 4;
        FrontController.getInstance().setView(new GUI() {
            @Override
            public void updateAnswers(List<String> answers) {
                updateAnswers = true;
            }

            @Override
            public void updatePlayerData(List<Player> players) {
                updateScores = true;
                updateUsernames = true;
            }

            @Override
            public void updateGamemode(String gamemodeName) {
                updateGamemodes = true;
            }

            @Override
            public void updateQuestion(String question) {
                updateQuestion = true;
            }

            @Override
            public void updateCategory(Category category) {
                updateCategory = true;
            }

            @Override
            public void updateRoundId(String id) {
                updateRoundId = true;
            }

            @Override
            public void updateDifficulty(Difficulty difficulty) {
                updateDifficulty = true;
            }

            @Override
            public void updateQuestionsImage(ImageIcon imageIcon) {
                updateQuestionsImage = true;
            }

            @Override
            public void setHasTimer(boolean b) {
                setHasTimer = b;
            }

            @Override
            public void setVisible(boolean b) {
                gamemodeFrameIsVisible = b;
            }
        });
        int correctAnswerIndex = Model.getInstance().getRound(Request.roundId).getQuestions().get(Request.questionId).getAnswers().
                indexOf(Model.getInstance().getRound(Request.roundId).getQuestions().
                        get(Request.questionId).getCorrectAnswer());
        int wrongAnswerIndex;
        if(correctAnswerIndex==0)
            wrongAnswerIndex = correctAnswerIndex + 1;
        else
            wrongAnswerIndex = correctAnswerIndex - 1;
        Model.getInstance().getPlayers().get(1).setScore(-1100);
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0,wrongAnswerIndex,1000));
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(1,correctAnswerIndex,500));
        assertFalse(updateUsernames);
        assertFalse(updateAnswers);
        assertFalse(updateScores );
        assertFalse(updateGamemodes);
        assertFalse(updateQuestion);
        assertFalse(updateCategory);
        assertFalse(updateRoundId);
        assertFalse(updateDifficulty);
        assertFalse(updateQuestionsImage);
        assertFalse(setHasTimer);
        assertTrue(gamemodeFrameIsVisible);
        assertEquals(0, Model.getInstance().getScore(1));
    }
}