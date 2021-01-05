package model.gamemodes;

import model.Model;
import model.player.Player;

import java.util.HashMap;

public class BoilingPoint extends Gamemode{
    /**
     * Default Constructor.
     */
    public BoilingPoint() {
        super("First player that answers correct 5 questions earns 5000 points", 15);
    }

    /**
     * @see Gamemode
     * @return the gamemode as {@code String}
     */
    @Override
    public String toString() {
        return "Boiling Point";
    }


    @Override
    public void actionIfCorrectAnswer(Model model, int millis, int playerIndex) {

    }
}
