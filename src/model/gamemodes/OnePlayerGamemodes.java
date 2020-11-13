package model.gamemodes;

import java.util.ArrayList;
import java.util.List;

/**
 * This enum represents the available gamemodes that can be played by one player.
 * @author Tasos Papadopoulos
 * @version 10.11.2020
 * */
public enum OnePlayerGamemodes implements PlayableNumOfPlayersGamemode {
    PointBuilder{
        @Override
        public String toString() {
            return "Point Builder";
        }
    },
    /*
    Gamemode not available in this version.
    StopTheClock{
        @Override
        public String toString() {
            return "Stop The Clock";
        }
    },*/
    HighStakes {
        @Override
        public String toString() {
            return "High Stakes";
        }
    };

    /**
     * @see PlayableNumOfPlayersGamemode
     * */
    public List<String> getAvailableGamemodes() {
        List<String> availableGamemodes= new ArrayList<>(OnePlayerGamemodes.values().length);
        for(PlayableNumOfPlayersGamemode currentGamemodeId : OnePlayerGamemodes.values())
            availableGamemodes.add(currentGamemodeId.toString());

        return availableGamemodes;
    }
}
