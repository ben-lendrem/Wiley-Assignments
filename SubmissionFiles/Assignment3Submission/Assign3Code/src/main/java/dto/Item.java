package dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Item {

    private int id;
    private String iName;
    private BigDecimal price;
    private int stock;

    public Item() {
        id = -1;
        iName = null;
        price = BigDecimal.ZERO;
        stock = -1;
    }

    public Item(int id, String iName, int stock, BigDecimal price) {
        this.id = id;
        this.iName = iName;
        this.price = price;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getiName() {
        return iName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Item id " + id + " { name: " + iName + " | " +
                "current stock: " + stock + " | " +
                "price: " + price.setScale(2, RoundingMode.HALF_UP) + " }";
    }
}
