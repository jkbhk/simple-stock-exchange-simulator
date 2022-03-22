import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {

        SimulatorDisplay window = new SimulatorDisplay();

        CounterManager counterManager = new CounterManager(
            new Counter("FB", 211.49),
            new Counter("SNAP", 35.17)
        );

        FakeRealtimePrice priceThread = new FakeRealtimePrice(window);

        InputManager inputManager = new InputManager(
            window,   
            new BuyCommand(),
            new QuitCommand()
        );

        inputManager.stopReading();
        priceThread.start();
        

    }

}
