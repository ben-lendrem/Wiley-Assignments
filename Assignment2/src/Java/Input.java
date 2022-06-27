package Java;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
    public static String getUserString(Scanner sInput) throws RuntimeException {
        try {
            String out = sInput.nextLine();
            return out;
        } catch (InputMismatchException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static int getUserInt(Scanner iInput) {
        try {
            int out = iInput.nextInt();
            iInput.nextLine();
            return out;
        } catch (InputMismatchException e) {
            e.printStackTrace();
            throw e;
        }

    }
}
