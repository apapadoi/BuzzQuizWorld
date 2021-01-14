package model.gamemodes;

import model.Model;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class represents the gamemode Fastest Finger. The first player that answers correct wins 1000 points while
 * the second one 500 points.
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 * @version 13.1.2021
 * */
public class FastestFinger extends Gamemode {
    /**
     * Default constructor.
     */
    public FastestFinger() {
        super("The first player that answers correct wins 1000 points while the second one 500 points.");
    }

    /**
     * @see Gamemode
     * @return the gamemode as {@code String}
     */
    @Override
    public String toString() {
        return "Fastest Finger";
    }

    /**
     * If the players answers correct update his score using the model component.
     * @see Gamemode
     */
    @Override
    public void actionIfCorrectAnswer(Model model, int playerIndex) {
        class PlayerResponseTimeEntry {
            private final int playerIndex;
            private final int msLeft;

            public PlayerResponseTimeEntry(int playerIndex, int msLeft) {
                this.playerIndex = playerIndex;
                this.msLeft = msLeft;
            }

            public int getPlayerIndex() {
                return playerIndex;
            }

            public int getMsLeft() {
                return msLeft;
            }
        }
        List<PlayerResponseTimeEntry> playerResponseTimeList = new ArrayList<>();
        for(Map.Entry<Integer,Integer> entry : model.getMillisLeft().entrySet())
            playerResponseTimeList.add(new PlayerResponseTimeEntry(entry.getKey(),entry.getValue()));

        playerResponseTimeList.sort((o1, o2) -> -1*Integer.compare(o1.getMsLeft(), o2.getMsLeft()));

        if(playerIndex==playerResponseTimeList.get(0).getPlayerIndex() && playerResponseTimeList.get(0).getMsLeft()!=0)
            model.addScore(1000, playerIndex);
        else if(playerIndex==playerResponseTimeList.get(1).getPlayerIndex() && playerResponseTimeList.get(1).getMsLeft()!=0)
            model.addScore(500, playerIndex);
    }

    /**
     * If the user answer wrong update his score using the {@code model} component.
     * @param model instance of {@code Model} class
     * @see Gamemode
     */
    @Override
    public void actionIfWrongAnswer(Model model, int playerIndex) {
        model.getMillisLeft().put(playerIndex, 0);
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
