package parser;

import commands.*;
import exceptions.InvalidCommandException;
import task.TaskType;

/**
 * The UserInput class processes and interprets user input strings into corresponding Command objects.
 * It is designed to parse and convert raw input from the user into actionable commands
 * that can be executed within the task management application.
 *
 * It validates the input and ensures that the appropriate command is created or throws
 * an exception when the input is invalid or unrecognized. The parse method follows
 * specific command formats and rules for supported commands like `list`, `mark`, `unmark`,
 * `delete`, `todo`, `deadline`, and `event`.
 *
 * Responsibilities:
 * - Validate the user input for non-null and non-empty conditions.
 * - Parse the input string to detect and instantiate the appropriate Command object.
 * - Throw an exception if the command is invalid or incorrectly formatted.
 *
 * Supported Commands:
 * 1. list - Lists all tasks.
 * 2. bye - Exits the application.
 * 3. mark [index] - Marks a task as completed (0-indexed).
 * 4. unmark [index] - Marks a task as incomplete (0-indexed).
 * 5. delete [index] - Deletes a task from the list (0-indexed).
 * 6. todo [description] - Adds a ToDo task with the given description.
 * 7. deadline [description] - Adds a Deadline task with the given description.
 * 8. event [description] - Adds an Event task with the given description.
 *
 * Exceptions:
 * - Throws InvalidCommandException when the input is invalid, empty, or does not match
 *   any expected command pattern.
 */
public class UserInput {
    String input;

    public UserInput(String input) {
        this.input = input;
    }

    /**
     * Parses the user input and returns the corresponding Command object based on the provided input string.
     * This method uses the first word of the input to determine the type of command and any additional tokens
     * or substrings as parameters for the command's construction.
     *
     * The method expects the input string to be valid and non-empty. If the command is unrecognized, incomplete,
     * or if required parameters are missing, it throws an InvalidCommandException.
     *
     * Recognized commands include:
     * - "list": Returns a ListCommand.
     * - "bye": Returns an ExitCommand.
     * - "mark <task_index>": Returns a MarkCommand to mark a task as done.
     * - "unmark <task_index>": Returns a MarkCommand to mark a task as not done.
     * - "delete <task_index>": Returns a DeleteCommand to delete a task.
     * - "todo <description>": Returns an AddCommand to add a TODO task.
     * - "deadline <description>": Returns an AddCommand to add a deadline task.
     * - "event <description>": Returns an AddCommand to add an event task.
     *
     * @return The Command object corresponding to the user input.
     * @throws InvalidCommandException If the input command is invalid, undefined, or does not meet required formats.
     */
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
