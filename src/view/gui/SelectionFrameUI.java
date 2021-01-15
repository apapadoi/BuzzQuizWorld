package view.gui;

import java.util.List;

/**
 * This interface contains all the method that a class needs to implement so it can be used as a source to select
 * usernames and number of rounds in a game session.
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 * @version 12.1.2021
 */
public interface SelectionFrameUI{
    /**
     * Returns the number of rounds the player chose.
     * @return number of rounds as {@code int}
     */
    default int getNumOfRoundsChoice() { return 0; }

    /**
     * Returns the usernames of the players.
     * @return usernames as {@code List<String>}
     */
    default List<String> getUsernames() {
        return null;
    }
}
