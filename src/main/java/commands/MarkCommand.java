package commands;

import iomanager.TasklistManager;
import task.Task;
import ui.Ui;

import java.util.ArrayList;

public class MarkCommand extends Command{
    //expects format mark int
    private final int index;
    private final boolean markAsDone;
    public MarkCommand(int index, boolean markAsDone) { //index should be 0-index
        super();
        this.index = index;
        this.markAsDone = markAsDone;
    }
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
