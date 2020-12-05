package model.util;

import java.util.concurrent.TimeUnit;

/**
 * This class represents a timer for counting seconds that took a specific event to finish. Objects of this class
 * behave as {@code Runnable} so it can be calculated how many seconds took the user to answer a question.
 * Creating a thread using an object of this class and calling {@code start} method calls the {@code run} method and starts the
 * timer.
 *
 * @author Tasos Papadopoulos
 * @version 28.11.2020
 */
public class UserAnswerTimer implements Runnable {
    int secondsCounted;

    /**
     * Default Constructor.
     */
    public UserAnswerTimer() {
        secondsCounted = 0;
    }

    /**
     * Method that returns the seconds the timer counted until the invoke was done as {@code int}
     *
     * @return int
     */
    public int getSecondsCounted() {
        return this.secondsCounted;
    }

    /**
     * Method that is called when a thread is created using an instance of this class and then thread's {@code start} method is called.
     * When invoked, it starts counting seconds.
     */
    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            secondsCounted++;
            if (Thread.currentThread().isInterrupted())
                return;
        }
    }
}
