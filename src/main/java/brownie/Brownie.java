package brownie;

import commands.Command;
import parser.ExtractDateTime;
import parser.UserInput;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import exceptions.InvalidCommandException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

;import iomanager.TasklistManager;
import ui.Ui;

/**
 * Represents the main Brownie application that manages tasks.
 * It initializes necessary components, loads tasks from file,
 * and handles user interactions via the command-line interface.
 *
 * The Brownie class coordinates interactions between the user interface,
 * task list, and file storage. It serves as the entry point of the application.
 */
public class Brownie {
    private TasklistManager tasklistManager;
    private ArrayList<Task> items;
    private Ui ui;

    /**
     * Constructs a new instance of the Brownie application.
     * Initializes core components including the User Interface (Ui),
     * TasklistManager, and task list. It attempts to load tasks from
     * the storage file and handles any exceptions that occur during
     * initialization.
     *
     * The constructor performs the following actions:
     * - Initializes the user interface to facilitate interaction with the user.
     * - Creates an instance of TasklistManager to handle task storage and management.
     * - Attempts to load an existing task list from the file, if available;
     *   otherwise, ensures the necessary file setup is complete.
     *
     * If any exception occurs during task list initialization or loading,
     * the exception is printed to the error stream.
     */
    public Brownie() {
        ui = new Ui();
        tasklistManager = new TasklistManager();
        try {
            tasklistManager.initializeTasklist();
            items = tasklistManager.loadTasksFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Executes the main logic loop of the application.
     * This method is responsible for user interaction, command parsing,
     * and command execution within the application.
     *
     * The method performs the following steps in a continuous loop:
     * 1. Displays a welcome message to the user at the start of the application.
     * 2. Repeatedly reads user input from the command line.
     * 3. Parses the user input into a command using the {@code UserInput} class.
     * 4. Executes the parsed command by delegating to the command's {@code execute} method.
     *
     * Exceptions that occur during user input reading, parsing, or command execution
     * are caught and their stack traces are printed to the error stream.
     *
     * This method runs indefinitely until termination is explicitly handled
     * through a command such as 'bye'.
     */
    public void run() {
        ui.showWelcome();
        while (true) {
            try {
                String userInput = ui.readCommand();
                ui.showLine();
                UserInput input = new UserInput(userInput);
                Command command = input.parse();
                command.execute(items, ui, tasklistManager);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Brownie brownie = new Brownie();
        brownie.run();
    }
}