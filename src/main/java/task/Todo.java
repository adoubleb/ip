package task;

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
