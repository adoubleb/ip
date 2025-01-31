package commands;

import iomanager.TasklistManager;
import task.Task;
import ui.Ui;

import java.util.ArrayList;

/**
 * Represents a command to mark a task in the task list as done or undone.
 * This command is used to toggle the completion status of a specific task
 * based on the user’s input.
 *
 * The command requires the index of the task in the task list and a boolean
 * value indicating whether the task should be marked as done or undone.
 */
public class MarkCommand extends Command{
    //expects format mark int
    private final int index;
    private final boolean markAsDone;

    /**
     * Constructs a MarkCommand with the index of the task to be marked and a flag
     * indicating whether the task should be marked as done or undone.
     *
     * @param index The zero-based index of the task in the task list to be marked.
     * @param markAsDone A boolean flag indicating if the task should be marked as done (true) or undone (false).
     */
    public MarkCommand(int index, boolean markAsDone) { //index should be 0-index
        super();
        this.index = index;
        this.markAsDone = markAsDone;
    }

    /**
     * Executes the MarkCommand by marking a specified task in the task list as either done or undone.
     * Updates and saves the task list after the operation and displays the updated list.
     *
     * @param tasks The current list of tasks.
     * @param ui An instance of the Ui class responsible for displaying messages to the user.
     * @param tasklistManager The manager responsible for saving changes to the task list.
     * @throws Exception If the operation fails, typically due to invalid input or saving errors.
     */
    public void execute(ArrayList<Task> tasks, Ui ui, TasklistManager tasklistManager) throws Exception {
        Task task = tasks.get(index);
        if (markAsDone) {
            task.markAsDone();
            ui.markSuccessMessage(index, task.toString(), true);
        } else {
            task.markAsUndone();
            ui.markSuccessMessage(index, task.toString(), false);
        }
        tasklistManager.saveTasksToFile(tasks);
        ui.showTasklist(tasks);
        ui.showLine();
    }
}
