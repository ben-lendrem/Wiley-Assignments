package dto;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ChangeTest {

    /*
    Testing the constructor for Change
    TestCase 1: 0.00 (zero value)
    TestCase 2: 0.01 (smallest denomination value)
    TestCase 3: 0.02 (smallest difference between two non-zero values)
    TestCase 4: 3.88 (test case with value of 1 for all denominations
    TestCase 5: 17.36 (random value)
    TestCase 6: -0.11 (negative value)
     */

    @org.junit.jupiter.api.Test
    void TestChangeConstructor() {
        Change test1 = new Change(new BigDecimal(0));
        Change test2 = new Change(new BigDecimal(0.01));
        Change test3 = new Change(new BigDecimal(0.02));
        Change test4 = new Change(new BigDecimal(3.88));
        Change test5 = new Change(new BigDecimal(17.36));

        Change test6 = new Change();
        boolean errorThrown = false;
        try {
            test6 = new Change(new BigDecimal(-0.11)); //should throw illegalArgumentException
        } catch (IllegalArgumentException e) {
            errorThrown = true;
        }

        //initialise a null treemap
        Comparator<Coins> c = Comparator.comparingDouble(a -> a.getVal().doubleValue());
        c = c.reversed();
        TreeMap<Coins, Integer> nullCoins = new TreeMap<Coins, Integer>(c);
        Stream.of(Coins.values()).forEach((n) -> {
            nullCoins.put(n,0);
        });

        //create treemaps to test cases against

        TreeMap<Coins, Integer> expectedMap1 = new TreeMap<>();
        TreeMap<Coins, Integer> expectedMap2 = new TreeMap<>();
        TreeMap<Coins, Integer> expectedMap3 = new TreeMap<>();
        TreeMap<Coins, Integer> expectedMap4 = new TreeMap<>();
        TreeMap<Coins, Integer> expectedMap5 = new TreeMap<>();

        //expectedMap1
        Stream.of(Coins.values()).forEach((n) -> {
            expectedMap1.put(n,0);
        });
        //expectedMap2
        Stream.of(Coins.values()).forEach((n) -> {
            expectedMap2.put(n,0);
        });
        expectedMap2.put(Coins.ONE_PENCE, 1);
        //expectedMap3
        Stream.of(Coins.values()).forEach((n) -> {
            expectedMap3.put(n,0);
        });
        expectedMap3.put(Coins.TWO_PENCE, 1);
        //expectedMap4
        Stream.of(Coins.values()).forEach((n) -> {
            expectedMap4.put(n,1);
        });
        //expectedMap5 should be 8,1,0,1,1,1,0,1
        Stream.of(Coins.values()).forEach((n) -> {
            expectedMap5.put(n,0);
        });
        expectedMap5.put(Coins.TWO_POUND , 8);
        expectedMap5.put(Coins.ONE_POUND , 1);
        expectedMap5.put(Coins.TWENTY_PENCE , 1);
        expectedMap5.put(Coins.TEN_PENCE , 1);
        expectedMap5.put(Coins.FIVE_PENCE , 1);
        expectedMap5.put(Coins.ONE_PENCE , 1);
        //expectedMap6 throws error and so doesn't matter

        //asserts
        assertEquals(expectedMap1, test1.getCoins());
        assertEquals(expectedMap2, test2.getCoins());
        assertEquals(expectedMap3, test3.getCoins());
        assertEquals(expectedMap4, test4.getCoins());
        assertEquals(expectedMap5, test5.getCoins());
        assertEquals(true, errorThrown);
    }


}