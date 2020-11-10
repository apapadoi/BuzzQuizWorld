package backend.general;

/**
 * This enum represents the available categories of questions in the game.
 * @author Tasos Papadopoulos
 * @version 10.11.2020
 * */
public enum Category {
    Geography,
    History,
    Art,
    ScienceAndNature{
        @Override
        public String toString() {
            return "Science And Nature";
        }
    },
    Sports,
    Entertainment
}
