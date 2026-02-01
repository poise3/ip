package mobi.command;

import mobi.parser.DateParser;
import mobi.task.Deadline;
import mobi.task.Event;
import mobi.task.Task;
import mobi.task.TaskList;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchCommandTest {
    @Test
    public void execute_ValidInput_findsDate(){
        DateTimeFormatter FORMATTER =
                new DateTimeFormatterBuilder()
                        .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")).toFormatter(Locale.ENGLISH);
        LocalDateTime by = LocalDateTime.parse("2026-02-01 0000", FORMATTER);
        TaskList taskList = new TaskList();
        Task task = new Deadline("a", by);
        taskList.add(task);

        LocalDate tDate = by.toLocalDate();
        LocalDate lDate = null;
        if (taskList.get(0) instanceof Deadline d) {
            lDate = d.getBy().toLocalDate();
        }
        assertTrue(lDate.isEqual(tDate));
    }
}
