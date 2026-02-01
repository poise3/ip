package mobi.command;

import mobi.task.Event;
import mobi.task.Task;
import mobi.task.TaskList;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class EventCommandTest {
    @Test
    public void execute_ValidInput_addsEvent(){
        DateTimeFormatter FORMATTER =
                new DateTimeFormatterBuilder()
                        .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")).toFormatter(Locale.ENGLISH);
        LocalDateTime by = LocalDateTime.parse("2026-02-01 0000", FORMATTER);
        TaskList taskList = new TaskList();
        Task task = new Event("a", by, by);
        taskList.add(task);

        assertInstanceOf(Event.class, taskList.get(0));
    }
}
