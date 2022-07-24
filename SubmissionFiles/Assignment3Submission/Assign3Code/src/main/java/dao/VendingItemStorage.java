package dao;

import dto.Change;
import dto.Item;
import dto.Transaction;
import errors.InvalidInputException;
import errors.NoItemInventoryException;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Scanner;

public class VendingItemStorage implements FileStorage {

    private ArrayList<Item> allItems;

    private String filepath;

    public VendingItemStorage(String filepath) throws FileNotFoundException {
        this.filepath = filepath;
        allItems = new ArrayList<>();
        LoadFromFile();
    }

    //finalize deprecated so make sure to call SaveToFile before program termination


    public ArrayList<Item> getAllItems() {
        return allItems;
    }

    public Transaction VendItem(int idIn, BigDecimal moneyIn) throws RuntimeException {
        int lowestId = allItems.get(0).getId();
        int highestId = allItems.get(allItems.size() - 1).getId();
        if (idIn < lowestId || idIn > highestId) {
            throw new InvalidInputException("The item requested is not in the Vend-O-Matic!");
        }
        //find the item that matches the id passed in
        Item vendedItem = new Item();
        for (Item current : allItems) {
            if (idIn == current.getId()) {
                vendedItem = current;
            }
        }
        //check that the item has been found (i.e. id is not default value)
        if (vendedItem.getId() == -1) {
            throw new InvalidInputException("The item requested is not in the Vend-O-Matic!");
        }
        //check that the item has stock
        int currentStock = vendedItem.getStock();
        if (currentStock <= 0) {
            throw new NoItemInventoryException("The item requested is out of stock!");
        }


        //checking for insufficient funds done in view (could refactor but not enough time :( )
        //for here check that the transaction is successful
        BigDecimal moneyInRounded = moneyIn.setScale(2, RoundingMode.HALF_UP);
        BigDecimal vendedItemPriceRounded = vendedItem.getPrice().setScale(2, RoundingMode.HALF_UP);
        boolean transactionSuccessful = (moneyInRounded.compareTo(vendedItemPriceRounded) >= 0);

        //decrement stock
        if (transactionSuccessful) {
            allItems.get(idIn - 1).setStock(currentStock - 1); //Check for id is already done so id - 1 is safe
        }
        Change thisChange;
        try {
            thisChange = new Change(moneyInRounded.subtract(vendedItemPriceRounded));
        } catch (IllegalArgumentException e) {
            thisChange = new Change();
        }

        return new Transaction(transactionSuccessful, vendedItem, thisChange);

    }


    public void LoadFromFile() throws FileNotFoundException {
        Scanner fileIn = new Scanner(new BufferedReader(
                new FileReader(filepath)));
        //each line is an item format id, name, stock, price
        while (fileIn.hasNextLine()) {
            String[] current = fileIn.nextLine().split(",");
            //id - parse string to int
            int currentId = Integer.parseInt(current[0]);
            //name - leave as string
            //stock - parse string to int
            int currentStock = Integer.parseInt(current[2]);
            //price - parse string to BigDecimal
            BigDecimal currentPrice = new BigDecimal(current[3]);
            //round because bigdecimal precision errors is a fun feature of Java
            currentPrice = currentPrice.setScale(2, RoundingMode.HALF_UP); //CHANGED - CHECK IF STILL WORKS
            allItems.add(new Item(currentId, current[1], currentStock, currentPrice));
        }

        fileIn.close();

    }

    @Override
    public void SaveToFile() throws IOException {
        PrintWriter fileOut = new PrintWriter(new FileWriter(filepath));
        allItems.stream().forEach((n) -> {
            BigDecimal currentPrice = n.getPrice().setScale(2, RoundingMode.HALF_UP);
            String current = n.getId() + ","
                    + n.getiName() + ","
                    + n.getStock() + ","
                    + currentPrice;
            fileOut.println(current);
        });

        fileOut.flush();
        fileOut.close();
    }

    @Override
    public String toString() {
        String out = "Item list: \n";
        for (Item current : allItems) {
            out = out.concat(current.toString() + "\n");
        }
        return out;
    }
}
