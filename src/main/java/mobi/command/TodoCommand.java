package mobi.command;

import mobi.exception.MobiException;
import mobi.storage.Storage;
import mobi.task.TaskList;
import mobi.task.Todo;
import mobi.ui.Ui;

import java.io.IOException;

/**
 * Represents the todo command.
 */
public class TodoCommand implements Command {
    private final String description;

    /**
     * Initializes TodoCommand object with task description
     *
     * @param description task description
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the todo command.
     * <p>
     * Creates a new Todo task, saves it to a task list, updates
     * storage and displays confirmation message to user.
     * </p>
     *
     * @param tasks the current {@link TaskList}
     * @param ui the {@link Ui} for displaying messages
     * @param store the {@link Storage} for saving tasks
     * @throws MobiException if the input is not a valid number
     *                       or if saving to file fails
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws MobiException {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert store != null : "Storage should not be null";

        try {
            tasks.add(new Todo(description));
            store.saveTasks(tasks.getAll());
        } catch (IOException e) {
            throw new MobiException("File save error :/");
        }
        ui.showMessage("Got it. I've added this task: ");
        ui.showMessage(tasks.get(tasks.size() - 1).toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
    }
}
