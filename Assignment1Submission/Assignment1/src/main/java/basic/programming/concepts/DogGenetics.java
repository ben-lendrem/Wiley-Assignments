package basic.programming.concepts;

import java.util.Arrays;
import java.util.Scanner;

public class DogGenetics {

    public static void main(String[] args) {
        DogGenetics driver = new DogGenetics();
    }

    private int[] breedPercentages;
    private String[] breedNames;
    private int percentLeft;

    public DogGenetics() {
        percentLeft = 100;
        InitArrays();
        OpeningText();
        GenerateValues();
        DisplayValues();
    }

    private void InitArrays() {
        String[] temp = {"Bulldog", "Poodle", "Bichon Frise", "Siberian Husky", "Border Collie"};
        breedNames = Arrays.copyOf(temp, 5);
        breedPercentages = new int[5];
        Arrays.fill(breedPercentages, 0);
    }

    private void OpeningText() {
        System.out.printf("What is your dog's name? ");
        Scanner input = new Scanner(System.in);
        String dogName = input.nextLine();
        System.out.printf("\nWell then, I have this highly reliable report\n" +
                "on %s's prestigious background right here.\n\n" +
                "%s is:\n\n", dogName, dogName);
    }

    private void GenerateValues() {
        for (int i = 0; i < breedPercentages.length - 1; ++i) {
            breedPercentages[i] = (int) (Math.random() * percentLeft);
            percentLeft -= breedPercentages[i];
        }
        breedPercentages[4] = percentLeft;
    }

    private void DisplayValues() {
        for (int i = 0; i < breedPercentages.length; ++i) {
            System.out.printf("%s: %d\n", breedNames[i], breedPercentages[i]);
        }
        System.out.println("\n\nWow, that's QUITE the dog!");
    }


}
