package mobi.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void add_taskAdded_sizeIncreases(){
        TaskList taskList = new TaskList();
        Task task = new Todo("a");
        taskList.add(task);

        assertEquals(1, taskList.size());
        assertEquals(task, taskList.get(0));
    }

    @Test
    public void remove_taskRemoved_sizeDecreases(){
        TaskList taskList = new TaskList();
        Task task = new Todo("a");
        taskList.add(task);
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.get(0));

        taskList.remove(0);
        assertEquals(0, taskList.size());
    }
}
