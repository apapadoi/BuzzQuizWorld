package model.gamemodes;

import java.util.ArrayList;
import java.util.List;

/**
 * This enum represents the available gamemodes that can be played by two players.
 *
 * @author Tasos Papadopoulos
 * @version 16.11.2020
 */
public class TwoPlayersGamemodes implements NumerablePlayersGamemode{
    final List<String> gamemodes;

    public TwoPlayersGamemodes() {
        gamemodes = new ArrayList<>(2);
        gamemodes.add("Point Builder");
        gamemodes.add("High Stakes");
        gamemodes.add("Fastest Finger");
        gamemodes.add("Boiling Point,");
        gamemodes.add("Stop The Clock");
    }

    @Override
    public List<String> getAvailableGamemodes() {
        return gamemodes;
    }
}
