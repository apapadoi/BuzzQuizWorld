package model.gamemodes;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This class represents the available gamemodes that can be played by one player.
 *
 * @author Tasos Papadopoulos
 * @version 29.11.2020
 */
public class OnePlayerGamemodes implements NumerablePlayersGamemode{
    @Override
    public final Gamemodable getRandomGamemode() {
        int randomNumber = ThreadLocalRandom.current().nextInt(2);
        if(randomNumber == 0)
            return new PointBuilder();

        return new HighStakes();
    }
}
