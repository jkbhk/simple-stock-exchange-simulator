package Orders;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.PriorityQueue;

import Exchange.Counter;
import Exchange.SimulatorExchange;

public class OrderBook {

    Comparator<Order> buyOrderComparator = (a, b) -> {
        return b.getLimit().compareTo(a.getLimit());
    };

    Comparator<Order> sellOrderComparator = (a, b) -> {
        return a.getLimit().compareTo(b.getLimit());
    };

    private PriorityQueue<Order> buyOrders;
    private PriorityQueue<Order> sellOrders;

    // lowest selling price
    private BigDecimal ask_price = new BigDecimal("0.00");
    // highest buying price
    private BigDecimal bid_price = new BigDecimal("0.00");
    // last traded price
    private BigDecimal last_price = new BigDecimal("0.00");

    // currently configured for single threaded exchange
    public void update() {
        processOrders();
        updatePrices();
    }

    private void processOrders() {

        while (!buyOrders.isEmpty()) {
            Order bo = buyOrders.peek();

            while (bo.filled < bo.requested) {

                if (!sellOrders.isEmpty()) {

                    Order so = sellOrders.peek();

                    if (bo.getLimit().doubleValue() >= so.getLimit().doubleValue()) {

                        if (bo.getRemainingRequested() >= so.getRemainingRequested()) {
                            // complete one sell order
                            bo.fill(so.getRemainingRequested());
                            so.fill(so.getRemainingRequested());

                            last_price = new BigDecimal(so.getLimit().toString());
                            sellOrders.poll();
                        } else {
                            // complete the buy order
                            so.fill(bo.getRemainingRequested());
                            bo.fill(bo.getRemainingRequested());

                            last_price = new BigDecimal(so.getLimit().toString());
                            break;
                        }
                    } else {
                        // the smallest ask does not match the largest bid
                        return;
                    }

                } else {
                    // no sell orders to fufil the buy order at the moment
                    return;
                }
            }

            buyOrders.poll();
        }
    }

    private void updatePrices() {
        if (!sellOrders.isEmpty())
            ask_price = sellOrders.peek().getLimit();

        if (!buyOrders.isEmpty())
            bid_price = buyOrders.peek().getLimit();

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

    public OrderBook() {
        buyOrders = new PriorityQueue<>(buyOrderComparator);
        sellOrders = new PriorityQueue<>(sellOrderComparator);
    }

    // market buy
    public Order addBuyOrder(String counter, int amount) {
        Order bo = new Order(counter, Order.TYPE.BUY, amount, null);

        bo.limit = new BigDecimal(Double.MAX_VALUE).setScale(2);
        buyOrders.add(bo);
        return bo;
    }

    // limit buy
    public Order addLimitBuyOrder(String counter, BigDecimal limit, int amount) {
        Order bo = new Order(counter, Order.TYPE.BUY, amount, limit);
        buyOrders.add(bo);
        return bo;
    }

    // market sell
    public Order addSellOrder(String counter, int amount) {
        Order so = new Order(counter, Order.TYPE.SELL, amount, null);

        so.limit = new BigDecimal("0.00");
        sellOrders.add(so);
        return so;
    }

    // limit sell
    public Order addLimitSellOrder(String counter, BigDecimal limit, int amount) {
        Order so = new Order(counter, Order.TYPE.SELL, amount, limit);
        sellOrders.add(so);
        return so;
    }

}
