package Commands;

import Exchange.SimulatorExchange;

public class ViewCommand extends GenericCommand {

    public ViewCommand(String name) {
        super(name);
    }

    @Override
    protected void handleCommand(String[] args) {

        if (args[0].equals("ORDERS")) {
            SimulatorExchange.instance.viewAllOrders();
        } else {
            System.out.println("invalid VIEW command.");
            return;
        }

    }

}
