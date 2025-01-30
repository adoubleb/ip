package commands;

import java.util.ArrayList;
import ui.Ui;
import iomanager.TasklistManager;

public class ExitCommand extends Command {
    @Override
    public void execute(ArrayList<task.Task> tasks, Ui ui, TasklistManager tasklistManager) {
        ui.showGoodbye();
        System.exit(0);
    }
}
