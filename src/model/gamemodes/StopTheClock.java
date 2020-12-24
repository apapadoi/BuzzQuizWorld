package model.gamemodes;

import model.Model;

/**
 * This class represents the gamemode Stop The Clock. Each player has 5 seconds to answer. Depending on how fast he
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
                "points.",5);
    }

    /**
     * @see Gamemode
     * @return the gamemode as {@code String}
     */
    @Override
    public String toString() {
        return "Stop The Clock";
    }

    @Override
    public void actionsIfCorrectAnswer(Model model, int millis) {
        model.updateScore((int)((this.availableTime*1000-millis)*0.2));
    }
}
