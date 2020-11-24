package model.util;

import java.util.concurrent.TimeUnit;

/**
 * This class represents a timer for counting seconds that took a specific event to finish.
 *
 * @author Tasos Papadopoulos
 * @version 17.11.2020
 */
public class MyTimer implements Runnable {
    int secondsCounted;

    /**
     * Default Constructor.
     */
    public MyTimer() {
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
     * This method is called when the timer starts.
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
