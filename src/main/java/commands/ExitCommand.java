package commands;

import java.util.ArrayList;

import iomanager.TasklistManager;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import ui.Ui;


/**
 * Represents a command to terminate the application.
 * When executed, it displays a goodbye message to the user and then exits the program.
 * Responsibilities:
 * 1. Interacts with the Ui class to display a goodbye message.
 * 2. Terminates the application process.
 * Overrides the execute method from the Command class.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(ArrayList<task.Task> tasks, Ui ui, TasklistManager tasklistManager) {
        handleBye();
        return ui.showGoodbye();
    }

    private void handleBye() {
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> {
            System.exit(0);
        });
        // Start the pause
        pause.play();
    }
}


