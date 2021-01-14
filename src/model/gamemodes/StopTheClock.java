package model.gamemodes;

import model.Model;

/**
 * This class represents the gamemode Stop The Clock. Each player has 10 seconds to answer. Depending on how fast he
 * answered he earns more points.
 *
 * @author Tasos Papadopoulos
 * @version 24.12.2020
 */
public class StopTheClock extends Gamemode{
    /**
     * Default Constructor.
     */
    public StopTheClock() {
        super("Each player has 5 seconds to answer. Depending on how fast he answered he earns more " +
                "points.",10);
    }

    /**
     * @see Gamemode
     * @return the gamemode as {@code String}
     */
    @Override
    public String toString() {
        return "Stop The Clock";
    }

    /**
     * @see Gamemodable
     */
    @Override
    public void actionIfCorrectAnswer(Model model, int millis, int playerIndex) {
        model.addScore((int)(millis*0.2), playerIndex);
    }

    /**
     * @see Gamemodable
     * @return whether or not a gamemode has timer as {@code boolean}
     */
    @Override
    public boolean hasTimer() {
        return true;
    }
}
