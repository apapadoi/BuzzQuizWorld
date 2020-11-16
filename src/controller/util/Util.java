package controller.util;

import java.util.Scanner;

/**
 * This class contains useful static methods used by the app.
 * @author Tasos Papadopoulos
 * @version 16.11.2020
 * */
public class Util {

    public static int readIntInput() throws NumberFormatException {
        return Integer.parseInt(new Scanner(System.in).nextLine());
    }

    public static boolean isInsideLimits(int value,int lower,int upper) throws ArithmeticException{
        if(value >= lower && value <= upper) //checking if the user typed an integer that corresponds to a valid gamemode
            return true;

        throw new ArithmeticException();// if not then throw an arithmetic exception
    }
}
