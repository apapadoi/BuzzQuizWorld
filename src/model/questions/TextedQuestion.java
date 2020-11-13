package model.questions;

import java.util.List;

/**
 * This class represents a typical quiz question with just text and 4 possible answers, with only one of them being
 * correct,a category and a difficulty.
 * @author Tasos Papadopoulos
 * @version 10.11.2020
 * */
public class TextedQuestion extends Question {
    /**
     * Constructs a new question with just text as content.
     * @param questionText the {@code String} that will be showed to the player as the question
     * @param correctAnswer the {@code String} that is the correct answer
     * @param answers the {@code List<String>} that contains the possible answers
     * @param difficulty the {@code Difficulty} of the question
     * @param category the {@code Category} of the question
     * */
    public TextedQuestion(String questionText, String correctAnswer, List<String> answers, Difficulty difficulty,
                          Category category) {
        super(questionText, correctAnswer, answers, difficulty, category);
    }
}