package brownie;

import java.util.ArrayList;
import java.util.function.BiConsumer;

import commands.Command;
import iomanager.TasklistManager;
import parser.UserInput;
import task.Task;
import ui.Ui;
import javafx.scene.image.Image;

/**
 * Represents the main Brownie application that manages tasks.
 * It initializes necessary components, loads tasks from file,
 * and handles user interactions via the command-line interface.
 *
 * The Brownie class coordinates interactions between the user interface,
 * task list, and file storage. It serves as the entry point of the application.
 */
public class Brownie {
    private final TasklistManager tasklistManager;
    private ArrayList<Task> items;
    private Ui ui;
    private BiConsumer<String, Image> dialogUpdater;

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
//    public void run() {
//        ui.showWelcome();
//        while (true) {
//            try {
//                String userInput = ui.readCommand();
//                ui.showLine();
//                UserInput input = new UserInput(userInput);
//                Command command = input.parse();
//                command.execute(items, ui, tasklistManager);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

//    public static void main(String[] args) {
//        Brownie brownie = new Brownie();
//        brownie.run();
//    }

    public void setDialogUpdater(BiConsumer<String, Image> dialogUpdater) {
        this.dialogUpdater = dialogUpdater;
    }

    /**
     * Processes the user input and generates an appropriate response by parsing and executing the input command.
     * Delegates command parsing to the {@code UserInput} and execution to the corresponding {@code Command}.
     * Handles any exceptions during the process, ensuring errors are captured and a response is generated.
     * Optionally updates the dialog UI using the configured callback method.
     *
     * @param input The raw input string provided by the user. This is expected to contain a command and optional arguments.
     */
    public void respondToUser(String input) {
        String response = "";
        try {
            UserInput userInput = new UserInput(input);
            Command command = userInput.parse();
            response = command.execute(items, ui, tasklistManager);
        } catch (Exception e) {
            e.printStackTrace();
            response = "Error: " + e.getMessage();
        }

        // Call the callback (only if it's defined) to update the GUI
        if (dialogUpdater != null) {
            dialogUpdater.accept(response, new Image("/images/Brownie.jpg"));
        }
    }


}
