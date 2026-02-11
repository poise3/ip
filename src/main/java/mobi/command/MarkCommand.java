package mobi.command;

import mobi.exception.MobiException;
import mobi.storage.Storage;
import mobi.task.Task;
import mobi.task.TaskList;
import mobi.ui.Ui;
import java.io.IOException;

/**
 * Represents the mark command.
 */
public class MarkCommand implements Command {
    private final String numberStr;

    /**
     * Initializes MarkCommand object with number of task to mark
     *
     * @param input the number of the task to mark, as a string
     */
    public MarkCommand(String input) {
        this.numberStr = input;
    }

    /**
     * Executes the mark command.
     * <p>
     * Marks the specified task as done, saves the updated task list
     * to storage, and displays a confirmation message to the user.
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

        task.markAsDone();
        ui.showMessage("Nice! I've marked this task as done:");
        ui.showMessage("[" + task.getStatusIcon() + "] " + task.toString());

        try {
            store.saveTasks(tasks.getAll());
        } catch (IOException e) {
            throw new MobiException("File save error :/");
        }
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
            throw new MobiException("Please enter a number :)");
        }

        if (num < 1 || num > taskListSize) {
            throw new MobiException("Please enter a number from the list :)");
        }

        return num;
    }
}
