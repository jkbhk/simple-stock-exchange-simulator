package Orders;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

import org.omg.CORBA.Request;

import Exchange.Counter;

public class OrderBook {

    Comparator<BuyOrder> buyOrderComparator = (a, b) -> {
        return a.getLimit().compareTo(b.getLimit());
    };

    Comparator<SellOrder> sellOrderComparator = (a, b) -> {
        return b.getLimit().compareTo(a.getLimit());
    };

    private HashMap<String, Order> orders = new HashMap<>();

    private PriorityQueue<BuyOrder> buyOrders = new PriorityQueue<>(buyOrderComparator);
    private PriorityQueue<SellOrder> sellOrders = new PriorityQueue<>(sellOrderComparator);

    private class OrderBookProcess extends Thread {

        @Override
        public void run() {

            while (true) {

                if (!buyOrders.isEmpty()) {
                    BuyOrder bo = buyOrders.peek();

                    while (bo.filled < bo.requested) {
                        if (!sellOrders.isEmpty()) {
                            SellOrder so = sellOrders.peek();
                            if (bo.getRemainingRequested() >= so.getRemainingRequested()) {
                                // complete one sell order
                                bo.fill(so.getRemainingRequested());
                                so.fill(so.getRemainingRequested());
                                sellOrders.poll();
                            } else {
                                // complete the buy order
                                bo.fill(bo.getRemainingRequested());
                                so.fill(bo.getRemainingRequested());
                                break;
                            }
                        }
                    }
                    buyOrders.poll();

                }
            }
        }

    }

    private OrderBookProcess orderBookProcess = new OrderBookProcess();

    public OrderBook() {
        // orderBookProcess.start();
    }

    public void printOrderBook() {
        for (Order o : orders.values()) {
            System.out.println(o.getDescription());
        }
    }

    // market buy
    public void addBuyOrder(Counter c, int amount) {
        BuyOrder bo = new BuyOrder(c, amount, null);
        bo.setMarketPriceSnapshot(c.getAsk());

        orders.put(bo.getID(), bo);

    }

    public void addLimitBuyOrder(Counter c, BigDecimal limit, int amount) {

    }

    public void addSellOrder(Counter c, int amount) {

    }

    public void addLimitSellOrder(Counter c, BigDecimal limit, int amount) {

    }

}
