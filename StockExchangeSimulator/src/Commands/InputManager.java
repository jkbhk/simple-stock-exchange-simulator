package Commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class InputManager {

    public static InputManager instance;

    private IDisplayable displayable;

    private HashMap<String, ICommand> commandMap = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public InputManager(IDisplayable displayable, ICommand... commands) {

        instance = this;
        this.displayable = displayable;

        for (ICommand c : commands) {
            if (!c.getCommandName().equals(""))
                commandMap.put(c.getCommandName(), c);
        }
    }

    public void outputToDisplayable(String m) {
        if (displayable != null)
            displayable.displayMessage(m);
    }

    public void requestInput() {

        while (true) {
            System.out.println("enter command:");
            handleInput(scanner.nextLine());
        }

    }

    public void handleInput(String input) {

        if (input.equals("")) {
            return;
        }

        String[] parts = input.split(" ");

        if (parts.length > 1) {

            String commandType = parts[0];
            String key = parts[1];
            String args[] = Arrays.copyOfRange(parts, 2, parts.length);

            switch (commandType) {
                case "Action:":
                    if (commandMap.containsKey(key)) {
                        commandMap.get(key).execute(args);
                    }
                    break;
                default:
                    System.out.println("unknown command");
                    break;
            }
        }
    }

}
