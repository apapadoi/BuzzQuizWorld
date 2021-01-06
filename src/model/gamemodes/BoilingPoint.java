package model.gamemodes;

import model.Model;
import java.util.HashMap;

public class BoilingPoint extends Gamemode{
    private static final HashMap<Integer, Integer> correctAnswersOfPlayerIndex = new HashMap<>();

    /**
     * Default Constructor.
     */
    public BoilingPoint() {
        super("First player that answers correct 5 questions earns 5000 points", 15);
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


    @Override
    public void actionIfCorrectAnswer(Model model, int millis, int playerIndex) {
        correctAnswersOfPlayerIndex.put(playerIndex, correctAnswersOfPlayerIndex.get(playerIndex)+1);

        if(correctAnswersOfPlayerIndex.get(playerIndex)==5) {
            model.updateScore(5000, playerIndex);
            for(int i=0;i<Model.getInstance().getMaxPlayers();i++)
                correctAnswersOfPlayerIndex.put(i, 0);
        }
    }

    @Override
    public void actionIfWrongAnswer(Model model, int playerIndex) {
        correctAnswersOfPlayerIndex.put(playerIndex, 0);
    }
}
