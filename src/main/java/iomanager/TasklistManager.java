package iomanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import task.Task;

/**
 * Manages the tasklist operations such as initialization, saving tasks, and loading tasks.
 */
public class TasklistManager {
    private static final String DATA_FOLDER_PATH = "./data/brownie";
    private static final String TASKLIST_FILE_PATH = DATA_FOLDER_PATH + "/tasklist.txt";

    private final PrintWriter writer;
    private final BufferedReader reader;

    /**
     * Constructs a new instance of the TasklistManager.
     *
     * This constructor initializes the input and output streams used by the manager
     * to interact with the system. Specifically:
     * - A PrintWriter is initialized to allow writing output messages to the system's
     *   standard output stream.
     * - A BufferedReader is initialized to read input from the system's standard input stream.
     *
     * These streams are utilized by various methods in the TasklistManager to handle
     * user interaction and file management tasks.
     */
    public TasklistManager() {
        this.writer = new PrintWriter(System.out);;
        this.reader = new BufferedReader(new InputStreamReader(System.in));;
    }

    /**
     * Ensures that the tasklist file is available and valid.
     */
    public void initializeTasklist() {
        File dataFolder = new File(DATA_FOLDER_PATH);
        File tasklistFile = new File(TASKLIST_FILE_PATH);

        ensureDataFolderExists(dataFolder);
        if (!tasklistFile.exists()) {
            createNewTasklistFile(tasklistFile);
        } else if (isFileCorrupted(tasklistFile)) {
            handleCorruptedFile(tasklistFile);
        }
    }

    /**
     * Ensures the data folder exists, and creates it if it doesn't.
     */
    private void ensureDataFolderExists(File dataFolder) {
        if (!dataFolder.exists()) {
            if (dataFolder.mkdirs()) {
                writer.println("Data folder created at " + DATA_FOLDER_PATH);
            }
        }
    }

    /**
     * Attempts to create a new tasklist.txt file.
     */
    private void createNewTasklistFile(File tasklistFile) {
        try {
            tasklistFile.createNewFile();
            writer.println("tasklist.txt created successfully at " + TASKLIST_FILE_PATH);
        } catch (IOException e) {
            writer.println("Error while creating tasklist.txt: " + e.getMessage());
            writer.flush();
            System.exit(1);
        }
    }

    /**
     * Checks if the file is corrupted.
     */
    private boolean isFileCorrupted(File tasklistFile) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(tasklistFile))) {
            String firstLine = fileReader.readLine();
            // Example corruption check: file is empty
            return firstLine == null || firstLine.trim().isEmpty();
        } catch (IOException e) {
            return true; // File cannot be read
        }
    }

    /**
     * Handles cases where the file is corrupted.
     */
    private void handleCorruptedFile(File tasklistFile) {
        writer.println("WARNING: tasklist.txt appears to be corrupted.");
        writer.flush();

        while (true) {
            writer.println("Would you like to create a new tasklist.txt? (y/n):");
            writer.flush();

            try {
                String userInput = reader.readLine().trim().toLowerCase();
                if (userInput.equals("y")) {
                    recreateTasklistFile(tasklistFile);
                    break;
                } else if (userInput.equals("n")) {
                    writer.println("Program will terminate. Please fix or replace tasklist.txt.");
                    writer.flush();
                    System.exit(1);
                } else {
                    writer.println("Invalid input. Please enter 'y' or 'n'.");
                    writer.flush();
                }
            } catch (IOException e) {
                writer.println("Error while reading input: " + e.getMessage());
                writer.flush();
            }
        }
    }

    /**
     * Deletes the existing corrupted file and creates a new one.
     */
    private void recreateTasklistFile(File tasklistFile) {
        try {
            if (tasklistFile.delete() && tasklistFile.createNewFile()) {
                writer.println("New tasklist.txt created successfully at " + TASKLIST_FILE_PATH);
                writer.flush();
            }
        } catch (IOException e) {
            writer.println("Error while recreating tasklist.txt: " + e.getMessage());
            writer.flush();
            System.exit(1);
        }
    }

    /**
     * Saves the current list of tasks to the file.
     *
     * @param items The list of tasks to save.
     */
    public void saveTasksToFile(ArrayList<Task> items) {
        try (PrintWriter fileWriter = new PrintWriter(new FileWriter(TASKLIST_FILE_PATH))) {
            for (Task task : items) {
                fileWriter.println(task.serialize()); // Serialize each task to a line in the file.
            }
        } catch (IOException e) {
            writer.println("Error saving tasks to file: " + e.getMessage());
            writer.flush();
        }
    }

    /**
     * Loads tasks from the file into an ArrayList.
     *
     * @return The list of tasks loaded from the file.
     */
    public ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> items = new ArrayList<>();
        File tasklistFile = new File(TASKLIST_FILE_PATH);

        try (BufferedReader fileReader = new BufferedReader(new FileReader(tasklistFile))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                Task task = Task.deserialize(line); // Deserialize each line back into a task object
                if (task != null) {
                    items.add(task);
                }
            }
        } catch (IOException e) {
            writer.println("Error loading tasks from file: " + e.getMessage());
            writer.flush();
        }

        return items;
    }
}

