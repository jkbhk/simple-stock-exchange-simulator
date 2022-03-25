package Commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class InputManager {

    public static InputManager instance;

    private IDisplayable displayable;
    private HashMap<String, GenericCommand> commands = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public InputManager(IDisplayable displayable, GenericCommand... commands) {

        instance = this;
        this.displayable = displayable;

        for (GenericCommand c : commands) {
            if (!c.getCommandName().equals(""))
                this.commands.put(c.getCommandName(), c);
        }
    }

    public void outputToDisplayable(String m) {
        if (displayable != null)
            displayable.displayMessage(m);
    }

    public void requestInput() {
        System.out.println("enter command:");
        handleInput(scanner.nextLine());

    }

    public void handleInput(String input) {

        if (input.equals("")) {
            return;
        }

        String[] parts = input.split(" ");

        try {
            String commandType = parts[0];

            switch (commandType) {
                case "Action:":

                    String commandName = parts[1];

                    if (commands.containsKey(commandName)) {

                        String args[] = Arrays.copyOfRange(parts, 2, parts.length);
                        commands.get(commandName).tryExecute(args);
                    } else {
                        System.out.println("no such Action");
                    }
                    break;

                default:
                    System.out.println("invalid command");
                    break;
            }
        } catch (Exception e) {
            System.out.println("invalid command");
        }

    }

}
