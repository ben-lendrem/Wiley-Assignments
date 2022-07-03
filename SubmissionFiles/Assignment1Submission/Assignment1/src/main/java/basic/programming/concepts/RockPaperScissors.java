package basic.programming.concepts;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RockPaperScissors {

    /*
    main method - creates RPS object whose constructor handles execution of program
     */
    public static void main(String[] args) {
        RockPaperScissors driver = new RockPaperScissors();
    }

    public RockPaperScissors() {
        RunGame();
    }

    /*
    RunGame - Handles the execution of a game, with helper methods below
     */
    private void RunGame() {
        //Variable initialisation
        Scanner userInput = new Scanner(System.in);

        boolean playAgain = false;

        //User inputs number of rounds to be played, input validation included
        do {
            int numRounds = 0, playerWins = 0, computerWins = 0;

            try { //try-catch block to handle exceptions thrown due to user input
                System.out.println("Enter the number of rounds (1 to 10 inclusive):");
                numRounds = userInput.nextInt();

            } catch (InputMismatchException e) {
                e.printStackTrace();
                System.out.println("The inputted number of rounds was not an integer!");
                System.exit(1);

            }

            //check if valid number of rounds has been inputted
            if (numRounds < 1 || numRounds > 10) {
                System.out.println("You entered an invalid number of rounds!\n" +
                        "You were supposed to enter a number between 1 and 10.");
                System.exit(1); //end execution if number of rounds is invalid
            }

            //RPS rounds begin
            //counters for round results (used to evaluate overall winner)
            int pWins = 0;
            int cWins = 0;
            int ties = 0;
            /*
            Loop runs rounds and adjusts counters until all rounds have been played
             */
            for (int i = 1; i <= numRounds; ++i) {
                int result = PlayRPSRound(i);
                switch (result) {
                    case 0:
                        System.out.println("You won this round!");
                        ++pWins;
                        break;
                    case 1:
                        System.out.println("I won this round :)");
                        ++cWins;
                        break;
                    case 2:
                        System.out.println("It was a tie!!");
                        ++ties;
                        break;
                }
            }

            //Evaluation of who is the overall winner
            int overallWins = pWins - cWins;
            String winner = " ";
            if (overallWins > 0) {
                winner = "You win!";
            } else if (overallWins < 0) {
                winner = "I win!";
            } else {
                winner = "It's a tie!!!";
            }

            //Displaying the results and the winner
            System.out.printf("\nResult:\n" +
                    "Number of player wins: %d\n" +
                    "Number of computer wins: %d\n" +
                    "Number of ties: %d\n%s\n", pWins, cWins, ties, winner);

            //Does player want to play again? (Handled in helper method PlayAgain)
            playAgain = PlayAgain();

        } while (playAgain);
        //Once the player is done, say thanks and end game
        System.out.println("\nThanks for playing!");
        userInput.close();
    }

    /*
    PlayRPSRound - handles the execution of a single round,
    returns int corresponding to round outcome
     */
    private int PlayRPSRound(int roundNum) {
        Scanner playerInput = new Scanner(System.in);
        System.out.printf("\n********* Round %d *********\n" +
                "1. Rock\n" +
                "2. Paper\n" +
                "3. Scissors\n", roundNum);
        boolean validInputGiven = false;
        int playerChoice = -1;
        do {
            try {
                System.out.print("Make your choice (1, 2, or 3): ");
                playerChoice = playerInput.nextInt();
                if ((playerChoice < 4) && (playerChoice > 0)) {
                    validInputGiven = true;
                    String choiceStr = " ";
                    switch (playerChoice) {
                        case 1:
                            choiceStr = "rock";
                            break;
                        case 2:
                            choiceStr = "paper";
                            break;
                        case 3:
                            choiceStr = "scissors";
                            break;
                    }
                    System.out.printf("\nYou chose %s\n", choiceStr);
                } else {
                    System.out.println("\nThe inputted choice was not 1, 2, or 3!\n" +
                            "Try again!");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nThe inputted choice was not a number!\n" +
                        "Try again!");
                playerInput.next();
            }
        } while (!validInputGiven);

        int compChoice = (int) (Math.random() * 2) + 1;
        String choiceStr = " ";
        switch (compChoice) {
            case 1:
                choiceStr = "rock";
                break;
            case 2:
                choiceStr = "paper";
                break;
            case 3:
                choiceStr = "scissors";
                break;
        }
        System.out.printf("Computer chose....\n%s!\n", choiceStr);

        //evaluate the result and print result
        int outcome = -1;

        //evaluate using nested switch (Yuck)
        outcome = EvaluateResults(playerChoice, compChoice);

        /*
        int returned:
        0 - Player win
        1 - Computer win
        2 - Tie
         */
        return outcome;
    }


    /*
    EvaluateResults - nested switch statement to determine who wins a round of RPS,
    parameters are the ints corresponding to the player's and computer's choice
    returns int corresponding to the outcome of the RPS game
     */
    private int EvaluateResults(int pChoice, int cChoice) {
        /*
        int returned:
        0 - Player win
        1 - Computer win
        2 - Tie
         */
        switch (pChoice) {
            case 1:
                switch (cChoice) {
                    case 1:
                        return 2;
                    case 2:
                        return 1;
                    case 3:
                        return 0;
                }
            case 2:
                switch (cChoice) {
                    case 1:
                        return 0;
                    case 2:
                        return 2;
                    case 3:
                        return 1;
                }
            case 3:
                switch (cChoice) {
                    case 1:
                        return 1;
                    case 2:
                        return 0;
                    case 3:
                        return 2;
                }
        }
        return -1;
    }

    /*
    PlayAgain - handles user interaction to decide whether to play another game or not
     */
    private boolean PlayAgain() {
        Scanner input = new Scanner(System.in);
        int choice = -1;
        do {
            try {
                System.out.printf("\nDo you want to play again?\n" +
                        "Enter 1 for yes or 2 for no: ");
                choice = input.nextInt();
                System.out.println();
                if (choice == 1) {
                    return true;
                } else if (choice == 2) {
                    return false;
                } else {
                    System.out.println("You entered an invalid number! Follow the instructions.");
                    continue;
                }

            } catch (InputMismatchException e) {
                System.out.println("You didn't enter a number! Follow the instructions.");
                input.next();
            }
        } while (choice != 1 & choice != 2);

     return false;
    }
}