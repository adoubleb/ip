package commands;

import exceptions.InvalidCommandException;
import iomanager.TasklistManager;
import task.Task;
import ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command{
    String keyword;

    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, TasklistManager tasklistManager) throws InvalidCommandException {
        ArrayList<Task> res = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                res.add(t);
            }
        }

        if (res.size() == 0) {
            System.out.println("No matching tasks found with description containing \"" + keyword + "\"");
        } else {
            ui.showTasklist(res);
        }
        ui.showLine();
    }
}
