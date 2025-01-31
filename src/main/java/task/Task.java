package task;

import java.time.LocalDateTime;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "*" + mySymbol() + "*" + "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Serializes the task into a String for saving in the file.
     */
    public abstract String serialize();

    /**
     * Deserializes a String back into a Task object.
     *
     * @param data The serialized task string.
     * @return The Task object parsed from the string.
     */


    public static Task deserialize(String data) {
        String[] parts = data.split(" \\| ");
        if (parts.length < 3) {
            return null;
        }

        String symbol = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        Task toReturn = null;
        switch (symbol) {
        case "T":
            toReturn = new Todo(description);
            break;
        case "D":
            toReturn = new Deadline(description, LocalDateTime.parse(parts[3]));
            break;
        case "E":
            toReturn = new Event(description, LocalDateTime.parse(parts[3]), LocalDateTime.parse(parts[4]));
            break;
        default:
            return null;
        }
        if (isDone) {
            toReturn.markAsDone();
        }
        return toReturn;
    }

    public String getDescription() {
        return this.description;
    } ;

    abstract public String mySymbol();

    public boolean isDone() {
        return isDone;
    }
}


