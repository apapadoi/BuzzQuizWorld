package backend.questions;

import java.util.List;

/**
 * This class represents a typical quiz question with just text and 4 possible answers, with only one of them being correct,
 * a category and a difficulty.
 * @author Tasos Papadopoulos
 * @version 9.11.2020
 * */
public class TextedQuestion implements Questionable{
    private String questionText;
    private String correctAnswer;
    private List<String> answers;
    private Difficulty difficulty;
    private Category category;

    /**
     * Constructs a new question associated with just text.
     * @param questionText the {@code String} that will be showed to the player as the question
     * @param correctAnswer the {@code String} that is the correct answer
     * @param answers the {@code List<String>} that contains the possible answers
     * @param difficulty the {@code Difficulty} of the question
     * @param category the {@code Category} of the question
     * */
    public TextedQuestion(String questionText, List<String> answers, String correctAnswer, Difficulty difficulty, Category category) {
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
