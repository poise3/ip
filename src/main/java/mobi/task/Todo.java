package mobi.task;

/**
 * Represents a Todo task.
 * <p>
 * Inherits from generic class Task.
 * </p>
 */
public class Todo extends Task {

    /**
     * Initializes a new Todo task with the given description.
     * Calls superclass constructor to initialize description.
     *
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the description of the task, alongside completion status.
     *
     * @return task description as a string
     */
    @Override
    public String toString() {
        return "[T]" + "[" + super.getStatusIcon() + "] " + super.toString();
    }

    /**
     * Returns the description of the task, alongside completion status,
     * in a format suitable for saving to a file.
     *
     * @return task description as a string suitable for saving to files
     */
    @Override
    public String toFileString() {
        return String.format("T | %d | %s", isDone ? 1 : 0, super.toString());
    }
}
