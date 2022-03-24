package Commands;

import java.math.BigDecimal;

import Exchange.*;

public class SellCommand extends GenericCommand {

    public SellCommand(String name) {
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

            SimulatorExchange.instance.limitSell(counter, limit, amount);

        } else if (type.equals("MKT")) {

            int amount = Integer.parseInt(args[2]);
            if (amount < 1) {
                System.out.println("invalid amount");
                return;
            }

            SimulatorExchange.instance.sell(counter, amount);

        } else {
            System.out.println("invalid SELL command");
            return;
        }

    }

}
