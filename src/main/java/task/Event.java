package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a task that occurs within a specific time*/
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs a new Event with the specified description, start time, and end time.
     *
     * @param description A brief description of the event.
     * @param start The starting date and time of the event.
     * @param end The ending date and time of the event.
     */
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

    /**
     * Serializes the Event object into a string representation for storage or transmission.
     * The string representation includes the task type (symbol), completion status,
     * description, start time, and end time, separated by a delimiter.
     *
     * @return A serialized string representation of the Event object.
     */
    public String serialize() {
        return mySymbol() + " | " + (this.isDone ? "1" : "0") + " | " + this.description + " | "
            + this.start + " | " + this.end;
    }
}
