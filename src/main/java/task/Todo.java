package task;

/**
 * The Todo class represents a simple task that does not have a specific deadline
 * or time frame associated with it. It extends the abstract Task class and
 * implements its own serialization and task type symbol.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String mySymbol() {
        return "T";
    }

    public String serialize() {
        return mySymbol() + " | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }
}
