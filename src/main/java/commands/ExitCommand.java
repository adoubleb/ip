package commands;

import java.util.ArrayList;
import ui.Ui;
import iomanager.TasklistManager;

/**
 * Represents a command to terminate the application.
 * When executed, it displays a goodbye message to the user and then exits the program.
 *
 * This class extends the abstract Command class and provides a specific implementation
 * to handle the exit operation. It ensures clean termination of the application by
 * calling the System.exit method after displaying a farewell message.
 *
 * Responsibilities:
 * 1. Interacts with the Ui class to display a goodbye message.
 * 2. Terminates the application process.
 *
 * Overrides the execute method from the Command class.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(ArrayList<task.Task> tasks, Ui ui, TasklistManager tasklistManager) {
        ui.showGoodbye();
        System.exit(0);
    }
}
