package Orders;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.PriorityQueue;

import Exchange.Counter;
import Exchange.SimulatorExchange;

public class OrderBook {

    Comparator<BuyOrder> buyOrderComparator = (a, b) -> {
        return b.getLimit().compareTo(a.getLimit());
    };

    Comparator<SellOrder> sellOrderComparator = (a, b) -> {
        return a.getLimit().compareTo(b.getLimit());
    };

    private PriorityQueue<BuyOrder> buyOrders;
    private PriorityQueue<SellOrder> sellOrders;

    private class OrderBookProcess extends Thread {

        @Override
        public void run() {

            while (true) {

                try {
                    System.out.println(getId() + " : " + buyOrders.size());
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block

                    e.printStackTrace();
                }

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

    private OrderBookProcess orderBookProcess;

    public void printTest() {
        System.out.println(buyOrders.peek().getLimit());
    }

    public OrderBook() {
        buyOrders = new PriorityQueue<>(buyOrderComparator);
        sellOrders = new PriorityQueue<>(sellOrderComparator);
        orderBookProcess = new OrderBookProcess();
        orderBookProcess.start();
    }

    // market buy
    public String addBuyOrder(Counter c, int amount) {
        BuyOrder bo = new BuyOrder(c, amount, null);
        bo.setMarketPriceSnapshot(c.getAsk());
        SimulatorExchange.instance.recordOrder(bo);
        return bo.getID();
    }

    // limit buy
    public String addLimitBuyOrder(Counter c, BigDecimal limit, int amount) {
        BuyOrder bo = new BuyOrder(c, amount, limit);
        SimulatorExchange.instance.recordOrder(bo);
        buyOrders.add(bo);

        System.out.println(buyOrders.size());
        return bo.getID();
    }

    // market sell
    public String addSellOrder(Counter c, int amount) {
        SellOrder so = new SellOrder(c, amount, null);
        so.setMarketPriceSnapshot(c.getAsk());
        SimulatorExchange.instance.recordOrder(so);

        return so.getID();
    }

    // limit sell
    public String addLimitSellOrder(Counter c, BigDecimal limit, int amount) {
        SellOrder so = new SellOrder(c, amount, limit);
        SimulatorExchange.instance.recordOrder(so);
        sellOrders.add(so);

        return so.getID();
    }

}
