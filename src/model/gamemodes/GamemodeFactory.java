package model.gamemodes;

import java.util.concurrent.ThreadLocalRandom;

public class GamemodeFactory {
    private static final Gamemodable[] gamemodes = {new PointBuilder(), new HighStakes(), new StopTheClock()};

    public static Gamemodable getRandomGamemode() {
        int randomNumber = ThreadLocalRandom.current().nextInt(3);

        if(randomNumber == 0)
            return gamemodes[0];
        else if ( randomNumber == 1)
            return gamemodes[1];

        return gamemodes[2];
    }
}
