package model.gamemodes;

import model.Model;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FastestFinger extends Gamemode {
    public FastestFinger() {
        super("The first player that answers correct wins 1000 points while the second one 500 points.");
    }

    @Override
    public String toString() {
        return "Fastest Finger";
    }

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
        for(Map.Entry<Integer,Integer> entry : model.getResponseTimes().entrySet())
            playerResponseTimeList.add(new PlayerResponseTimeEntry(entry.getKey(),entry.getValue()));

        playerResponseTimeList.sort((o1, o2) -> -1*Integer.compare(o1.getMsLeft(), o2.getMsLeft()));

        if(playerIndex==playerResponseTimeList.get(0).getPlayerIndex() && playerResponseTimeList.get(0).getMsLeft()!=0)
            model.updateScore(1000, playerIndex);
        else if(playerIndex==playerResponseTimeList.get(1).getPlayerIndex() && playerResponseTimeList.get(1).getMsLeft()!=0)
            model.updateScore(500, playerIndex);
    }

    @Override
    public void actionIfWrongAnswer(Model model, int playerIndex) {
        model.getResponseTimes().put(playerIndex, 0);
    }

    @Override
    public boolean hasTimer() {
        return true;
    }
}
