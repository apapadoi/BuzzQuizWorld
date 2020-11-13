package model.questions;

import java.util.List;

/**
 * This class represents an abstract question entity.
 * @author Tasos Papadopoulos
 * @version 10.11.2020
 * */
public abstract class Question implements Questionable {
    protected final String questionText;
    protected final String correctAnswer;
    protected final List<String> answers;
    protected final Difficulty difficulty;
    protected final Category category;

    /**
     * Used to help construct a new type of question from a class that extends this abstract class.
     * @param questionText the {@code String} that will be showed to the player as the question
     * @param correctAnswer the {@code String} that is the correct answer
     * @param answers the {@code List<String>} that contains the possible answers
     * @param difficulty the {@code Difficulty} of the question
     * @param category the {@code Category} of the question
     * */
    public Question(String questionText,String correctAnswer,List<String> answers,Difficulty difficulty,Category category) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.answers = answers;
        this.difficulty = difficulty;
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
