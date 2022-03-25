package Orders;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.PriorityQueue;

public class OrderBook {

    // prioritize bigger buy prices
    Comparator<Order> buyOrderComparator = (a, b) -> {
        return b.getLimit().compareTo(a.getLimit());
    };

    // prioritize smaller sell prices
    Comparator<Order> sellOrderComparator = (a, b) -> {
        return a.getLimit().compareTo(b.getLimit());
    };

    private PriorityQueue<Order> buyOrders;
    private PriorityQueue<Order> sellOrders;
    private String associatedCounter;

    // lowest selling price
    private BigDecimal ask_price = new BigDecimal("0.00");
    // highest buying price
    private BigDecimal bid_price = new BigDecimal("0.00");
    // last traded price
    private BigDecimal last_price = new BigDecimal("0.00");

    public OrderBook(String name) {
        this.associatedCounter = name;
        buyOrders = new PriorityQueue<>(buyOrderComparator);
        sellOrders = new PriorityQueue<>(sellOrderComparator);
    }

    // currently configured for single threaded exchange
    public void update() {
        processOrders();
        updatePrices();
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

    public Order addBuyOrder(String counter, int amount) {
        Order bo = new Order(counter, Order.TYPE.BUY, amount, null);

        bo.limit = new BigDecimal(Double.MAX_VALUE).setScale(2);
        buyOrders.add(bo);
        return bo;
    }

    public Order addLimitBuyOrder(String counter, BigDecimal limit, int amount) {
        Order bo = new Order(counter, Order.TYPE.BUY, amount, limit);
        buyOrders.add(bo);
        return bo;
    }

    public Order addSellOrder(String counter, int amount) {
        Order so = new Order(counter, Order.TYPE.SELL, amount, null);

        so.limit = new BigDecimal("0.00");
        sellOrders.add(so);
        return so;
    }

    public Order addLimitSellOrder(String counter, BigDecimal limit, int amount) {
        Order so = new Order(counter, Order.TYPE.SELL, amount, limit);
        sellOrders.add(so);
        return so;
    }

    private void processOrders() {

        while (!buyOrders.isEmpty()) {
            Order bo = buyOrders.peek();

            while (bo.filled < bo.requested) {

                if (!sellOrders.isEmpty()) {

                    Order so = sellOrders.peek();

                    if (bo.getLimit().doubleValue() >= so.getLimit().doubleValue()) {

                        int toFill = 0;

                        if (bo.getRemainingRequested() >= so.getRemainingRequested()) {
                            // complete one sell order

                            toFill = so.getRemainingRequested();

                            bo.fill(toFill);
                            bo.addTransaction(new Transaction(associatedCounter, so.getLimit(), toFill));

                            so.fill(toFill);
                            so.addTransaction(new Transaction(associatedCounter, so.getLimit(), toFill));

                            last_price = new BigDecimal(so.getLimit().toString());
                            sellOrders.poll();
                        } else {
                            // complete the buy order

                            toFill = bo.getRemainingRequested();

                            so.fill(toFill);
                            so.addTransaction(new Transaction(associatedCounter, so.getLimit(), toFill));

                            bo.fill(toFill);
                            bo.addTransaction(new Transaction(associatedCounter, so.getLimit(), toFill));

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

}
