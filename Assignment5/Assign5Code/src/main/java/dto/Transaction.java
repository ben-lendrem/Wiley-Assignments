package dto;

public class Transaction {
    private boolean transactionSuccessful;
    private Item itemVended;
    private Change change;


    public Transaction() { //default constructor for initialising empty object in controller
        transactionSuccessful = false;
        itemVended = new Item();
        change = new Change();
    }
    public Transaction(boolean transactionSuccessful, Item itemVended, Change change) {
        this.transactionSuccessful = transactionSuccessful;
        this.itemVended = itemVended;
        this.change = change;
    }

    public boolean isTransactionSuccessful() {
        return transactionSuccessful;
    }


    public Item getItemVended() {
        return itemVended;
    }


    public Change getChange() {
        return change;
    }

}
