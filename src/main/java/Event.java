public class Event extends Task {
    String start;
    String end;

    public Event(String desc, String start, String end) {
        super(desc);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String toFileString() {
        return String.format("E | %d | %s | %s - %s", isDone ? 1 : 0, description, start, end);
    }

}
