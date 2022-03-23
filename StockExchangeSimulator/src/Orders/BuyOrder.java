package Orders;

import java.math.BigDecimal;

import Exchange.Counter;

public class BuyOrder extends Order {

    private BigDecimal limit;
    private BigDecimal marketPriceSnapshot;

    public BuyOrder(Counter c, int requested, BigDecimal limit) {
        super(c, requested);
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public BigDecimal getMarketPriceSnapshot() {
        return marketPriceSnapshot;
    }

    public void setMarketPriceSnapshot(BigDecimal marketPriceSnapshot) {
        this.marketPriceSnapshot = marketPriceSnapshot;
    }

    @Override
    public String getDescription() {
        return limit == null
                ? (counter.getName() + " MKT BUY $" + marketPriceSnapshot.toString() + " " + getStatus())
                : (counter.getName() + " LMT BUY $" + limit.toString() + " " + getStatus());
    }

}
