package model.gamemodes;

import java.util.ArrayList;
import java.util.List;

/**
 * This enum represents the available gamemodes that can be played by two players.
 * @author Tasos Papadopoulos
 * @version 16.11.2020
 * */
public enum TwoPlayersGamemodes implements PlayableNumOfPlayersGamemode {
    PointBuilder{
        @Override
        public String toString() {
            return "Point Builder";
        }
    },
    StopTheClock{
        @Override
        public String toString() {
            return "Stop The Clock";
        }
    },
    HighStakes{
        @Override
        public String toString() {
            return "High Stakes";
        }
    },
    FastestFinger{
        @Override
        public String toString() {
            return "Fastest Finger";
        }
    },
    BoilingPoint{
        @Override
        public String toString() {
            return "Boiling Point";
        }
    };

    /**
     * @see PlayableNumOfPlayersGamemode
     * */
    public static List<String> getAvailableGamemodes() {
        List<String> availableGamemodes= new ArrayList<>(TwoPlayersGamemodes.values().length);
        for(PlayableNumOfPlayersGamemode currentGamemodeId : TwoPlayersGamemodes.values())
            availableGamemodes.add(currentGamemodeId.toString());

        return availableGamemodes;
    }
}
