package dao;

import dto.Transaction;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class VendingAuditStorage implements FileStorage {

    private ArrayList<Transaction> allTransactions;
    private ArrayList<LocalDateTime> allTimes; //could combine into a map with transactions, will see if separate works

    private String filepath;

    public VendingAuditStorage(String filepath) {
        allTransactions = new ArrayList<>();
        allTimes = new ArrayList<>();
        this.filepath = filepath;
    }

    public void StoreTransaction(Transaction transactionIn) {
        allTransactions.add(transactionIn);
        allTimes.add(LocalDateTime.now());
    }

    @Override
    public void SaveToFile() throws IOException {
        PrintWriter fileOut = new PrintWriter(new FileWriter(filepath, true));
        for (Transaction current : allTransactions) {
            LocalDateTime thisTime = LocalDateTime.MIN;
            if (!allTimes.isEmpty()) {
                thisTime = allTimes.get(0);
                allTimes.remove(0);
            }
            //dateTime formatting taken from https://www.w3schools.com/java/java_date.asp
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDate = thisTime.format(format);
            String entry = formattedDate + "," + current.getItemVended().getiName();
            fileOut.println(entry);
        }
        fileOut.flush();
        fileOut.close();
    }
}
