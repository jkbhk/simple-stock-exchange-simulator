public class OrderBook {

   OrderThread thread = new OrderThread();

    public OrderBook(){
        thread.start();
    }
}
