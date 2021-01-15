package model.gamemodes;

import model.Model;
import java.util.HashMap;

/**
 * This class represents the gamemode Boiling Point. First player that answers correct the 5 questions earns 5000
 * points.
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 * @version 13.1.2021
 * */
public class BoilingPoint extends Gamemode{
    private final HashMap<Integer, Integer> correctAnswersOfPlayerIndex;

    /**
     * Default Constructor.
     */
    public BoilingPoint() {
        super("First player that answers correct 5 questions earns 5000 points", 15);
        correctAnswersOfPlayerIndex = new HashMap<>();
        for(int i=0;i<Model.getInstance().getMaxPlayers();i++)
            correctAnswersOfPlayerIndex.put(i, 0);
    }

    /**
     * @see Gamemode
     * @return the gamemode as {@code String}
     */
    @Override
    public String toString() {
        return "Boiling Point";
    }

    /**
     * If the players answers correct update his score using the model component.
     * @see Gamemode
     */
    @Override
    public void actionIfCorrectAnswer(Model model, int millis, int playerIndex) {
        correctAnswersOfPlayerIndex.merge(playerIndex, 1, Integer::sum);

        if(correctAnswersOfPlayerIndex.get(playerIndex)==5) {
            model.addScore(5000, playerIndex);
            for(int i=0;i<Model.getInstance().getMaxPlayers();i++)
                correctAnswersOfPlayerIndex.put(i, 0);
        }
    }

    /**
     * If the user answer wrong update his score using the {@code model} component.
     * @param model instance of {@code Model} class
     * @see Gamemode
     */
    @Override
    public void actionIfWrongAnswer(Model model, int playerIndex) {
        correctAnswersOfPlayerIndex.put(playerIndex, 0);
    }
}
