package model.util;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Util {

    /**
     * Method that reads a string from the user and returns it as {@code String}.
     *
     * @return The string the user typed as {@code String}.
     */
    public static String readStringInput() {
        return new Scanner(System.in).nextLine();
    }

    /**
     * Method that checks if the {@code value} is inside the interval [{@code lower},{@code upper}]
     *
     * @param value The value we want to check if it inside the interval
     * @param lower The minimum value of the interval.
     * @param upper The maximum value of the interval.
     * @return True if the value is inside the interval.
     * @throws ArithmeticException if the value is not inside the interval
     */
    public static boolean isInsideLimits(int value, int lower, int upper){
        //checking if the user typed an integer that corresponds to a valid gamemode
        return value >= lower && value <= upper;
    }

    /**
     * Method that tries to read from the user an integer input and returns it as {@code int}.
     *
     * @return The integer the user typed as {@code int}
     * @throws NumberFormatException if the user did not type an integer at all.
     */
    public static int readIntInput() throws NumberFormatException {
        return Integer.parseInt(new Scanner(System.in).nextLine());
    }

    /**
     * Method that stops the execution for {@code seconds} seconds.
     *
     * @param seconds The amount of seconds we want to stop the execution.
     */
    public static void stopExecution(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}
