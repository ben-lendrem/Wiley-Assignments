package basic.programming.concepts;

import java.util.Scanner;

public class HealthyHearts {
    public static void main(String[] args) {
        HealthyHearts driver = new HealthyHearts();
    }

    public HealthyHearts() {
        int age;

        Scanner input = new Scanner(System.in);
        int maxHeartRate;
        int[] heartZone = new int[2];
        System.out.printf("What is your age? ");
        age = input.nextInt();
        maxHeartRate = 220 - age;
        heartZone[0] = (int)((double)maxHeartRate * 0.5);
        heartZone[1] = (int)((double)maxHeartRate * 0.85);
        System.out.printf("\nYour maximum heart rate should be %d beats per minute\n" +
                "Your target HR zone is %d - %d beats per minute", maxHeartRate, heartZone[0], heartZone[1]);
    }
}
