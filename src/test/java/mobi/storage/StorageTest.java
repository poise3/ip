package mobi.storage;

import mobi.task.Deadline;
import mobi.task.Event;
import mobi.task.TaskList;
import mobi.task.Todo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class StorageTest {
    @Test
    public void loadTasks_validInput_retrievalSucceeds(){
        DateTimeFormatter FORMATTER =
                new DateTimeFormatterBuilder()
                        .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")).toFormatter(Locale.ENGLISH);
        Storage store = new Storage("./data/store_test.txt");
        TaskList tasks = new TaskList();
        TaskList tasks2;
        try {
            Path testFile = Path.of("./data/store_test.txt");
            LocalDateTime by = LocalDateTime.parse("2026-02-01 0000", FORMATTER);
            tasks.add(new Todo("test"));
            tasks.add(new Deadline("test2", by));
            tasks.add(new Event("test3", by, by));
            store.saveTasks(tasks.getAll());

            tasks2 = new TaskList(store.loadTasks());

            assertInstanceOf(Todo.class, tasks2.get(0));
            assertInstanceOf(Deadline.class, tasks2.get(1));
            assertInstanceOf(Event.class, tasks2.get(2));
        } catch (IOException e) {
            System.out.println("IO error: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("DateTimeParse error: " + e.getMessage());
        }
    }

    @Test
    public void saveTasks_validInput_savesToTxt(){
        DateTimeFormatter FORMATTER =
                new DateTimeFormatterBuilder()
                        .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")).toFormatter(Locale.ENGLISH);
        Storage store = new Storage("./data/store_test.txt");
        TaskList tasks = new TaskList();
        try {
            Path testFile = Path.of("./data/store_test.txt");
            LocalDateTime by = LocalDateTime.parse("2026-02-01 0000", FORMATTER);
            tasks.add(new Deadline("test", by));
            store.saveTasks(tasks.getAll());
            List<String> lines = Files.readAllLines(testFile);

            assertEquals("D | 0 | test | Feb 1 2026, 00:00", lines.get(0));
        } catch (IOException e) {
            System.out.println("IO error: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("DateTimeParse error: " + e.getMessage());
        }
    }
}