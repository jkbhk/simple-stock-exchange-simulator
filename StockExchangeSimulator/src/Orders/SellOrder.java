package Orders;

import java.math.BigDecimal;

import Exchange.Counter;

public class SellOrder extends Order {

    private BigDecimal limit;
    private BigDecimal marketPriceSnapshot;

    public SellOrder(Counter c, int requested, BigDecimal limit) {
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
                ? (counter.getName() + "MKT SELL $" + marketPriceSnapshot.toString() + " " + getStatus())
                : (counter.getName() + "LMT SELL $" + limit.toString() + " " + getStatus());
    }

}
