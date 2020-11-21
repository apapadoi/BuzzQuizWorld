package model.round;

import model.questions.Category;
import model.questions.Difficulty;
import model.questions.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a single round with the number of the rounds, a list of the questions that are going to be picked randomly, the current question id (contains
 * correct answer, 4 possible answers, the description and the difficulty) and the countdown timer.
 *
 * @author Thodwrhs Myridis
 * @author Tasos Papadopoulos
 * @version 17.11.2020
 */
public class Round {
    private final List<Question> questions;

    /**
     * Default constructor.
     * */
    public Round() {
        questions = new ArrayList<>(5);

        Question currentQuestion = new Question();

        currentQuestion.setQuestionText("The famous computer Deep Blue was created by which company?");
        currentQuestion.setCorrectAnswer("IBM");
        currentQuestion.setCategory(Category.Science);
        currentQuestion.setDifficulty(Difficulty.Medium);

        List<String> answers = new ArrayList<>(4);
        answers.add("IBM");
        answers.add("Amazon");
        answers.add("Gateway");
        answers.add("Oracle");
        Collections.shuffle(answers);

        currentQuestion.setAnswers(answers);

        for (int i = 0; i < 5; i++) {
            questions.add(currentQuestion);
        }
    }

    /**
     * This method returns the questions
     *
     * @return The questions as {@code List<Question>}
     */
    public List<Question> getQuestions() {
        return this.questions;}
}
