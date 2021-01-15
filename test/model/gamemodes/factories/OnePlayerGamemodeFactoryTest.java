package model.gamemodes.factories;

import model.gamemodes.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OnePlayerGamemodeFactoryTest {
    @Test
    void getInstance() {
        assertNotNull(OnePlayerGamemodeFactory.getInstance());
    }

    @Test
    void getRandomGamemode() {
        int gamemodesCounted = 0;
        for (int i = 0; i < 1000; i++) {
            Gamemodable currentGamemode = OnePlayerGamemodeFactory.getInstance().getRandomGamemode();
            if (currentGamemode instanceof PointBuilder
                    || currentGamemode instanceof HighStakes
                    || currentGamemode instanceof StopTheClock)
                gamemodesCounted++;
        }
        assertEquals(1000, gamemodesCounted);
    }

    @Test
    void clearGamemodeData() {
        OnePlayerGamemodeFactory.getInstance().clearGamemodeData();
        int gamemodesCounted = 0;
        for (int i = 0; i < 1000; i++) {
            Gamemodable currentGamemode = OnePlayerGamemodeFactory.getInstance().getRandomGamemode();
            if (currentGamemode instanceof PointBuilder
                    || currentGamemode instanceof HighStakes
                    || currentGamemode instanceof StopTheClock
                    || currentGamemode instanceof BoilingPoint
                    || currentGamemode instanceof FastestFinger)
                gamemodesCounted++;
        }
        assertEquals(1000, gamemodesCounted);
    }
}