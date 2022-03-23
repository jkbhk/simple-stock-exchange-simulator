import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {

        CounterManager counterManager = new CounterManager(
            new Counter("FB", 211.49),
            new Counter("SNAP", 35.17)
        );

        SimulatorDisplay window = new SimulatorDisplay();

        FakeRealtimePrice priceThread = new FakeRealtimePrice(window);
        priceThread.start();

        InputManager inputManager = new InputManager(
            window,   
            new BuyCommand(),
            new QuitCommand()
        );

        inputManager.stopReading();
        
        

    }

}
