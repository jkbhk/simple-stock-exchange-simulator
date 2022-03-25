package Exchange;

import java.math.BigDecimal;

import Orders.Order;
import Orders.OrderBook;

public class Counter {

    private String name;
    private OrderBook orderBook;

    public Counter(String name, OrderBook orderBook) {
        this.name = name;
        this.orderBook = orderBook;
    }

    public String getName() {
        return this.name;
    }

    public OrderBook getOrderBook() {
        return this.orderBook;
    }

}
