package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDateTime deadline;
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }
    public String mySymbol() {
        return "D";
    }

    public String getDeadline() {
        return deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")); // -> Oct 15 2019
    }

    @Override
    public String toString() {
        return super.toString() + " (" + this.getDeadline() + ")";
    }

    public String serialize() {
        return mySymbol() + " | " + (this.isDone ? "1" : "0") + " | " + this.description +
                " | " + deadline;
    }
}
