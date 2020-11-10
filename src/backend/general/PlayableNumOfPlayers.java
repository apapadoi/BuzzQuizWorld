package backend.general;

import java.util.List;

/**
 * This interface contains the methods that must be implemented by an enumerated type so it can be added as an
 * available choice for number of players in the game.For example one player,two players,four players etc.
 * @author Tasos Papadopoulos
 * @version 10.11.2020
 * */
public interface PlayableNumOfPlayers {
    /**
     * This method returns the available gamemodes for a specific number of players depending the implementation.
     * @return The available gamemodes as {@code List<String>}
     * */
    static List<String> getAvailableGamemodes() {
        return null;
    }

    /**
     * This method returns the id of the available gamemodes for a specific number of players.
     * @return The id of an available gamemode as {@code int}
     * */
    int ordinal();
}
