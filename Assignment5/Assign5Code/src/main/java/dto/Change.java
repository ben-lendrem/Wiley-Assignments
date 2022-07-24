package dto;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Stream;

enum Coins {
    TWO_POUND(new BigDecimal("2.00")),
    ONE_POUND(new BigDecimal("1.00")),
    FIFTY_PENCE(new BigDecimal("0.50")),
    TWENTY_PENCE(new BigDecimal("0.20")),
    TEN_PENCE(new BigDecimal("0.10")),
    FIVE_PENCE(new BigDecimal("0.05")),
    TWO_PENCE(new BigDecimal("0.02")),
    ONE_PENCE(new BigDecimal("0.01"));

    private BigDecimal val;

    Coins(BigDecimal val) {
        this.val = val;
    }

    public BigDecimal getVal() {
        return val;
    }
}

public class Change {
    private TreeMap<Coins, Integer> numCoins;
    private BigDecimal decimalValue;

    public Change() {
        InitNumCoins();
        decimalValue = BigDecimal.ZERO;
    }

    public Change(BigDecimal valIn) throws IllegalArgumentException {
        InitNumCoins();
        if (valIn.compareTo(BigDecimal.ZERO) >= 0) {
            DecimalToChange(valIn);
            decimalValue = valIn;
        } else {
            throw new IllegalArgumentException("You cannot have a negative amount of change!");
        }
    }

    private void InitNumCoins() {
        //create comparator for treemap constructor
        Comparator<Coins> c = Comparator.comparingDouble(a -> a.getVal().doubleValue()); //currently lowest -> highest
        c = c.reversed(); //sorts from highest denomination to lowest
        numCoins = new TreeMap<>(c); //constructs treemap with keys sorted highest to lowest
        Stream.of(Coins.values()).forEach((n) -> {
            numCoins.put(n, 0);
        });
    }

    public void DecimalToChange(BigDecimal valIn) throws IllegalStateException {
        if (numCoins.isEmpty()) {
            throw new IllegalStateException("The Change variable has not been initialised yet!");
        }
        BigDecimal current = valIn;
        ArrayList<Coins> denominationValList = new ArrayList<>(Arrays.asList(Coins.values()));

        MathContext round3 = new MathContext(3);

        for (Coins n : denominationValList) {
            BigDecimal denominationVal = n.getVal().round(round3);
            if (current.compareTo(BigDecimal.ZERO) >= 0) {
                //division for coin num
                BigDecimal division = current.divide(denominationVal, 0, RoundingMode.FLOOR);

                //modulo replaces current for next iteration
                BigDecimal modulo = current.remainder(denominationVal).round(round3);

                //put divided value into numCoins
                numCoins.put(n, division.intValue());

                //replace current with modulo
                current = modulo;
            }
        }
    }

    public Map<Coins, Integer> getCoins() {
        return numCoins;
    }

    public BigDecimal getDecimalValue() {
        return decimalValue;
    }

    @Override
    public String toString() {
        String out = "Change : \n";
        for (Coins current : Coins.values()) {
            out = out.concat(current + ": " + numCoins.get(current).toString() + " | ");
        }
        out = out.concat("\n");
        return out;
    }
}
