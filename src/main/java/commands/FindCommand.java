package commands;

import java.util.ArrayList;

import exceptions.InvalidCommandException;
import iomanager.TasklistManager;
import task.Task;
import ui.Ui;

/**
 * The FindCommand class represents a command to search for tasks
 * in the task list containing a specific keyword in their descriptions.
 *
 * Upon execution, this command filters tasks whose descriptions match
 * the keyword (case-insensitive) and displays the result using the user interface.
 * If no matching tasks are found, a message is displayed indicating this.
 *
 * This command interacts with the task list, user interface,
 * and optionally the tasklist manager for execution.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     * This command is used to search for tasks in the task list
     * containing the provided keyword in their description.
     *
     * @param keyword The keyword to search for in the descriptions of tasks.
     *                The search is case-insensitive.
     */
    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    @Override
    public String execute(ArrayList<Task> tasks, Ui ui, TasklistManager tasklistManager)
            throws InvalidCommandException {
        ArrayList<Task> res = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                res.add(t);
            }
        }

        if (res.size() == 0) {
            return "No matching tasks found with description containing \"" + keyword + "\"";
        } else {
            return ui.showTasklist(res);
        }
    }
}
