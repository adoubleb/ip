package commands;

import iomanager.TasklistManager;
import task.Task;
import task.Todo;
import ui.Ui;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MarkCommandTest {

    @Test
    public void execute_markAsDone_marksTaskAsDone() throws Exception {
        // Arrange
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Task 1"));
        tasks.add(new Todo("Task 2"));

        // Stub Ui to validate interactions
        Ui ui = new Ui() {
            private boolean markSuccessCalled = false;
            private boolean showTasklistCalled = false;

            @Override
            public void markSuccessMessage(int index, String taskString, boolean markedAsDone) {
                markSuccessCalled = true;
                assertEquals(0, index);
                assertEquals("*T*[X] Task 1", taskString);
                assertTrue(markedAsDone);
            }

            @Override
            public String showTasklist(ArrayList<Task> tasks) {
                showTasklistCalled = true;
                assertEquals(2, tasks.size());
            }

            @Override
            public void showLine() {
                assertTrue(markSuccessCalled && showTasklistCalled, "Messages should be called in order.");
            }
        };

        // Stub TasklistManager to validate saveInteractions
        TasklistManager tasklistManager = new TasklistManager() {
            private boolean saveCalled = false;

            @Override
            public void saveTasksToFile(ArrayList<Task> tasks) {
                saveCalled = true;
                assertEquals(2, tasks.size());
                assertTrue(tasks.get(0).isDone());
            }
        };

        // Test data
        MarkCommand markCommand = new MarkCommand(0, true);

        // Act
        markCommand.execute(tasks, ui, tasklistManager);

        // Assert
        assertTrue(tasks.get(0).isDone(), "Task 1 should be marked as done.");
        assertFalse(tasks.get(1).isDone(), "Task 2 should remain undone.");
    }

    @Test
    public void execute_markAsUndone_marksTaskAsUndone() throws Exception {
        // Arrange
        ArrayList<Task> tasks = new ArrayList<>();
        Task task = new Todo("Task 1");
        task.markAsDone();
        tasks.add(task);

        // Stub Ui to validate interactions
        Ui ui = new Ui() {
            private boolean markSuccessCalled = false;

            @Override
            public void markSuccessMessage(int index, String taskString, boolean markedAsDone) {
                markSuccessCalled = true;
                assertEquals(0, index);
                assertFalse(markedAsDone);
            }

            @Override
            public String showTasklist(ArrayList<Task> tasks) {
                assertTrue(markSuccessCalled, "markSuccessMessage should be called first.");
                return "";
            }

        };

        // Stub TasklistManager to validate saveInteractions
        TasklistManager tasklistManager = new TasklistManager() {
            @Override
            public void saveTasksToFile(ArrayList<Task> tasks) {
                assertFalse(tasks.get(0).isDone(), "Task 1 should be marked as undone.");
            }
        };

        // Test data
        MarkCommand markCommand = new MarkCommand(0, false);

        // Act
        markCommand.execute(tasks, ui, tasklistManager);

        // Assert
        assertFalse(tasks.get(0).isDone(), "Task 1 should be marked undone.");
    }
}