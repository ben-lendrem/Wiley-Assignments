package com.testing;

import com.exceptions.CustomException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.stream.Stream;


public class TestMain {
    public static void main(String[] args) {
        testComparator();
        //testDoubleDivisionModulo();
    }

    public static void testComparator() {
        Comparator<Coins> c = Comparator.comparingDouble(a -> a.getVal().doubleValue());
        c = c.reversed(); //sorts from highest denomination to lowest
        TreeMap<Coins, Integer> numCoins = new TreeMap<Coins, Integer>(c);
        numCoins.put(Coins.TWO_POUND, 1);
        numCoins.put(Coins.ONE_POUND, 2);
        numCoins.put(Coins.FIFTY_PENCE, 3);
        numCoins.put(Coins.TWENTY_PENCE, 4);
        numCoins.put(Coins.TEN_PENCE, 5);
        numCoins.put(Coins.FIVE_PENCE, 6);
        numCoins.put(Coins.TWO_PENCE, 7);
        numCoins.put(Coins.ONE_PENCE, 8);
        numCoins.put(Coins.TWO_POUND, 87);

        Stream.of(numCoins.values()).forEach((n) -> {
            System.out.println(n);
        });
    }

    public static void testDoubleDivisionModulo() {
        BigDecimal x = new BigDecimal(0.01);
        BigDecimal y = new BigDecimal(0.01);
        BigDecimal division = x.divide(y, 0, RoundingMode.FLOOR);
        BigDecimal modulo = x.remainder(y).round(new MathContext(3));
        System.out.println(division);
        System.out.println(modulo);
        System.out.println(modulo.doubleValue() < 0.001);
    }

}
