package Orders;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
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

    public static enum STATUS {

        PENDING("PENDING"), PARTIAL("PARTIAL"), FILLED("FILLED");

        private String value;

        public String getValue() {
            return this.value;
        }

        private STATUS(String value) {
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
    protected STATUS orderStatus;

    protected ArrayList<Transaction> transactions = new ArrayList<>();

    public Order(String counter, TYPE orderType, int requested, BigDecimal limit) {
        this.orderID = UUID.randomUUID().toString();
        this.filled = 0;
        this.requested = requested;
        this.orderType = orderType;
        this.counter = counter;
        this.limit = limit;
        this.isMarketOrder = (limit == null);
        this.orderStatus = STATUS.PENDING;
    }

    public void addTransaction(Transaction t) {
        transactions.add(t);
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
            updateStatus();
            return overflow;
        } else {
            filled += amount;
            updateStatus();
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

    public String getRatio() {
        return filled + "/" + requested;
    }

    public STATUS getStatus() {
        return this.orderStatus;
    }

    public String getDescription() {

        if (isMarketOrder) {
            return isFilled()
                    ? counter + " MKT " + orderType.getValue() + " $" + calculateAverageTransaction() + " " + getRatio()
                            + " " + getStatus().getValue()
                    : counter + " MKT " + orderType.getValue() + " " + getRatio() + " " + getStatus().getValue();
        } else {
            return counter + " LMT " + orderType.getValue() + " $" + limit + " " + getRatio() + " " + getStatus();
        }
    }

    private void updateStatus() {
        if (filled == requested) {
            orderStatus = STATUS.FILLED;
        } else {
            if (filled == 0) {
                orderStatus = STATUS.PENDING;
            } else {
                orderStatus = STATUS.PARTIAL;
            }
        }
    }

    private BigDecimal calculateAverageTransaction() {

        BigDecimal total = new BigDecimal(BigInteger.ZERO, 2);
        BigDecimal divisor = new BigDecimal(BigInteger.ZERO, 0);

        for (Transaction t : transactions) {
            BigDecimal transactionCost = t.getPrice().multiply(BigDecimal.valueOf(t.getAmount()));
            total = total.add(transactionCost);
            divisor = divisor.add(BigDecimal.valueOf(t.getAmount()));
        }

        BigDecimal avg = total.divide(divisor, RoundingMode.HALF_UP);
        return avg.setScale(2);
    }

}
