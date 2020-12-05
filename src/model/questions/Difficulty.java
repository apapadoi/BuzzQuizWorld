package model.questions;

/**
 * This enum represents the available difficulties of questions in the game.
 * If a new difficulty of question needs to be added, add it here and on the txt file containing the questions in
 * the same way as the other difficulties are saved.If the difficulty consists of many words, {@code toString} method
 * must be overridden.
 * @author Tasos Papadopoulos
 * @version 10.11.2020
 * */
public enum Difficulty {
    Easy,
    Medium,
    Hard
}
