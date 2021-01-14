package model.questions;

/**
 * This enum represents the available categories of questions in the game.
 * If a new category of question needs to be added, add it here and on the txt file containing the questions in
 * the same way as the other categories are saved.If the category consists of many words, {@code toString} method
 * must be overridden.
 * @author Tasos Papadopoulos
 * @version 9.1.2021
 * */
public enum Category {
    Food,
    Technology,
    Films,
    Science,
    HipHopArtists {
        /**
         * Returns the category as string.
         * @return the category as {@code String}
         */
        @Override
        public String toString() {
            return "Hip Hop Artists";
        }
    },
    HipHopGroups {
        /**
         * Returns the category as string.
         * @return the category as {@code String}
         */
        @Override
        public String toString() {
            return "Hip Hop Groups";
        }
    },
    Cars,
    Formula1 {
        /**
         * Returns the category as string.
         * @return the category as {@code String}
         */
        @Override
        public String toString() {
            return "Formula 1";
        }
    }
}
