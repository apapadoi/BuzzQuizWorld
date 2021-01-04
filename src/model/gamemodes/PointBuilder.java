package model.gamemodes;

import model.Model;

/**
 * This class represents the gamemode Point Builder. If the player answers correct he earns 1000 points.
 *
 * @author Tasos Papadopoulos
 * @version 5.12.2020
 */
public class PointBuilder extends Gamemode {
    /**
     * Default Constructor.
     */
    public PointBuilder() {
        super("Each player that answers correctly earns 1000 points.",15);
    }

    /**
     * @see Gamemode
     * @return the gamemode as {@code String}
     */
    @Override
    public String toString() {
        return "Point Builder";
    }

    /**
     * If the player answers correct update his score using the {@code model} component.
     * @see Gamemode
     * @param model instance of {@code Model} class
     */
    @Override
    public void actionIfCorrectAnswer(Model model) {
        model.updateScore(1000);
    }


}
