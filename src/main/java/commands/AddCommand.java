package commands;

import java.time.LocalDateTime;
import java.util.ArrayList;

import exceptions.InvalidCommandException;
import iomanager.TasklistManager;
import parser.DateTimeExtractor;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskType;
import task.Todo;
import ui.Ui;

/**
 * Represents a command to add a task to the task list. This class extends the
 * abstract Command class and provides the implementation for adding tasks of
 * type TODO, EVENT, or DEADLINE.
 */
public class AddCommand extends Command {
    private static final String START_DELIMITER = "start:";
    private static final String BY_DELIMITER = "by:";
    private final TaskType taskType;
    private final String content;

    /**
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

    private boolean hasDuplicate(ArrayList<Task> tasks, Task taskToAdd) {
        for (Task t : tasks) {
            if (t.equals(taskToAdd)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param tasks The list of tasks to which the new task will be added.
     * @param ui The user interface instance for displaying messages and feedback.
     * @param tasklistManager The manager responsible for saving and managing the task list.
     * @throws InvalidCommandException If the task type is invalid or the content cannot be parsed.
     */
    @Override
    public String execute(ArrayList<Task> tasks, Ui ui, TasklistManager tasklistManager) throws
            InvalidCommandException {
        String result;
        Task taskToAdd;

        switch (taskType) {
        case TODO:
            taskToAdd = createTodo(content);
            break;
        case EVENT:
            taskToAdd = createEvent(content);
            break;
        case DEADLINE:
            taskToAdd = createDeadline(content);
            break;
        default:
            throw new InvalidCommandException("Invalid task type");
        }
        assert taskToAdd != null;
        result = "";
        if (hasDuplicate(tasks, taskToAdd)) {
            result += "Note: An exact copy of the task exists in the list. The new task is added anyways.";
        }
        tasks.add(taskToAdd);
        tasklistManager.saveTasksToFile(tasks);
        int userFriendlyIndex = tasks.size() - 1;
        result += ui.addSuccessMessage(userFriendlyIndex, taskToAdd.toString());
        return result;
    }

    private Task createTodo(String content) {
        return new Todo(content);
    }

    private Task createEvent(String content) {
        int startIndex = content.indexOf(START_DELIMITER);
        String description = content.substring(0, startIndex).trim();
        DateTimeExtractor extractDateTime = new DateTimeExtractor(this.content);
        ArrayList<LocalDateTime> datetimesEvent = extractDateTime.eventDateTime();
        Task taskToAdd = new Event(description, datetimesEvent.get(0), datetimesEvent.get(1));
        return taskToAdd;
    }

    private Task createDeadline(String content) throws InvalidCommandException {
        int byIndex = content.indexOf(BY_DELIMITER);
        String description = content.substring(0, byIndex).trim();
        DateTimeExtractor extractDateTime = new DateTimeExtractor(this.content);
        ArrayList<LocalDateTime> datetimesDeadline = extractDateTime.deadlineDateTime();
        Task taskToAdd = new Deadline(description, datetimesDeadline.get(0));
        return taskToAdd;
    }
}
