package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDateTime start;
    LocalDateTime end;
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }
    public String mySymbol() {
        return "E";
    }

    public String getStart() {
        return start.format(DateTimeFormatter.ofPattern("MMM d yyyy")); // -> Oct 15 2019
    }

    public String getEnd() {
        return end.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return super.toString() + " (start: " + this.getStart() + ")" + " (end: " + this.getEnd() + ")";
    }

    public String serialize() {
        return mySymbol() + " | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " +
                this.start + " | " + this.end;
    }
}
