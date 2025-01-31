package parser;

import commands.*;
import exceptions.InvalidCommandException;
import task.TaskType;

public class UserInput {
    String input;
    public UserInput(String input) {
        this.input = input;
    }
    //parse to return Command
    public Command parse() throws InvalidCommandException {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidCommandException("Input Cannot Be Empty");
        }
        String[] tokens = input.split("\\s+");
        String commandWord = tokens[0];
        switch (commandWord) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "mark":
            if (tokens.length != 2) {
                throw new InvalidCommandException("Invalid Mark Command");
            }
            return new MarkCommand(Integer.parseInt(tokens[1]) - 1, true);
        case "unmark":
            if (tokens.length != 2) {
                throw new InvalidCommandException("Invalid Unmark Command");
            }
            return new MarkCommand(Integer.parseInt(tokens[1]) - 1, false);
        case "delete":
            if (tokens.length != 2) {
                throw new InvalidCommandException("Invalid Delete Command");
            }
            return new DeleteCommand(Integer.parseInt(tokens[1]) - 1);
        case "find":
            if (tokens.length != 2) {
                throw new InvalidCommandException("Invalid Find Command");
            }
            return new FindCommand(tokens[1]);
        case "todo":
            String todoDescription = input.substring(input.indexOf(" ") + 1);
            return new AddCommand(TaskType.TODO, todoDescription);
        case "deadline":
            String deadlineDescription = input.substring(input.indexOf(" ") + 1);
            return new AddCommand(TaskType.DEADLINE, deadlineDescription);
        case "event":
            String eventDescription = input.substring(input.indexOf(" ") + 1);
            return new AddCommand(TaskType.EVENT, eventDescription);
        default:
            throw new InvalidCommandException("Invalid/Undefined command: " + commandWord);
        }
    }
}
