import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    protected LocalDateTime  by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (by: " +
                    by.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")) + ")";
    }

    @Override
    public String toFileString() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description,
                        by.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")));
    }
}