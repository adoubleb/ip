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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        String formattedDateTime = start.format(formatter);
        return formattedDateTime; // -> Oct 15 2019
    }

    public String getEnd() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        String formattedDateTime = end.format(formatter);
        return formattedDateTime;
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
