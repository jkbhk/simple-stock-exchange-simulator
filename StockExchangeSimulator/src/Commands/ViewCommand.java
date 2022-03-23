package Commands;

import Exchange.SimulatorExchange;

public class ViewCommand implements ICommand {

    @Override
    public void execute(String[] args) {
        // for now, assume only works with "VIEW ORDERS"
        if (args.length < 1) {
            System.out.println("invalid VIEW command.");
            return;
        }
        if (args[0].equals("ORDERS")) {
            SimulatorExchange.instance.getOrderBook().printOrderBook();
        } else {
            System.out.println("invalid VIEW command.");
            return;
        }
    }

    @Override
    public String getCommandName() {
        return "VIEW";
    }

}
