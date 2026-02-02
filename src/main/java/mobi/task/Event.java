package mobi.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 * <p>
 * Inherits from generic class Task.
 * Stores additional temporal information, start and end dates.
 * </p>
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Initializes a new Event task with the given description.
     * Calls superclass constructor to initialize description.
     * Initializes start and end dates.
     *
     * @param desc the description of the task
     * @param start the starting date of the task
     * @param end the deadline date of the task
     */
    public Event(String desc, LocalDateTime start, LocalDateTime end) {
        super(desc);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the start date of the task.
     *
     * @return start date as a LocalDateTime
     */
    public LocalDateTime getStart() {
        return this.start;
    }

    /**
     * Returns the description of the task, alongside
     * completion status and formatted start and end dates.
     *
     * @return task description as a string
     */
    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "] " + super.toString()
                + " (from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"))
                + " to: " + end.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")) + ")";
    }

    /**
     * Returns the description of the task, alongside completion status,
     * in a format suitable for saving to a file.
     * Format: E | isDone | description | start - end
     *
     * @return task description as a string suitable for saving to files
     */
    @Override
    public String toFileString() {
        return String.format("E | %d | %s | %s - %s", isDone ? 1 : 0, super.toString(),
                            start.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")),
                            end.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")));
    }

}
