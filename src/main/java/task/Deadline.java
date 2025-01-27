package task;

public class Deadline extends Task {
    String deadline;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline.trim();
    }
    public String mySymbol() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString() + " (" + deadline + ")";
    }

    public String serialize() {
        return mySymbol() + " | " + (this.isDone ? "1" : "0") + " | " + this.description +
                " | " + deadline;
    }
}
