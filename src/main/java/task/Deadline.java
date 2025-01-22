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
}
