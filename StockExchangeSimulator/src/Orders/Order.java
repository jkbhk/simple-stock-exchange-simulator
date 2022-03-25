package Orders;

import java.math.BigDecimal;
import java.util.UUID;

public class Order {

    public static enum TYPE {

        BUY("BUY"), SELL("SELL");

        private String value;

        public String getValue() {
            return this.value;
        }

        private TYPE(String value) {
            this.value = value;
        }
    }

    protected String orderID;
    protected int filled;
    protected int requested;
    protected String counter;
    protected BigDecimal limit;
    protected boolean isMarketOrder;
    protected TYPE orderType;

    public Order(String counter, TYPE orderType, int requested, BigDecimal limit) {
        this.orderID = UUID.randomUUID().toString();
        this.filled = 0;
        this.requested = requested;
        this.orderType = orderType;
        this.counter = counter;
        this.limit = limit;
        this.isMarketOrder = (limit == null);
    }

    public Boolean isMarketOrder() {
        return this.isMarketOrder;
    }

    public String getCounterName() {
        return this.counter;
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

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal bd) {
        this.limit = bd;
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
        return isMarketOrder ? counter + " MKT " + orderType.getValue() + " " + getStatus()
                : counter + " LMT " + orderType.getValue() + " $" + limit + " " + getStatus();
    }

}
