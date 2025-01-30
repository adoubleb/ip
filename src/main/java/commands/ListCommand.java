package commands;

import iomanager.TasklistManager;
import task.Task;
import ui.Ui;

import java.util.ArrayList;

public class ListCommand extends Command{
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, TasklistManager tasklistManager) throws Exception {
        ui.showTasklist(tasks);
    }
}
