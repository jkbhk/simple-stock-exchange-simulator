package Commands;

public class QuitCommand implements ICommand {

    @Override
    public void execute(String[] args) {
        System.exit(0);
    }

    @Override
    public String getCommandName() {
        return "QUIT";
    }

}
