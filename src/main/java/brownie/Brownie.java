package brownie;

import java.util.ArrayList;

import commands.Command;
import iomanager.TasklistManager;
import parser.UserInput;
import task.Task;
import ui.Ui;

public class Brownie {
    private TasklistManager tasklistManager;
    private ArrayList<Task> items;
    private Ui ui;

    public Brownie() {
        ui = new Ui();
        tasklistManager = new TasklistManager();
        try {
            tasklistManager.initializeTasklist();
            items = tasklistManager.loadTasksFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        ui.showWelcome();
        while (true) {
            try {
                String userInput = ui.readCommand();
                ui.showLine();
                UserInput input = new UserInput(userInput);
                Command command = input.parse();
                command.execute(items, ui, tasklistManager);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Brownie brownie = new Brownie();
        brownie.run();
    }
}