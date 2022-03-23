package Exchange;

import java.math.BigDecimal;

public class Counter {

    private String name;
    private double price;
    private int pool;

    // lowest selling price
    private BigDecimal ask_price;
    // highest buying price
    private BigDecimal bid_price;
    // last traded price
    private BigDecimal last_price;

    public Counter(String name, Double price) {
        this.name = name;
        this.price = price;
        this.pool = Integer.MAX_VALUE;

        this.ask_price = new BigDecimal("0.00");
        this.bid_price = new BigDecimal("0.00");
        this.last_price = new BigDecimal("0.00");

        ;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double p) {
        this.price = p;
    }

    public BigDecimal getAsk() {
        return this.ask_price;
    }

    public BigDecimal getBid() {
        return this.bid_price;
    }

    public BigDecimal getlast() {
        return this.last_price;
    }

    public void incrementPrice(double amt) {
        this.price += amt;
    }

}
