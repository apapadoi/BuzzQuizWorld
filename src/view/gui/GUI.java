package view.gui;

import model.player.Player;
import model.questions.Category;
import java.awt.*;
import java.util.List;

public interface GUI {
    default List<String> getUsernames() {
        return null;
    }
    default int getNumOfRoundsChoice() { return 0; }
    default void updateAnswers(List<String> answers) {}
    default void updateScore(List<Player> players) {}
    default void updateGamemode(String gamemodeName) {}
    default void updateQuestion(String question) {}
    default void updateCategory(Category category) {}
    default void updateRoundId(String id) {}
    default Dimension getSize() { return null; }
    default void setVisible(boolean b) {}
    default void updateTimerLabel(String text) {}
    default void dispose() {}
    default void setHasTimer(boolean b) {}
    default void restartCount() {}
    default void stopTimer() {}
    default void startTimer() {}
    default int getCount() { return 0; }
    default GUI getPreQuestionFrame() { return null; }
}
