package com.testing;

import java.math.BigDecimal;

enum Coins {
    TWO_POUND(new BigDecimal(2.00)),
    ONE_POUND(new BigDecimal(1.00)),
    FIFTY_PENCE(new BigDecimal(0.50)),
    TWENTY_PENCE(new BigDecimal(0.20)),
    TEN_PENCE(new BigDecimal(0.10)),
    FIVE_PENCE(new BigDecimal(0.05)),
    TWO_PENCE(new BigDecimal(0.02)),
    ONE_PENCE(new BigDecimal(0.01));

    private BigDecimal val;

    Coins(BigDecimal val) {
        this.val = val;
    }

    public BigDecimal getVal() { return val; }
}