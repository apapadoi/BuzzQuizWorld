package model.gamemodes.factories;

import model.gamemodes.*;
import java.util.concurrent.ThreadLocalRandom;

public class TwoPlayersGamemodeFactory implements GamemodeFactory{
    private static final Gamemodable[] gamemodes = {new PointBuilder(), new HighStakes(), new StopTheClock(),
                                                new BoilingPoint(), new FastestFinger()};
    private static final TwoPlayersGamemodeFactory instance = new TwoPlayersGamemodeFactory();

    private TwoPlayersGamemodeFactory() { }

    public static TwoPlayersGamemodeFactory getInstance() {
        return instance;
    }

    @Override
    public Gamemodable getRandomGamemode() {
        int randomNumber = ThreadLocalRandom.current().nextInt(5);

        if(randomNumber == 0)
            return gamemodes[0];
        else if ( randomNumber == 1)
            return gamemodes[1];
        else if( randomNumber == 2)
            return gamemodes[2];
        else if( randomNumber == 3)
            return gamemodes[3];

        return gamemodes[4];
    }

    @Override
    public void clearGamemodeData() {
        gamemodes[0] = new PointBuilder();
        gamemodes[1] = new HighStakes();
        gamemodes[2] = new StopTheClock();
        gamemodes[3] = new BoilingPoint();
        gamemodes[4] = new FastestFinger();
    }
}
