package Commands;

public abstract class GenericCommand {

    protected String commandName;

    public GenericCommand(String name) {
        this.commandName = name;
    }

    public void tryExecute(String[] args) {
        try {
            handleCommand(args);
        } catch (Exception e) {
            System.out.println("error running command");
        }
    }

    public String getCommandName() {
        return this.commandName;
    }

    protected abstract void handleCommand(String[] args);

}
