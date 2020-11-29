package model.gamemodes;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This class represents the available gamemodes that can be played by two players.
 *
 * @author Tasos Papadopoulos
 * @version 29.11.2020
 */
public class TwoPlayersGamemodes implements NumerablePlayersGamemode{
    @Override
    public final Gamemodable getRandomGamemode() {
        int randomNumber = ThreadLocalRandom.current().nextInt(2);
        if(randomNumber == 0)
            return new PointBuilder();
        else
            return new HighStakes();
        /*else if(randomNumber == 2)
            return new FastestFinger();
        else if(randomNumber == 3)
            return new BoilingPoint();
        else
            return new StopTheClock();*/

    }
}
