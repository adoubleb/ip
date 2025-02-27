package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * The Deadline class represents a task with a specific deadline.
 * This class extends the abstract Task class and adds a deadline attribute
 * to denote the date and time by which the task must be completed.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructs a new Deadline with the specified description and deadline.
     * The deadline represents the specific date and time by which the task must be completed.
     *
     * @param description A brief description of the deadline task.
     * @param deadline The date and time by which the task must be completed.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }
    public String mySymbol() {
        return "D";
    }

    public String getDeadline() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        return deadline.format(formatter);
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.getDeadline() + ")";
    }

    /**
     * Serializes the Deadline object into a string representation for storage or transmission.
     * The string representation includes the task type, completion status, description,
     * and the deadline, separated by a delimiter.
     *
     * @return A serialized string representation of the Deadline object.
     */
    public String serialize() {
        return mySymbol() + " | " + (this.isDone ? "1" : "0") + " | " + this.description
            + " | " + deadline;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Deadline) {
            Deadline other = (Deadline) obj;
            boolean hasSameDescription = Objects.equals(this.description, other.description);
            boolean hasSameDeadline = Objects.equals(this.deadline, other.deadline);
            return hasSameDescription && hasSameDeadline;
        }
        return false;
    }

}
