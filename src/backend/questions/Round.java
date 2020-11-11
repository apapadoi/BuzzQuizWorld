package backend.questions;

import java.util.Timer;

/**
 * This class represents a single round with the number of the rounds, a list of the questions that are going to be picked randomly, the current question id (contains
 * correct answer, 4 possible answers, the description and the difficulty) and the countdown timer.
 * @author Thodwrhs Myridis
 * @version 11.11.2020
 * */
public class Round {
    private int roundId;
    private List<Question> questions;
    private int currentQuestionId;
    private Timer countdownTimer;

    /**
     * Soon.
     * @param roundId the {@code int} that it is the current round
     * @param questions the {@code List<Question>} that it is the available questions
     * @param currentQuestionId the {@code int} that it is the current question's number id from enum "Questionable"
     * @param countdownTimer the {@code Time} that it is the countdown timer to every gamemode chosen
     * */

    /**
     * This method returns the current round
     * @return The current round as {@code int}
     * */
    public int getRoundId(){ return this.roundId;}

    /**
     * This method returns the question
     * @return The question as {@code Question}
     * */
    public Question getQuestion(){ return this.questions;}
}
