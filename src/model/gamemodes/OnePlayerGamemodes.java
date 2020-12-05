package model.gamemodes;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A utility class for creating objects of gamemodes that can be played by one player.
 *
 * @author Tasos Papadopoulos
 * @version 5.12.2020
 */
public class OnePlayerGamemodes implements NumerablePlayersGamemode{

    /**
     * Gamemodes that can be played from one player in the current version are PointBuilder and HighStakes.
     * @return a random gamemode, either PointBuilder or HighStakes, as {@code Gamemodable}
     */
    @Override
    public final Gamemodable getRandomGamemode() {
        int randomNumber = ThreadLocalRandom.current().nextInt(2);
        if(randomNumber == 0)
            return new PointBuilder();

        return new HighStakes();
    }
}
