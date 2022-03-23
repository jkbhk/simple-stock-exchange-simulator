package Exchange;

import java.math.BigDecimal;

import Orders.OrderBook;

public class SimulatorExchange {

    public static SimulatorExchange instance;

    private OrderBook orderBook;

    public SimulatorExchange() {
        instance = this;
        orderBook = new OrderBook();
    }

    public OrderBook getOrderBook() {
        return this.orderBook;
    }

}
