package commands;

import exceptions.InvalidCommandException;
import iomanager.TasklistManager;
import parser.ExtractDateTime;
import task.*;
import ui.Ui;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AddCommand extends Command {
    private static final String START_DELIMITER = "start:";
    private static final String BY_DELIMITER = "by:";
    private final TaskType taskType;
    private final String content;

    public AddCommand(TaskType taskType, String content) {
        super();
        this.taskType = taskType;
        this.content = content.trim();
    }

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
            int startIndex = content.indexOf(START_DELIMITER);
            description = content.substring(content.indexOf(" ") + 1, startIndex).trim();
            ArrayList<LocalDateTime> datetimesEvent = extractDateTime.eventDateTime();
            taskToAdd = new Event(description, datetimesEvent.get(0), datetimesEvent.get(1));
            break;
        case DEADLINE:
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
