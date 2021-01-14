package model.gamemodes.factories;

import model.gamemodes.Gamemodable;

/**
 * This interface contains all the methods that a new gamemode factory class must implement so it can be added to
 * the game.
 * @author Tasos Papadopoulos
 * @version 13.1.2021
 */
public interface GamemodeFactory {
    /**
     * Returns a random gamemode.
     * @return The random gamemode as {@code Gamemodable}
     */
    Gamemodable getRandomGamemode();

    /**
     * Creates new objects for each gamemode the gamemode factory contains.
     */
    void clearGamemodeData();
}
