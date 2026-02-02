package mobi.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 * <p>
 * Inherits from generic class Task.
 * Stores additional temporal information, by/deadline date.
 * </p>
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Initializes a new Deadline task with the given description.
     * Calls superclass constructor to initialize description.
     * Initializes by/deadline date.
     *
     * @param description the description of the task
     * @param by the deadline date of the task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the by/deadline date of the task.
     *
     * @return by/deadline date as a LocalDateTime
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Returns the description of the task, alongside
     * completion status and formatted by/deadline date.
     *
     * @return task description as a string
     */
    @Override
    public String toString() {
        return "[D]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (by: " +
                    by.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")) + ")";
    }

    /**
     * Returns the description of the task, alongside completion status,
     * in a format suitable for saving to a file.
     * Format: E | isDone | description | end
     *
     * @return task description as a string suitable for saving to files
     */
    @Override
    public String toFileString() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, super.toString(),
                        by.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")));
    }
}