package dao;

import dto.Transaction;
import errors.NoItemInventoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class VendingItemStorageTest {
    public VendingItemStorage testStore;

    @BeforeEach
    void setUp() {
        try {
            testStore = new VendingItemStorage("vendingItemsTest.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Error loading store");
            System.exit(1);
        }

    }

    @Test
    void vendItemTest() {
        /*
        Testing vend method:
        testcase 1: vend success
        testcase 2: vend failure (lack of stock)
        testcase 3: vend failure (lack of funds)
         */
        Transaction test1 = testStore.VendItem(1, new BigDecimal(3)); //0.01 change, success
        Transaction test2 = new Transaction();
        boolean noStockThrown = false;
        try {
            test2 = testStore.VendItem(2, new BigDecimal(3.50)); //failure, error thrown
        } catch (NoItemInventoryException e) {
            noStockThrown = true;
        }

        Transaction test3 = testStore.VendItem(3, new BigDecimal(4.76)); //failure, no error thrown

        assertEquals(true, test1.isTransactionSuccessful());
        assertEquals(0.01, test1.getChange().getDecimalValue().setScale(2, RoundingMode.HALF_UP).doubleValue());
        assertEquals(true, noStockThrown);
        assertEquals(false, test3.isTransactionSuccessful());

    }
}