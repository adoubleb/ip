package commands;

import java.util.ArrayList;

import exceptions.InvalidCommandException;
import iomanager.TasklistManager;
import task.Task;
import ui.Ui;


/**
 * Represents a command that deletes a specific task from the task list.
 * The task to be deleted is specified via its index in the task list.
 * This index is 0-based.
 *
 * When executed, the command removes the task from the list, saves the updated list
 * using the TasklistManager, and notifies the user through the UI. If the provided
 * index is invalid (negative or greater than the largest index in the task list),
 * an InvalidCommandException is thrown.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand to delete a task at the specified index in the task list.
     * The index provided is 0-indexed, meaning the first element in the list has index 0.
     * If executed, this command will remove the task from the list, save the updated
     * task list, and provide feedback to the user. An exception will be thrown if
     * the index is negative or out of bounds.
     *
     * @param index The 0-based index of the task to be deleted from the task list.
     */
    public DeleteCommand(int index) { //attention! index here is 0-indexed
        super();
        this.index = index;
    }

    /**
     * Executes the delete command by removing a task from the provided task list,
     * saving the updated list to the file using TasklistManager, and displaying a
     * confirmation message to the user using the UI.
     * If the provided task index is invalid (negative or out of bounds),
     * an InvalidCommandException is thrown.
     *
     * @param tasks The current list of tasks from which the task will be deleted.
     * @param ui The UI instance used to display messages to the user.
     * @param tasklistManager The TasklistManager instance used to save the updated list.
     * @throws InvalidCommandException If the specified task index is negative or exceeds the task list size.
     */
    @Override
    public String execute(ArrayList<Task> tasks, Ui ui, TasklistManager tasklistManager) throws
            InvalidCommandException {
        if (this.index < 0) {
            throw new InvalidCommandException("Can't delete negative index");
        } else if (index >= tasks.size()) {
            throw new InvalidCommandException("Can't delete index: " + (this.index + 1) + ". Biggest index is " + tasks.size());
        }
        Task task = tasks.get(index);
        tasks.remove(index);
        tasklistManager.saveTasksToFile(tasks);
        return ui.deleteSuccessMessage(index, task.toString());
    }
}
