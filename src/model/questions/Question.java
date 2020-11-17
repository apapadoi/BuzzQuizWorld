package model.questions;

import java.util.List;

/**
 * This class represents a question entity.
 *
 * @author Tasos Papadopoulos
 * @version 17.11.2020
 */
public class Question implements Questionable {
    protected String questionText;
    protected String correctAnswer;
    protected List<String> answers;
    protected Difficulty difficulty;
    protected Category category;

    /**
     * Default constructor.
     * */
    public Question() {
        questionText = "";
        correctAnswer = "";
        answers = null;
        difficulty = null;
        category = null;
    }

    /**
     * Used to help construct a new type of question from a class that extends this abstract class.
     * @param questionText the {@code String} that will be showed to the player as the question
     * @param correctAnswer the {@code String} that is the correct answer
     * @param answers the {@code List<String>} that contains the possible answers
     * @param difficulty the {@code Difficulty} of the question
     * @param category the {@code Category} of the question
     * */
    public Question(String questionText, String correctAnswer, List<String> answers, Difficulty difficulty, Category category) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.answers = answers;
        this.difficulty = difficulty;
        this.category = category;
    }

    /**
     * Setter for the question for the text attribute.
     * @param questionText The new question text.
     * */
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    /**
     * Setter for the question for the correct answer attribute.
     * @param correctAnswer The new correct answer.
     * */
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /**
     * Setter for the answers attribute.
     * @param answers The list of possible answers for the question as {@code List<String>}}
     * */
    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    /**
     * Setter for the difficulty attribute.
     * @param difficulty The difficulty of the question as {@code Difficulty}
     * */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Setter for the category attribute.
     * @param category The difficulty of the question as {@code Category}
     * */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * @see Questionable
     * */
    @Override
    public String getQuestionText() {
        return this.questionText;
    }

    /**
     * @see Questionable
     * */
    @Override
    public String getCorrectAnswer() {
        return this.correctAnswer;
    }

    /**
     * @see Questionable
     * */
    @Override
    public List<String> getAnswers() {
        return this.answers;
    }

    /**
     * @see Questionable
     * */
    @Override
    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    /**
     * @see Questionable
     * */
    @Override
    public Category getCategory() {
        return this.category;
    }

    /**
     * @see Questionable
     * */
    @Override
    public boolean hasContent() {
        return false;
    }

    /**
     * @see Questionable
     * */
    @Override
    public void showContent() {
    }
}
