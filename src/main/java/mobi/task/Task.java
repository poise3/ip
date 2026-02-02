package mobi.task;

/**
 * Abstract class representing a generic Task.
 * <p>
 * Each Task has a description and a completion state.
 * Subclasses should provide a way to represent the
 * task as a string suitable for saving to a file.
 * </p>
 */
public abstract class Task {
    private final String description;
    protected boolean isDone;

    /**
     * Initializes a new Task with the given description.
     * The task is initially marked as not done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string representing the completion status of the task.
     * <p>
     * Returns "X" if the task is done, or a blank space " " if not done.
     * </p>
     *
     * @return status icon as a string
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description of the task.
     *
     * @return task description as a string
     */
    public String toString() {
        return this.description;
    }

    /**
     * Marks the task as completed.
     * Sets {@code isDone} to {@code true}.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     * Sets {@code isDone} to {@code false}.
     */
    public void markNotDone() { this.isDone = false; }

    /**
     * Returns a string representation of the task suitable for saving to a file.
     * Must be implemented by subclasses for their own specific format.
     *
     * @return string representation of the task for storage
     */
    public abstract String toFileString();
}