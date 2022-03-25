import Commands.*;
import Exchange.SimulatorExchange;

public class App {
    public static void main(String[] args) throws Exception {

        SimulatorExchange simulatorExchange = new SimulatorExchange(new String[] {
                "FB",
                "SNAP"
        });

        InputManager inputManager = new InputManager(new GenericCommand[] {
                new QuitCommand("QUIT"),
                new SellCommand("SELL"),
                new BuyCommand("BUY"),
                new ViewCommand("VIEW"),
                new QuoteCommand("QUOTE")
        });

        while (true) {
            inputManager.requestInput();
            simulatorExchange.update();
        }

    }

}
