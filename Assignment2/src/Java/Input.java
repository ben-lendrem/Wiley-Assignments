package Java;

import java.util.InputMismatchException;
import java.util.Scanner;


/*
Input class provides functions to handle user input, including the handling of InputMismatchExceptions

Scanner is included as parameter for the methods so that only a singer scanner object has to be used for the lifetime
of the program. The scanner object for the DVDLibrary is an attribute of the DVDLibrary class.
 */
public class Input {
    public static String getUserString(Scanner sInput) throws RuntimeException {
        try {
            return sInput.nextLine();
        } catch (InputMismatchException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static int getUserInt(Scanner iInput) {
        try {
            int out = iInput.nextInt();
            iInput.nextLine(); //issues when switching from Integer input to String input, this fixes the problem
            return out;
        } catch (InputMismatchException e) {
            e.printStackTrace();
            throw e;
        }

    }
}
