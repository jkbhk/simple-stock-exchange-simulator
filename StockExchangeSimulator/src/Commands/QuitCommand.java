package Commands;

public class QuitCommand extends GenericCommand {

    public QuitCommand(String name) {
        super(name);
    }

    @Override
    protected void handleCommand(String[] args) {
        System.exit(0);
    }

}
