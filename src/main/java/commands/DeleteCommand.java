package commands;

import exceptions.InvalidCommandException;
import iomanager.TasklistManager;
import task.Task;
import ui.Ui;

import java.util.ArrayList;

public class DeleteCommand extends Command{
    int index;
    public DeleteCommand(int index) { //attention! index here is 0-indexed
        super();
        this.index = index;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, TasklistManager tasklistManager) throws InvalidCommandException{
        if (this.index < 0) {
            throw new InvalidCommandException("Can't delete negative index");
        } else if (index >= tasks.size()) {
            throw new InvalidCommandException("Can't delete index: " + this.index + ". Biggest index is " + tasks.size());
        }
        Task task = tasks.get(index);
        tasks.remove(index);
        tasklistManager.saveTasksToFile(tasks);
        ui.deleteSuccessMessage(index, task.toString());
        ui.showLine();
    }
}
