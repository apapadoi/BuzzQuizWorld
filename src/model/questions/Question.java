package model.questions;

import javax.swing.*;
import java.util.List;
import java.util.Objects;

/**
 * This class represents a question with only a correct answer, a difficulty, a category and 3 other wrong answers.
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
     * Create a question with the information given.
     * @param questionText the {@code String} that will be showed to the player as the question
     * @param correctAnswer the {@code String} that is the correct answer
     * @param answers the {@code List<String>} that contains the possible answers
     * @param difficulty the {@code Difficulty} of the question
     * @param category the {@code Category} of the question
     * */
    public Question(String questionText, String correctAnswer, List<String> answers, Difficulty difficulty,
                    Category category) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.answers = answers;
        this.difficulty = difficulty;
        this.category = category;
    }

    /**
     * Setter for the question's text attribute.
     * @param questionText The new question text.
     * */
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    /**
     * Setter for the question's correct answer attribute.
     * @param correctAnswer The new correct answer.
     * */
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /**
     * Setter for the question's answers attribute.
     * @param answers The list of possible answers for the question as {@code List<String>}}
     * */
    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    /**
     * Setter for the question's difficulty attribute.
     * @param difficulty The difficulty of the question as {@code Difficulty}
     * */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Setter for the question's category attribute.
     * @param category The difficulty of the question as {@code Category}
     * */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * @see Questionable
     * @return the question's text as {@code String}
     * */
    @Override
    public String getQuestionText() {
        return this.questionText;
    }

    /**
     * @see Questionable
     * @return the question's correct answer as {@code String}
     * */
    @Override
    public String getCorrectAnswer() {
        return this.correctAnswer;
    }

    /**
     * @see Questionable
     * @return the question's possible answer as {@code List<String>}
     * */
    @Override
    public List<String> getAnswers() {
        return this.answers;
    }

    /**
     * @see Questionable
     * @return the question's difficulty as {@code Difficulty}
     * */
    @Override
    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    /**
     * @see Questionable
     * @return the question's category as {@code Category}
     * */
    @Override
    public Category getCategory() {
        return this.category;
    }

    /**
     * @see Questionable
     * @return Whether or not the question has content as {@code boolean}
     * */
    @Override
    public boolean hasContent() {
        return false;
    }

    @Override
    public ImageIcon getContent() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question = (Question) o;
        return Objects.equals(getQuestionText(), question.getQuestionText()) &&
                Objects.equals(getCorrectAnswer(), question.getCorrectAnswer()) &&
                getDifficulty() == question.getDifficulty() &&
                getCategory() == question.getCategory();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuestionText(), getCorrectAnswer(), getAnswers(), getDifficulty(), getCategory());
    }
}
