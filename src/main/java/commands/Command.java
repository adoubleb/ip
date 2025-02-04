package commands;

import iomanager.TasklistManager;
import task.Task;
import ui.Ui;

import java.util.ArrayList;

/**
 * Represents an abstract command in the task management application.
 * Each subclass of Command encapsulates a specific action or user instruction
 * such as adding, deleting, marking, or listing tasks. This design allows for
 * extending the application by implementing new commands while adhering to the
 * same structure.
 *
 * Subclasses are required to implement the `execute` method, which defines
 * the specific behavior of the command. Commands executed can modify the task list,
 * interact with the user interface, and utilize the task list manager for file persistence.
 */
public abstract class Command {
    public abstract String execute(ArrayList<Task> tasks, Ui ui, TasklistManager tasklistManager) throws Exception;
}
