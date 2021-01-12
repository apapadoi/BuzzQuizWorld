package view.gui;

import model.player.Player;
import model.questions.Category;
import model.questions.Difficulty;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * This interface contains all the method that a gui class must implement so it can work correctly.
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 * @version 12.1.2021
 */
public interface UI {

    /**
     * Returns the usernames of the players.
     * @return usernames as {@code List<String>}
     */
    default List<String> getUsernames() {
        return null;
    }

    /**
     * Returns the number of rounds the player chose.
     * @return number of rounds as {@code int}
     */
    default int getNumOfRoundsChoice() { return 0; }

    //TODO maybe one method to update everything except question's image
    /**
     * Updates players usernames.
     * @param players instance of {@code List<Player>}
     */
    default void updateUsernames(List<Player> players) {}

    /**
     * Updates players answers.
     * @param answers instance of {@code List<String>}
     */
    default void updateAnswers(List<String> answers) {}

    /**
     * Updates players scores.
     * @param players instance of {@code List<Player>}
     */
    default void updateScores(List<Player> players) {}

    /**
     * Updates current gamemode of the game.
     * @param gamemodeName instance of {@code String}
     */
    default void updateGamemode(String gamemodeName) {}

    /**
     * Updates current question of the game.
     * @param question instance of {@code String}
     */
    default void updateQuestion(String question) {}

    /**
     * Updates the category of the game.
     * @param category instance of {@code Category}
     */
    default void updateCategory(Category category) {}

    /**
     * Updates current round id.
     * @param id instance {@code String}
     */
    default void updateRoundId(String id) {}

    /**
     * Updates the difficulty of the game.
     * @param difficulty instance of {@code Difficulty}
     */
    default void updateDifficulty(Difficulty difficulty) {}

    /**
     * Updates imaged question of the game.
     * @param imageIcon instance of {@code ImageIcon}
     */
    default void updateQuestionsImage(ImageIcon imageIcon) {}

    /**
     * Returns true if the gamemode has timer and false if it doesnt.
     * @param b instance of {@code boolean}
     */
    default void setHasTimer(boolean b) {}

    /**
     * Restarts the timer count.
     */
    default void restartCount() {}

    /**
     * Stops the timer.
     */
    default void stopTimer() {}

    /**
     * Starts the timer.
     */
    default void startTimer() {}

    /**
     * Returns prequestion frame.
     * @return prequestion frame as {@code UI}
     */
    default UI getPreQuestionFrame() { return null; }

    /**
     * Updates players.
     * @param players instance of {@code List<Player>}
     */
    default void updatePlayers(List<Player> players) {}

    /**
     * Checks if the game has more than two players.
     * @return if game has more than two players as {@code boolean}
     */
    default boolean hasMoreThanTwoPlayers() { return false; }

    /**
     * Returns the size.
     * @return size as {@code Dimension}
     */
    Dimension getSize();

    /**
     * Setts the panel,frame true=visible or false=invisible.
     * @param b instance of {@code boolean}
     */
    void setVisible(boolean b);

    /**
     * Disposes the frame.
     */
    void dispose();

    /**
     * Returns the width.
     * @return width as {@code int}
     */
    int getWidth();

    /**
     * Returns the height.
     * @return height as {@code int}
     */
    int getHeight();
}
