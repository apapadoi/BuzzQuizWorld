package backend.util;

import java.util.Scanner;

/**
 * This class contains useful static methods used by the app.
 * @author Tasos Papadopoulos
 * @version 10.11.2020
 * */
public class Util {

    public static int readIntInput() throws NumberFormatException{
        return Integer.parseInt(new Scanner(System.in).nextLine());
    }
}
