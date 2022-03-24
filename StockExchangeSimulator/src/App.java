import javax.jws.soap.InitParam;
import javax.swing.JFrame;

import Commands.*;
import Exchange.Counter;
import Exchange.CounterManager;
import Exchange.SimulatorExchange;

public class App {
    public static void main(String[] args) throws Exception {

        CounterManager counterManager = new CounterManager(
                new Counter("FB", 211.49),
                new Counter("SNAP", 35.17));

        // SimulatorDisplay window = new SimulatorDisplay();

        // FakeRealtimePrice priceThread = new FakeRealtimePrice(window);
        // priceThread.start();

        SimulatorExchange simulatorExchange = new SimulatorExchange();

        /*
         * InputManager inputManager = new InputManager(
         * null,
         * new BuyCommand(),
         * new SellCommand(),
         * new QuitCommand(),
         * new ViewCommand());
         * 
         */

        InputManager inputManager = new InputManager(
                null,
                new QuitCommand("QUIT"),
                new SellCommand("SELL"));

        inputManager.requestInput();

    }

}
