import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import exceptions.InvalidCommandException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import iomanager.TasklistManager;

public class Brownie {
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String INVALID_INDEX_MESSAGE = "Invalid index. Please try again.";
    private static final String INPUT_CANNOT_BE_EMPTY = "Input cannot be empty. Please try again.";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String ITEM_ADDED_MESSAGE = "Added ";

    public static void main(String[] args) {
        System.out.println("Hello! I'm Brownie \nWhat can I do for you?");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        ArrayList<Task> items = new ArrayList<>();
        TasklistManager tasklistManager = new TasklistManager(writer, reader);
        tasklistManager.initializeTasklist();
        items = tasklistManager.loadTasksFromFile();

        while (true) {
            try {
                String input = reader.readLine();
                if (input == null || input.trim().isEmpty()) {
                    throw new InvalidCommandException(INPUT_CANNOT_BE_EMPTY);
                }
                if (input.equals(COMMAND_BYE)) {
                    output(writer, GOODBYE_MESSAGE);
                    break;
                }
                if (input.equals(COMMAND_LIST)) {
                    displayList(writer, items);
                } else if (input.startsWith("mark")) {
                    handleMarkCommand(writer, input, items, true, tasklistManager);
                } else if (input.startsWith("unmark")) {
                    handleMarkCommand(writer, input, items, false, tasklistManager);
                } else if (input.startsWith("delete")) {
                    handleDeleteCommand(writer, input, items, tasklistManager);
                } else {
                    processTaskInput(writer, input, items, tasklistManager);
                }
            } catch (InvalidCommandException e) {
                output(writer, e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        writer.close();
    }

    private static void displayList(PrintWriter writer, ArrayList<Task> items) {
        writer.println("Items in the list:");
        for (int i = 0; i < items.size(); i++) {
            writer.println((i + 1) + ". " + items.get(i));
        }
        writer.flush();
    }

    private static void handleMarkCommand(PrintWriter writer, String input, ArrayList<Task> items,
            boolean markAsDone, TasklistManager tasklistManager)  throws InvalidCommandException {
        int index = parseIndex(input.split(" "), items.size());
        if (index == -1) {
            output(writer, INVALID_INDEX_MESSAGE);
            return;
        }
        Task task = items.get(index);
        if (markAsDone) {
            task.markAsDone();
            output(writer, "Marked " + task + " as done.");
        } else {
            task.markAsUndone();
            output(writer, "Noted, " + task + " is not done.");
        }
        tasklistManager.saveTasksToFile(items);
    }

    private static void handleDeleteCommand(PrintWriter writer, String input, ArrayList<Task> items,
            TasklistManager tasklistManager) throws InvalidCommandException {
        int index = parseIndex(input.split(" "), items.size());
        if (index == -1) {
            output(writer, INVALID_INDEX_MESSAGE);
            return;
        }
        Task task = items.get(index);
        items.remove(index);
        output(writer, "Deleted " + task);
        tasklistManager.saveTasksToFile(items);
    }

    private static void processTaskInput(PrintWriter writer, String input, ArrayList<Task> items,
            TasklistManager tasklistManager) throws InvalidCommandException {
        String command = input.split(" ")[0];
        String description;
        Task taskToAdd;

        switch (command) {
        case "todo":
            description = input.substring(input.indexOf(" ") + 1);
            taskToAdd = new Todo(description);
            break;
        case "deadline":
            final String BY_DELIMITER = "by:";
            int byIndex = input.indexOf(BY_DELIMITER);
            if (byIndex == -1) {
                throw new InvalidCommandException("Invalid deadline. Please try again.");
            }
            description = input.substring(input.indexOf(" ") + 1, byIndex).trim();
            String deadline = input.substring(byIndex + BY_DELIMITER.length()).trim();
            taskToAdd = new Deadline(description, deadline);
            break;
        case "event":
            final String START_DELIMITER = "start:";
            final String END_DELIMITER = "end:";
            int startIndex = input.indexOf(START_DELIMITER);
            int endIndex = input.indexOf(END_DELIMITER);
            if (startIndex == -1 || endIndex == -1) {
                throw new InvalidCommandException("Invalid event. Please try again.");
            }
            description = input.substring(input.indexOf(" ") + 1, startIndex).trim();
            String start = input.substring(startIndex + START_DELIMITER.length(), endIndex).trim();
            String end = input.substring(endIndex + END_DELIMITER.length()).trim();
            taskToAdd = new Event(description, start, end);
            break;
        default:
            throw new InvalidCommandException("Invalid command: " + command);
        }

        items.add(taskToAdd);
        output(writer, ITEM_ADDED_MESSAGE + input);
        tasklistManager.saveTasksToFile(items);

    }

    private static int parseIndex(String[] inputParts, int size) {
        if (inputParts.length < 2) return -1;
        try {
            int index = Integer.parseInt(inputParts[1]) - 1;
            return (index >= 0 && index < size) ? index : -1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void output(PrintWriter writer, String message) {
        writer.println(message);
        writer.flush();
    }
}