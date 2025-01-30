package commands;

import iomanager.TasklistManager;
import task.Task;
import ui.Ui;

import java.util.ArrayList;

public abstract class Command {
    public abstract void execute(ArrayList<Task> tasks, Ui ui, TasklistManager tasklistManager) throws Exception;
}
