package Commands;

import Exchange.*;

public class QuoteCommand extends GenericCommand {

    public QuoteCommand(String name) {
        super(name);
    }

    @Override
    protected void handleCommand(String[] args) {

        Counter c = SimulatorExchange.instance.getCounter(args[0]);
        if (c == null) {
            System.out.println("Counter does not exist");
            return;
        } else {
            System.out
                    .println(c.getName() + " BID: $" + c.getBid() + " ASK: $" + c.getAsk() + " LAST: $" + c.getlast());
        }
    }

}
