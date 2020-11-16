package model.round;

import model.questions.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * This class represents a single round with the number of the rounds, a list of the questions that are going to be picked randomly, the current question id (contains
 * correct answer, 4 possible answers, the description and the difficulty) and the countdown timer.
 * @author Thodwrhs Myridis
 * @author Tasos Papadopoulos
 * @version 16.11.2020
 * */
public class Round {
    private int roundId;
    private List<Question> questions;
    private int currentQuestionId;
    private int countdownTimer;

    /**
     * @param roundId the {@code int} that it is the current round
     * @param questions the {@code List<Question>} that it is the available questions
     * @param currentQuestionId the {@code int} that it is the current question's number id from enum "Questionable"
     * @param countdownTimer the {@code Time} that it is the countdown timer to every gamemode chosen
     * */

    public Round() {
        roundId = 0;
        questions = new ArrayList<>(5);
        currentQuestionId = 0;
        countdownTimer = 45;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }



    /**
     * This method returns the current round
     * @return The current round as {@code int}
     * */
    public int getRoundId(){ return this.roundId;}

    /**
     * This method returns the question
     * @return The question as {@code Question}
     * */
    public List<Question> getQuestion(){ return this.questions;}
}
