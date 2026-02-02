package mobi.command;

import mobi.task.Task;
import mobi.task.TaskList;
import mobi.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FindCommandTest {
    @Test
    public void execute_ValidInput_findsTasks(){
        TaskList taskList = new TaskList();
        Task task = new Todo("b");
        Task task2 = new Todo("a 2");
        Task task3 = new Todo("2 ab");
        String find = "a";
        taskList.add(task);
        taskList.add(task2);
        taskList.add(task3);

        assertFalse(taskList.get(0).getDesc().contains(find));
        assertTrue(taskList.get(1).getDesc().contains(find));
        assertTrue(taskList.get(2).getDesc().contains(find));

    }
}
