package commands;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import iomanager.TasklistManager;
import task.Task;
import task.Todo;
import ui.Ui;


//unitBeingTested_descriptionOfTestInputs_expectedOutcome

public class MarkCommandTest {
    @Test
    public void execute_markAsDone_marksTaskAsDone() throws Exception {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Task 1"));
        tasks.add(new Todo("Task 2"));

        // Stub Ui to validate interactions
        Ui ui = new Ui() {
            private boolean isMarkSuccessCalled = false;
            private boolean isShowTasklistCalled = false;

            @Override
            public String markSuccessMessage(int index, String taskString, boolean markedAsDone) {
                isMarkSuccessCalled = true;
                Assertions.assertEquals(0, index);
                Assertions.assertEquals("*T*[X] Task 1", taskString);
                Assertions.assertTrue(markedAsDone);
                return "Successfully marked item at index " + (index + 1) + ": " + taskString + " as done.";
            }

            @Override
            public String showTasklist(ArrayList<Task> tasks) {
                isShowTasklistCalled = true;
                Assertions.assertEquals(2, tasks.size());
                return "";
            }
        };

        // Stub TasklistManager to validate saveInteractions
        TasklistManager tasklistManager = new TasklistManager() {
            private boolean isSaveCalled = false;

            @Override
            public void saveTasksToFile(ArrayList<Task> tasks) {
                isSaveCalled = true;
                Assertions.assertEquals(2, tasks.size());
                Assertions.assertTrue(tasks.get(0).isDone());
            }
        };

        MarkCommand markCommand = new MarkCommand(0, true);

        markCommand.execute(tasks, ui, tasklistManager);

        Assertions.assertTrue(tasks.get(0).isDone(), "Task 1 should be marked as done.");
        Assertions.assertFalse(tasks.get(1).isDone(), "Task 2 should remain undone.");
    }

    @Test
    public void execute_markAsUndone_marksTaskAsUndone() throws Exception {
        ArrayList<Task> tasks = new ArrayList<>();
        Task task = new Todo("Task 1");
        task.markAsDone();
        tasks.add(task);

        // Stub Ui to validate interactions
        Ui ui = new Ui() {
            private boolean markSuccessCalled = false;

            @Override
            public String markSuccessMessage(int index, String taskString, boolean markedAsDone) {
                markSuccessCalled = true;
                Assertions.assertEquals(0, index);
                Assertions.assertFalse(markedAsDone);
                return "Successfully marked item at index " + (index + 1) + ": " + taskString + " as undone.";
            }

            @Override
            public String showTasklist(ArrayList<Task> tasks) {
                Assertions.assertTrue(markSuccessCalled, "markSuccessMessage should be called first.");
                return "";
            }

        };

        // Stub TasklistManager to validate saveInteractions
        TasklistManager tasklistManager = new TasklistManager() {
            @Override
            public void saveTasksToFile(ArrayList<Task> tasks) {
                Assertions.assertFalse(tasks.get(0).isDone(), "Task 1 should be marked as undone.");
            }
        };

        MarkCommand markCommand = new MarkCommand(0, false);
        markCommand.execute(tasks, ui, tasklistManager);

        Assertions.assertFalse(tasks.get(0).isDone(), "Task 1 should be marked undone.");
    }
}
