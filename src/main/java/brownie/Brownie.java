package brownie;

import commands.Command;
import parser.ExtractDateTime;
import parser.UserInput;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import exceptions.InvalidCommandException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

;import iomanager.TasklistManager;
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