package backend.questions;

import java.util.List;

/**
 * This interface contains the methods that must be implemented by a class so it can be added as type of
 * question in the game.For example questions that have just text or questions with image or video etc.
 * @author Tasos Papadopoulos
 * @version 9.11.2020
 * */
public interface Questionable {
    /**
     * This method returns the question's text
     * @return The question's text as {@code String}
     * */
    String getQuestionText();

    /**
     * This method returns the question's correct answer
     * @return The correct answer as {@code String}
     * */
    String getCorrectAnswer();

    /**
     * This method returns the question's possible answers
     * @return The question's possible answers as {@code List<String>}
     * */
    List<String> getAnswers();

    /**
     * This method returns the question's difficulty
     * @return The question's difficulty as a value of enumerated type {@code Difficulty}
     * */
    Difficulty getDifficulty();

    /**
     * This method returns the question's category
     * @return The question's category as a value of enumerated type {@code Category}
     * */
    Category getCategory();

    /**
     * This method returns whether or not the question has content to show e.g image,video or even sound content
     * @return Whether the question has content or not as {@code boolean}
     * */
    boolean hasContent();

    /**
     * This method shows the question's content e.g with GUI or CLI depending the interface's client
     * */
    void showContent();
}
