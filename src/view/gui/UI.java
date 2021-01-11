package view.gui;

import model.player.Player;
import model.questions.Category;
import model.questions.Difficulty;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public interface UI {
    default List<String> getUsernames() {
        return null;
    }
    default int getNumOfRoundsChoice() { return 0; }
    //TODO maybe one method to update everything except question's image
    default void updateUsernames(List<Player> players) {}
    default void updateAnswers(List<String> answers) {}
    default void updateScores(List<Player> players) {}
    default void updateGamemode(String gamemodeName) {}
    default void updateQuestion(String question) {}
    default void updateCategory(Category category) {}
    default void updateRoundId(String id) {}
    default void updateDifficulty(Difficulty difficulty) {}
    default void updateQuestionsImage(ImageIcon imageIcon) {}
    default void setHasTimer(boolean b) {}
    default void restartCount() {}
    default void stopTimer() {}
    default void startTimer() {}
    default UI getPreQuestionFrame() { return null; }
    default void updatePlayers(List<Player> players) {}
    default boolean hasMoreThanTwoPlayers() { return false; }


    Dimension getSize();
    void setVisible(boolean b);
    void dispose();
    int getWidth();
    int getHeight();
}
