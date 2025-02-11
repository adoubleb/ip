package task;

/**
 * Represents the various types of tasks that can be created within the task management application.
 * Each enum value corresponds to a specific type of task: TODO, EVENT, or DEADLINE.
 *
 * This enum is primarily used to categorize tasks and provide type-specific functionality
 * such as serialization or handling within the application.
 */
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

