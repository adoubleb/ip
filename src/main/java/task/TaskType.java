package task;

public enum TaskType {
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline");

    private final String typeName;

    // Constructor for the enum
    TaskType(String typeName) {
        this.typeName = typeName;
    }

    // Getter to retrieve the custom name
    public String getTypeName() {
        return typeName;
    }

}

