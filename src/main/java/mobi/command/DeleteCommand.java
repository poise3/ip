package mobi.command;

import mobi.exception.MobiException;
import mobi.storage.Storage;
import mobi.task.Task;
import mobi.task.TaskList;
import mobi.ui.Ui;
import java.io.IOException;

/**
 * Represents the delete command.
 */
public class DeleteCommand implements Command {
    private final String numberStr;

    /**
     * Initializes DeleteCommand object with number of task to delete
     *
     * @param num the number of the task to delete, as a string
     */
    public DeleteCommand(String num) {
        this.numberStr = num;
    }

    /**
     * Executes the delete command.
     * <p>
     * Removes the indicated task from the task list, and saves
     * the updated task list to storage.
     * </p>
     *
     * @param tasks the current {@link TaskList}
     * @param ui the {@link Ui} for displaying messages
     * @param store the {@link Storage} for saving tasks
     * @throws MobiException if saving to file fails
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws MobiException {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert store != null : "Storage should not be null";

        int num = parseNumberStr(numberStr, tasks.size());
        Task task = tasks.get(num - 1);

        ui.showMessage("Noted. I've removed this task: ");
        ui.showMessage(task.toString());
        tasks.remove(num - 1);

        try {
            store.saveTasks(tasks.getAll());
        } catch (IOException e) {
            throw new MobiException("File save error :/");
        }
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Parses input number.
     * <p>
     * Checks if input number is valid and returns parsed number.
     * </p>
     *
     * @param numberStr the input string
     * @param taskListSize size of the current task list
     * @throws MobiException if input is invalid (not a number or out of bounds)
     */
    public int parseNumberStr(String numberStr, int taskListSize) throws MobiException {
        int num;

        try {
            num = Integer.parseInt(numberStr);
        } catch (NumberFormatException e) {
            throw new MobiException("I need the task number, please? :/");
        }

        if (num > taskListSize || num < 1) {
            throw new MobiException("That doesn't seem to be a number from the list :/");
        }

        return num;
    }
}
