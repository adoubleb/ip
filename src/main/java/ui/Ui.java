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
        return "Bye. Hope to see you again soon!\"";
    }

    public String readCommand() throws IOException {
        return reader.readLine();
    }

    public String showTasklist(ArrayList<Task> items) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            result.append((i + 1)).append(". ").append(items.get(i)).append("\n");
        }
        return result.toString();
    }
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
        return "Successfully added item at index " + (index+ 1) + ": " + description;
    }

    public void showErrorMessage(String message) {
        System.out.println(message);
    }

}
