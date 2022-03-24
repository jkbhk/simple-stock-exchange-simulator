package Commands;

import java.math.BigDecimal;

import Exchange.CounterManager;

public class SellCommand extends GenericCommand {

    public SellCommand(String name) {
        super(name);
    }

    @Override
    protected void handleCommand(String[] args) {

        String counter = args[0];
        String type = args[1];

        // check counter exists
        if (CounterManager.instance.getCounters().get(counter) == null) {
            System.out.println("Counter does not exist");
            return;
        }

        if (type.equals("LMT")) {

            if (!CommandUtils.isValidCurrencyAmount(args[2])) {
                System.out.println("invalid amount");
                return;
            }

            BigDecimal limit = CommandUtils.getAmountFromString(args[2]);
            if (limit.doubleValue() < 0) {
                System.out.println("amount cannot be negative");
                return;
            }

            int amount = Integer.parseInt(args[3]);

            String m = "You have placed a limit sell order for " + amount + " " + counter + " shares at $" +
                    limit + " each.";
            System.out.println(m);
            InputManager.instance.outputToDisplayable(m);

        } else if (type.equals("MKT")) {

            int amount = Integer.parseInt(args[2]);
            String m = "You have placed a market order for " + amount + " " + counter + " shares.";
            System.out.println(m);
            InputManager.instance.outputToDisplayable(m);

        } else {
            System.out.println("invalid SELL command");
            return;
        }

    }

}
