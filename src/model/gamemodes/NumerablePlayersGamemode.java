package model.gamemodes;

/**
 * This interface contains all the methods that must be implemented by a class that represents the available gamemodes depending the number of
 * players that can play these gamemodes.
 * @author Tasos Papadopoulos
 * @version 5.12.2020
 */
public interface NumerablePlayersGamemode {
    /**
     * Returns a random gamemode depending the number of players.
     * @return a random gamemode as {@code Gamemodable}
     */
    Gamemodable getRandomGamemode();
}
