package ui;

import dto.Item;
import dto.Transaction;
import errors.InsufficientFundsException;
import errors.InvalidInputException;
import input.Input;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Scanner;

public class VendingView {

    Scanner input;

    public VendingView() {
        //System.out.println("View constructor called!");
        input = new Scanner(System.in);
    }

    public void DisplayMenu(ArrayList<Item> allItems) {
        System.out.print("*************************\n\nWelcome to the Vend-O-Matic 3000!\n" +
                "Current items we stock:\n");
        allItems.stream().forEach((n) -> {
            System.out.println(n);
        });
    }

    public int GetMenuChoice() {
        System.out.print("\n1. Enter money\n" +
                "2. Leave the vending machine\n");
        int choice = -1;
        do {
            try {
                System.out.print("Enter your choice: ");
                choice = Input.getUserInt(input);
                if (choice < 1 || choice > 2) {
                    throw new InvalidInputException("The value entered should be either 1 or 2!");
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage() + "\nTry again!");
            }
        } while (choice < 0);
        return choice;
    }

    public BigDecimal GetUserMoney() {
        double out = -0.1;
        do {
            try {
                System.out.print("Please put money into the Vend-O-Matic: ");
                out = Input.getUserDouble(input);
                if (out < 0.0) {
                    throw new InvalidInputException("You can't put a negative amount of money into the Vend-O-Matic!");
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage() + "\nTry again!");
            }
        } while (out < 0.0);
        return new BigDecimal(out).setScale(2, RoundingMode.HALF_UP);
    }

    public int GetUserItem() {
        int id = -1;
        do {
            try {
                System.out.print("Please enter the id of the item you'd like to vend (refer to list above): ");
                id = Input.getUserInt(input);
                if (id < 0) {
                    throw new InvalidInputException("There are no items with a negative id!");
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage() + "\nTry again!");
            }
        } while (id < 0);
        return id;
    }

    public void OutputTransaction(Transaction transactionIn) throws InsufficientFundsException {
        //only scenario where we reach this point and the transaction is false is if there wasn't enough money
        if (transactionIn.isTransactionSuccessful()) { //print transaction successful, item name vended, display change
            System.out.print("Your choice of " + transactionIn.getItemVended().getiName() + " has been successful!\n" +
                    "Your change is displayed below: \n" + transactionIn.getChange() + "\n");
        } else { //display not enough money put in, transaction unsuccessful, throw insufficientfundsexception
            throw new InsufficientFundsException("Vending unsuccessful. Not enough funds deposited.\n" +
                    "Your money is being returned to you.\n");
        }
    }
}
