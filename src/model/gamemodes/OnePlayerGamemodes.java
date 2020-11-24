package model.gamemodes;

import java.util.ArrayList;
import java.util.List;

/**
 * This enum represents the available gamemodes that can be played by one player.
 *
 * @author Tasos Papadopoulos
 * @version 17.11.2020
 */
public class OnePlayerGamemodes implements NumerablePlayersGamemode{
    final List<String> gamemodes;

    public OnePlayerGamemodes() {
        gamemodes = new ArrayList<>(2);
        gamemodes.add("Point Builder");
        gamemodes.add("High Stakes");
    }

    @Override
    public List<String> getAvailableGamemodes() {
        return gamemodes;
    }
}
