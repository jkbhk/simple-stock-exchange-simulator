package Exchange;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import Orders.Order;
import Orders.OrderBook;

public class SimulatorExchange {

    public static SimulatorExchange instance;
    private HashMap<String, Counter> counters = new HashMap<>();

    // all orders of the exchange, can be changed to hash map if needs to be
    // searched
    private ArrayList<Order> orderList = new ArrayList<>();

    public SimulatorExchange(String[] names) {
        instance = this;

        // set up fake counters
        for (String n : names) {
            Counter temp = new Counter(n, new OrderBook(n));
            counters.put(n, temp);
        }
    }

    public void update() {
        // process all orderbook
        for (Counter c : counters.values()) {
            c.getOrderBook().update();
        }
    }

    public boolean isValidCounter(String counter) {
        return counters.get(counter) != null;
    }

    public Counter getCounter(String name) {
        return counters.get(name);
    }

    public void viewAllOrders() {

        int counter = 1;

        for (Order o : orderList) {
            System.out.println(counter + ". " + o.getDescription());
            counter++;
        }
    }

    // market buy
    public void buy(String counter, int amount) {

        Counter c = counters.get(counter);

        if (counters.get(counter) != null) {
            Order order = c.getOrderBook().addBuyOrder(counter, amount);
            orderList.add(order);

            String m = "You have placed a market order for " + amount + " " + counter + " shares.";
            System.out.println(m);
        } else
            System.out.println("counter does not exist");
    }

    // limit buy
    public void limitBuy(String counter, BigDecimal limit, int amount) {

        Counter c = counters.get(counter);

        if (c != null) {
            Order order = c.getOrderBook().addLimitBuyOrder(counter, limit, amount);
            orderList.add(order);

            String m = "You have placed a limit buy order for " + amount + " " + counter + " shares at $" +
                    limit + " each.";
            System.out.println(m);
        } else
            System.out.println("counter does not exist");
    }

    // market sell
    public void sell(String counter, int amount) {
        Counter c = counters.get(counter);

        if (c != null) {
            Order order = c.getOrderBook().addSellOrder(counter, amount);
            orderList.add(order);

            String m = "You have placed a market order for " + amount + " " + counter + " shares.";
            System.out.println(m);
        } else
            System.out.println("counter does not exist");

    }

    // limit sell
    public void limitSell(String counter, BigDecimal limit, int amount) {
        Counter c = counters.get(counter);

        if (c != null) {
            Order order = c.getOrderBook().addLimitSellOrder(counter, limit, amount);
            orderList.add(order);
            String m = "You have placed a limit sell order for " + amount + " " + counter + " shares at $" +
                    limit + " each.";
            System.out.println(m);
        } else
            System.out.println("counter does not exist");
    }

}
