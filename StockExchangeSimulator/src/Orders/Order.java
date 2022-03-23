package Orders;

import java.util.UUID;

import Exchange.Counter;

public abstract class Order {

    public static enum STATUS {
        PENDING, PARTIAL, FILLED
    };

    protected String orderID;
    protected int filled;
    protected int requested;
    protected Counter counter;

    public Order(Counter c, int requested) {
        this.orderID = UUID.randomUUID().toString();
        this.filled = 0;
        this.requested = requested;
        this.counter = c;
    }

    public String getID() {
        return this.orderID;
    }

    public int getRequested() {
        return this.requested;
    }

    public int getFilled() {
        return this.filled;
    }

    public int fill(int amount) {

        int overflow = amount - (requested - filled);

        if (overflow > 0) {
            filled = requested;
            return overflow;
        } else {
            filled += amount;
            return 0;
        }

    }

    public int getRemainingRequested() {
        return requested - filled;
    }

    public boolean isFilled() {
        return filled == requested;
    }

    public String getStatus() {

        String m = "";

        if (filled == requested) {
            m = "FILLED";
        } else {
            if (filled == 0) {
                m = "PENDING";
            } else {
                m = "PARTIAL";
            }
        }

        return filled + "/" + requested + " " + m;
    }

    public String getDescription() {
        return "GENERIC ORDER DESCRIPTION";
    }

}
