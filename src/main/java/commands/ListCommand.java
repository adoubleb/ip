package commands;

import java.util.ArrayList;

import iomanager.TasklistManager;
import task.Task;
import ui.Ui;

/**
 * Represents the ListCommand that lists all tasks currently stored in the application's task list.
 * This command, when executed, displays the list of tasks to the user through the user interface.
 *
 * The command does not modify the task list but relies on the Ui class to handle user interaction
 * and display the task list in a readable format.
 *
 * This class extends the abstract Command class and implements the execute method to perform its
 * specific functionality.
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand, which displays all tasks currently stored
     * in the user's task list using the provided Ui object.
     *
     * @param tasks The list of tasks to be displayed, represented as an ArrayList of Task objects.
     * @param ui The Ui object responsible for displaying the list of tasks to the user.
     * @param tasklistManager The TasklistManager managing operations on the task list.
     *                        This parameter is not utilized in this implementation of the execute method.
     * @throws Exception If any unexpected errors occur during the execution of the command.
     */
    @Override
    public String execute(ArrayList<Task> tasks, Ui ui, TasklistManager tasklistManager) throws Exception {
        return ui.showTasklist(tasks);
    }
}
