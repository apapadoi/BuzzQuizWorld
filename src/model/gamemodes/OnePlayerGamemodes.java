package model.gamemodes;

/**
 * This enum represents the available gamemodes that can be played by one player.
 *
 * @author Tasos Papadopoulos
 * @version 17.11.2020
 */
public enum OnePlayerGamemodes {
    PointBuilder {
        @Override
        public String toString() {
            return "Point Builder";
        }
    },
    HighStakes {
        @Override
        public String toString() {
            return "High Stakes";
        }
    }
}
