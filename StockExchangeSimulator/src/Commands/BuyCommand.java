package Commands;

import java.math.BigDecimal;

import Exchange.SimulatorExchange;

public class BuyCommand extends GenericCommand {

    public BuyCommand(String name) {
        super(name);
    }

    @Override
    protected void handleCommand(String[] args) {

        String counter = args[0];
        String type = args[1];

        if (type.equals("LMT")) {

            if (!CommandUtils.isValidCurrencyAmount(args[2])) {
                System.out.println("invalid amount");
                return;
            }

            BigDecimal limit = CommandUtils.getAmountFromString(args[2]);
            if (limit.doubleValue() < 0.01) {
                System.out.println("invalid limit");
                return;
            }

            int amount = Integer.parseInt(args[3]);
            if (amount < 1) {
                System.out.println("invalid amount");
                return;
            }

            SimulatorExchange.instance.limitBuy(counter, limit, amount);

        } else if (type.equals("MKT")) {

            int amount = Integer.parseInt(args[2]);
            if (amount < 1) {
                System.out.println("invalid amount");
                return;
            }

            SimulatorExchange.instance.buy(counter, amount);

        } else {
            System.out.println("invalid BUY command");
            return;
        }
    }
}
