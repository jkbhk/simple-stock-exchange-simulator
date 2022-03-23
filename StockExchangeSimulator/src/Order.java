public class Order {
   
    public static enum STATUS{PENDING,PARTIAL,FILLED};

    private Counter counter;
    private int filled;
    private int requested;

    public Order(Counter c, int requested){
        this.counter = counter;
        this.filled = 0;
        this.requested = requested;
    }

    public int getRequested(){
        return this.requested;
    }

    public int getFilled(){
        return this.filled;
    }

    public void fill(int amount){
        amount -= requested;
        filled += amount;
    }
    
}
