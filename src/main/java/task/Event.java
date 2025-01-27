package task;

public class Event extends Task {
    String start;
    String end;
    public Event(String description, String start, String end) {
        super(description);
        this.start = start.trim();
        this.end = end.trim();
    }
    public String mySymbol() {
        return "E";
    }

    @Override
    public String toString() {
        return super.toString() + " (start: " + this.start + ")" + " (end: " + this.end + ")";
    }

    public String serialize() {
        return mySymbol() + " | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " +
                this.start + " | " + this.end;
    }
}
