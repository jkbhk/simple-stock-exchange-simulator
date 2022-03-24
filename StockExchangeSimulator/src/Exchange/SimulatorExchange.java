package Exchange;

import java.math.BigDecimal;
import java.util.HashMap;

import Orders.Order;
import Orders.OrderBook;

public class SimulatorExchange {

    public static SimulatorExchange instance;
    private HashMap<String, Counter> counters = new HashMap<>();
    private HashMap<String, Order> orders = new HashMap<>();

    public SimulatorExchange(String... names) {
        instance = this;

        // set up fake counters
        for (String n : names) {
            Counter temp = new Counter(n, new OrderBook());
            counters.put(n, temp);
        }
    }

    public boolean isValidCounter(String counter) {
        return counters.get(counter) != null;
    }

    public Counter getCounter(String name) {
        return counters.get(name);
    }

    public void recordOrder(Order o) {
        orders.put(o.getID(), o);
    }

    public void viewAllOrders() {

        for (Order o : orders.values()) {
            System.out.println(o.getDescription());
        }
    }

    // market buy
    public void buy(String counter, int amount) {

        Counter c = counters.get(counter);

        if (c != null) {
            c.getOrderBook().addBuyOrder(c, amount);
            String m = "You have placed a market order for " + amount + " " + counter + " shares.";
            System.out.println(m);
        } else
            System.out.println("counter does not exist");
    }

    // limit buy
    public void limitBuy(String counter, BigDecimal limit, int amount) {

        Counter c = counters.get(counter);

        if (c != null) {
            c.getOrderBook().addLimitBuyOrder(c, limit, amount);
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
            c.getOrderBook().addSellOrder(c, amount);
            String m = "You have placed a market order for " + amount + " " + counter + " shares.";
            System.out.println(m);
        } else
            System.out.println("counter does not exist");

    }

    // limit sell
    public void limitSell(String counter, BigDecimal limit, int amount) {
        Counter c = counters.get(counter);

        if (c != null) {
            c.getOrderBook().addLimitSellOrder(c, limit, amount);
            String m = "You have placed a limit sell order for " + amount + " " + counter + " shares at $" +
                    limit + " each.";
            System.out.println(m);
        } else
            System.out.println("counter does not exist");
    }

}
