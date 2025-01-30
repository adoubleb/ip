package ui;

import task.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Ui {
    BufferedReader reader;
    public Ui() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void showLine() {
        System.out.println("----------------------------------------------------");
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Brownie. \nWhat can I do for you?");
        System.out.println("   _=,_\n" +
                "    o_/6 /#\\\n" +
                "    \\__ |##/\n" +
                "     ='|--\\\n" +
                "       /   #'-.\n" +
                "       \\#|_   _'-. /\n" +
                "        |/ \\_( # |\" \n" +
                "       C/ ,--___/");
        showLine();
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!\"");
        System.out.println("   ___\n" +
                " __/_  `.  .-\"\"\"-.\n" +
                " \\_,` | \\-'  /   )`-')\n" +
                "  \"\") `\"`    \\  ((`\"`\n" +
                " ___Y  ,    .'7 /|\n" +
                "(_,___/...-` (_/_/");
        showLine();
    }

    public String readCommand() throws IOException {
        return reader.readLine();
    }

    public void showTasklist(ArrayList<Task> items) {
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i));
        }
    }
    public void markSuccessMessage(int index, String description, boolean markAsDone) {
        if (markAsDone) {
            System.out.println("Successfully marked item at index " + (index + 1) + ": " + description + " as done.");
        } else {
            System.out.println("Successfully noted, item at index " + (index + 1) + ": " + description + " is not done.");
        }}

    public void deleteSuccessMessage(int index, String description) {
        System.out.println("Successfully deleted item at index " + (index + 1) + ": " + description);
    }

    public void addSuccessMessage(int index, String description) {
        System.out.println("Successfully added item at index " + index + ": " + description);
    }

    public void showErrorMessage(String message) {
        System.out.println(message);
    }
}
