package backend.questions;

import backend.general.Category;
import backend.general.Difficulty;
import backend.general.Questionable;

import javax.swing.*;
import java.util.List;

/**
 * This class represents a question associated with an image and 4 possible answers, with only one of them being correct,
 * a category and a difficulty.
 * @author Tasos Papadopoulos
 * @version 10.11.2020
 * */
public class ImagedQuestion extends Question {
    private final ImageIcon image;

    /**
     * Constructs a new question with an image as content.
     * @param questionText the {@code String} that will be showed to the player as the question
     * @param correctAnswer the {@code String} that is the correct answer
     * @param answers the {@code List<String>} that contains the possible answers
     * @param difficulty the {@code Difficulty} of the question
     * @param category the {@code Category} of the question
     * @param image the {@code ImageIcon} associated with the question's image
     * */
    public ImagedQuestion(String questionText, String correctAnswer, List<String> answers, Difficulty difficulty, Category category, ImageIcon image) {
        super(questionText,correctAnswer,answers,difficulty,category);
        this.image = image;
    }

    /**
     * @see Questionable
     * */
    @Override
    public boolean hasContent() {
        return true;
    }

    /**
     * @see Questionable
     * */
    @Override
    public void showContent() {
        System.out.println(image.getDescription());
    }
}
