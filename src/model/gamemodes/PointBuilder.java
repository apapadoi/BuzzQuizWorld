package model.gamemodes;

import model.Model;

/**
 * This class represents the gamemode Point Builder.
 *
 * @author Tasos Papadopoulos
 * @version 23.11.2020
 */
public class PointBuilder extends Gamemode {
    /**
     * Default Constructor.
     */
    public PointBuilder() {
        super("Each player that answers correctly earns 1000 points.",5,3);
    }

    /**
     * Returns the gamemode as {@code String}.
     */
    @Override
    public String toString() {
        return "Point Builder";
    }

    /**
     * @see Gamemodable
     */
    @Override
    public void actionIfCorrectAnswer(Model model,int secondsTookToAnswer) {
        model.updateScore(1000);
    }
}
