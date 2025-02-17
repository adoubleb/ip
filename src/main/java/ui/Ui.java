package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import task.Task;

/**
 * The Ui class is responsible for user interaction in a console-based application.
 * It provides methods to display messages, read user input, and format outputs for
 * various operations.
 */
public class Ui {
    private BufferedReader reader;

    public Ui() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    public String readCommand() throws IOException {
        return reader.readLine();
    }

    /**
     * Generates a formatted string representing the list of tasks.
     * Each task is prefixed with its position in the list, followed by the task description.
     *
     * @param items The list of tasks to display.
     * @return A string containing the formatted task list, with each task on a new line.
     */
    public String showTasklist(ArrayList<Task> items) {
        if (items.size() == 0) {
            return "No tasks found.";
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            result.append((i + 1)).append(". ").append(items.get(i)).append("\n");
        }
        return result.toString();
    }

    /**
     * Constructs a success message indicating whether the specified task was marked as done or undone.
     *
     * @param index The zero-based index of the task in the task list.
     * @param description A string representing the description of the task.
     * @param markAsDone A boolean flag indicating if the task is marked as done (true) or not done (false).
     * @return A formatted string message describing the success of the marking operation.
     */
    public String markSuccessMessage(int index, String description, boolean markAsDone) {
        if (markAsDone) {
            return "Successfully marked item at index " + (index + 1) + ": " + description + " as done.";
        } else {
            return "Successfully noted, item at index " + (index + 1) + ": " + description + " is not done.";
        }
    }

    public String deleteSuccessMessage(int index, String description) {
        return "Successfully deleted item at index " + (index + 1) + ": " + description;
    }

    public String addSuccessMessage(int index, String description) {
        return "Successfully added item at index " + (index + 1) + ": " + description;
    }

}
