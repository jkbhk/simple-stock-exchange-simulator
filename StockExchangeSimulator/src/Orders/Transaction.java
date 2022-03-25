package Orders;

import java.math.BigDecimal;

public class Transaction {

    private String counter;
    private BigDecimal price;
    private int amount;

    public Transaction(String counter, BigDecimal price, int amount) {
        this.counter = counter;
        this.price = price;
        this.amount = amount;
    }

    public String getCounter() {
        return this.counter;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public int getAmount() {
        return this.amount;
    }
}
