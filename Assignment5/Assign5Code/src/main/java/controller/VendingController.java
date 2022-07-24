package controller;

import dao.VendingAuditStorage;
import dao.VendingItemStorage;
import dto.Transaction;
import ui.VendingView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

public class VendingController {

    private VendingView view;

    private VendingItemStorage itemStore;

    private VendingAuditStorage auditStore;

    //change the constructor to take in view and storage objects as parameters
    public VendingController(VendingView viewIn, VendingItemStorage itemIn, VendingAuditStorage auditIn) {
        //System.out.println("Controller constructor called!");
        /*sout lines like the above one are dotted around; I was experimenting and looking at the order of construction
        when using Spring DI
         */
        view = viewIn;
        itemStore = itemIn;
        auditStore = auditIn;
    }

    public void RunVendingMachine() {
        //Storage - Load Stock from file (now done in constructor)

        //do while exited is false
        boolean exited = false;

        do {
            //View - display menu
            view.DisplayMenu(itemStore.getAllItems());
            //View - get menu option (exit program or enter money)
            int menuChoice = view.GetMenuChoice();
            switch (menuChoice) {
                case 1:
                    //View - Get user inputted money
                    BigDecimal moneyIn = view.GetUserMoney();

                    //View - Get user inputted item choice
                    int idIn = view.GetUserItem();


                    Transaction thisTransaction;
                    //Storage - Vend Item
                    try {
                        thisTransaction = itemStore.VendItem(idIn, moneyIn);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage() + "\nReturning to menu...\n\n");
                        break;
                    }


                    //View - Output Transaction
                    try {
                        view.OutputTransaction(thisTransaction);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage() + "\nReturning to menu...\n\n");
                        break;
                    }


                    //Storage - Store Transaction audit
                    if (thisTransaction.isTransactionSuccessful()) {
                        auditStore.StoreTransaction(thisTransaction);
                    }
                    break; //go back to menu
                case 2:
                    exited = true;
                    break;
            }
        } while (!exited);

        //when done, save new stock and save audit
        try {
            System.out.println("Saving to file...");
            itemStore.SaveToFile();
            auditStore.SaveToFile();
            System.out.println("Saving done!");
        } catch (IOException e) {
            System.out.println("Something went wrong!!!!");
        }
    }

}
