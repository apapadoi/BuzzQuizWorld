package model.gamemodes.factories;

import model.gamemodes.Gamemodable;
import model.gamemodes.HighStakes;
import model.gamemodes.PointBuilder;
import model.gamemodes.StopTheClock;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class represents a gamemode factory for one player game session. Contains Point Builder, Stop The Clock and
 * High Stakes gamemodes.
 * Singleton Design Pattern is used.
 * @author Tasos Papadopoulos
 * @version 13.1.2021
 */
public class OnePlayerGamemodeFactory implements GamemodeFactory {
    private static final Gamemodable[] gamemodes = {new PointBuilder(), new HighStakes(), new StopTheClock()};
    private static final OnePlayerGamemodeFactory instance = new OnePlayerGamemodeFactory();

    /**
     * Default constructor.
     */
    private OnePlayerGamemodeFactory() { }

    /**
     * Returns the unique {@code OnePlayerGamemodeFactory} instance
     * @return the gamemode factory for one player game sessions as {@code OnePlayerGamemodeFactory} object
     */
    public static OnePlayerGamemodeFactory getInstance() {
        return instance;
    }

    /**
     * @see GamemodeFactory
     * @return The random gamemode as {@code Gamemodable}
     */
    public Gamemodable getRandomGamemode() {
        int randomNumber = ThreadLocalRandom.current().nextInt(3);

        if(randomNumber == 0)
            return gamemodes[0];
        else if ( randomNumber == 1)
            return gamemodes[1];

        return gamemodes[2];
    }

    /**
     * @see GamemodeFactory
     * Creates new objects for each gamemode the gamemode factory contains.
     */
    @Override
    public void clearGamemodeData() {
        gamemodes[0] = new PointBuilder();
        gamemodes[1] = new HighStakes();
        gamemodes[2] = new StopTheClock();
    }
}
