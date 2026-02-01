package mobi.command;

import mobi.task.Task;
import mobi.task.TaskList;
import mobi.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarkCommandTest {
    @Test
    public void execute_ValidTask_marksTask(){
        TaskList taskList = new TaskList();
        Task task = new Todo("a");
        taskList.add(task);
        taskList.get(0).markAsDone();

        assertEquals("X", taskList.get(0).getStatusIcon());
    }
}
