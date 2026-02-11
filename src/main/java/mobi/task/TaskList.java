package mobi.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * <p>
 * Stores an {@link ArrayList} of {@link Task} objects and provides
 * methods to manipulate and access the tasks.
 * </p>
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Initializes a TaskList with a list of tasks.
     *
     * @param taskList the initial list of tasks, loaded from a txt file
     */
    public TaskList(ArrayList<Task> taskList) {
        assert taskList != null: "Loaded task list should not be null";
        this.tasks = taskList;
    }

    /**
     * Initializes TaskList as an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to add
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index the index of the task to retrieve
     * @return the task at the given index
     */
    public Task get(int index) {
        assert index >= 0 && index < tasks.size(): "Input index is not valid (out of bounds)";
        return tasks.get(index);
    }

    /**
     * Removes the task at the specified index.
     *
     * @param index the index of the task to remove
     */
    public void remove(int index) {
        assert index >= 0 && index < tasks.size(): "Input index is not valid (out of bounds)";
        this.tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the internal list of tasks.
     *
     * @return an ArrayList containing all tasks
     */
    public ArrayList<Task> getAll() {
        return tasks;
    }

    /**
     * Checks whether the task list is empty.
     *
     * @return true if the task list contains no tasks, false otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
