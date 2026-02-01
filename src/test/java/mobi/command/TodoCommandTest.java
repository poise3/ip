package mobi.command;

import mobi.task.Task;
import mobi.task.TaskList;
import mobi.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class TodoCommandTest {
    @Test
    public void execute_ValidInput_addsTodo(){
        TaskList taskList = new TaskList();
        Task task = new Todo("a");
        taskList.add(task);

        assertInstanceOf(Todo.class, taskList.get(0));
    }
}
