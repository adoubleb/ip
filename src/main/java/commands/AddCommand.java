package commands;

import exceptions.InvalidCommandException;
import iomanager.TasklistManager;
import parser.ExtractDateTime;
import task.*;
import ui.Ui;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents a command to add a task to the task list. This class extends the
 * abstract Command class and provides the implementation for adding tasks of
 * type TODO, EVENT, or DEADLINE.
 */
public class AddCommand extends Command {
    private final TaskType taskType;
    private final String content;

    /**
     * Constructs an AddCommand with the specified task type and content.
     * This command is used to add a task of the given type (TODO, EVENT, DEADLINE)
     * with the provided content.
     *
     * @param taskType the type of task to be added (TODO, EVENT, or DEADLINE)
     * @param content  the details or description of the task, including any
     *                 additional information such as dates or times for EVENTS
     *                 and DEADLINES
     */
    public AddCommand(TaskType taskType, String content) {
        super();
        this.taskType = taskType;
        this.content = content.trim();
    }

    /**
     * Executes the AddCommand. Depending on the task type (TODO, EVENT, or DEADLINE),
     * this method parses the content, creates the appropriate Task object, adds it
     * to the tasks list, saves the updated list to the file via TasklistManager, and
     * displays a success message using the Ui.
     *
     * @param tasks The list of tasks to which the new task will be added.
     * @param ui The user interface instance for displaying messages and feedback.
     * @param tasklistManager The manager responsible for saving and managing the task list.
     * @throws InvalidCommandException If the task type is invalid or the content cannot be parsed.
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, TasklistManager tasklistManager) throws InvalidCommandException {
        Task taskToAdd;
        ExtractDateTime extractDateTime = new ExtractDateTime(this.content);
        String description;
        switch (taskType) {
        case TODO:
            taskToAdd = new Todo(content);
            break;
        case EVENT:
            final String START_DELIMITER = "start:";
            int startIndex = content.indexOf(START_DELIMITER);
            description = content.substring(content.indexOf(" ") + 1, startIndex).trim();
            ArrayList<LocalDateTime> datetimesEvent = extractDateTime.eventDateTime();
            taskToAdd = new Event(description, datetimesEvent.get(0), datetimesEvent.get(1));
            break;
        case DEADLINE:
            final String BY_DELIMITER = "by:";
            int byIndex = content.indexOf(BY_DELIMITER);
            description = content.substring(content.indexOf(" ") + 1, byIndex).trim();
            ArrayList<LocalDateTime> datetimesDeadline = extractDateTime.deadlineDateTime();
            taskToAdd = new Deadline(description, datetimesDeadline.get(0));
            break;
        default:
            throw new InvalidCommandException("Invalid task type");
        }
        tasks.add(taskToAdd);
        tasklistManager.saveTasksToFile(tasks);
        ui.addSuccessMessage(tasks.size() - 1, taskToAdd.toString());
        ui.showLine();
    }
}
