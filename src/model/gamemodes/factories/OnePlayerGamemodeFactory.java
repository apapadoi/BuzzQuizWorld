package model.gamemodes.factories;

import model.gamemodes.Gamemodable;
import model.gamemodes.HighStakes;
import model.gamemodes.PointBuilder;
import model.gamemodes.StopTheClock;
import java.util.concurrent.ThreadLocalRandom;

public class OnePlayerGamemodeFactory implements GamemodeFactory {
    private static final Gamemodable[] gamemodes = {new PointBuilder(), new HighStakes(), new StopTheClock()};
    private static final OnePlayerGamemodeFactory instance = new OnePlayerGamemodeFactory();

    private OnePlayerGamemodeFactory() { }

    public static OnePlayerGamemodeFactory getInstance() {
        return instance;
    }

    public Gamemodable getRandomGamemode() {
        int randomNumber = ThreadLocalRandom.current().nextInt(3);

        if(randomNumber == 0)
            return gamemodes[0];
        else if ( randomNumber == 1)
            return gamemodes[1];

        return gamemodes[2];
    }
}
