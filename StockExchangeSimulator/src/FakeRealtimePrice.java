import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class FakeRealtimePrice extends Thread{

    IDisplayable displayable;

    public FakeRealtimePrice(IDisplayable d){
        this.displayable = d;
    }
    
    @Override
    public void run(){
        while(true){
            simulatePrice();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void simulatePrice(){
        HashMap<String, Counter> map  = CounterManager.instance.getCounters();

        for(Counter c : map.values()){
            
            int increase = ThreadLocalRandom.current().nextInt(1, 2 + 1);
            double amount = ThreadLocalRandom.current().nextDouble(1, 4 + 1);

            c.incrementPrice(increase % 2 == 0 ? amount : -amount);
            displayable.updatePrice();
        }
        
    }

    
}
