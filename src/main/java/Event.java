import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String desc, LocalDateTime start, LocalDateTime end) {
        super(desc);
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")) + " to: "
                        + end.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")) + ")";
    }

    @Override
    public String toFileString() {
        return String.format("E | %d | %s | %s - %s", isDone ? 1 : 0, description, start.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")),
                        end.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")));
    }

}
