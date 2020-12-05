package model.gamemodes;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A utility class for creating objects of gamemodes that can be played by two players.
 *
 * @author Tasos Papadopoulos
 * @version 5.12.2020
 */
public class TwoPlayersGamemodes implements NumerablePlayersGamemode{

    /**
     * There are no gamemodes that can be played from two player in the current version. This method is under development for the next version.
     * @return a random gamemode, either PointBuilder or HighStakes, as {@code Gamemodable}
     */
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
