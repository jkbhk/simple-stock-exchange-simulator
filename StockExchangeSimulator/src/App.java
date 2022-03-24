import javax.jws.soap.InitParam;
import javax.swing.JFrame;

import Commands.*;
import Exchange.Counter;
import Exchange.SimulatorExchange;

public class App {
    public static void main(String[] args) throws Exception {

        // SimulatorDisplay window = new SimulatorDisplay();

        // FakeRealtimePrice priceThread = new FakeRealtimePrice(window);
        // priceThread.start();

        SimulatorExchange simulatorExchange = new SimulatorExchange("FB", "SNAP");

        InputManager inputManager = new InputManager(
                null,
                new QuitCommand("QUIT"),
                new SellCommand("SELL"),
                new BuyCommand("BUY"),
                new ViewCommand("VIEW"),
                new QuoteCommand("QUOTE"));

        inputManager.requestInput();

    }

}
