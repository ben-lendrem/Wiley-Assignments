package input;

import java.util.InputMismatchException;
import java.util.Scanner;


/*
*****************************************
INPUT CLASS BROUGHT IN FROM ASSIGNMENT 2
*****************************************

Input class provides functions to handle user input, including the handling of InputMismatchExceptions

Scanner is included as parameter for the methods so that only a singer scanner object has to be used for the lifetime
of the program. The scanner object for the DVDLibrary is an attribute of the DVDLibrary class.
 */
public class Input {
    public static int getUserInt(Scanner iInput) throws RuntimeException {
        try {
            int out = iInput.nextInt();
            iInput.nextLine(); //issues when switching from Integer input to String input, this fixes the problem
            return out;
        } catch (InputMismatchException e) {
            iInput.next();
            throw new InputMismatchException("You were supposed to enter an integer!\n");
        }

    }

    public static double getUserDouble(Scanner dInput) throws RuntimeException {
        try {
            double out = dInput.nextDouble();
            dInput.nextLine();
            return out;
        } catch (InputMismatchException e) {
            dInput.next();
            throw new InputMismatchException("You were supposed to enter a double!\n");
        }
    }
}
