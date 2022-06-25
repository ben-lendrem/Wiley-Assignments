package Java;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
    public static String getUserString() throws RuntimeException {
        Scanner input = new Scanner(System.in);
        try {
            String out = input.nextLine();
            return out;
        } catch (InputMismatchException e) {
            e.printStackTrace();
            throw e;
        } finally {
            input.close();
        }
    }

    public static int getUserInt() {
        Scanner input = new Scanner(System.in);
        try {
            int out = input.nextInt();
            return out;
        } catch (InputMismatchException e) {
            e.printStackTrace();
            throw e;
        } finally {
            input.close();
        }
    }
}
