package mobi.command;

import mobi.exception.MobiException;
import mobi.parser.DateParser;
import mobi.storage.Storage;
import mobi.task.Deadline;
import mobi.task.Task;
import mobi.task.TaskList;
import mobi.ui.Ui;
import java.io.IOException;

/**
 * Represents the delete command.
 */
public class DeleteCommand implements Command {
    private final String number;

    /**
     * Initializes DeleteCommand object with number of task to delete
     *
     * @param num the number of the task to delete, as a string
     */
    public DeleteCommand(String num) {
        this.number = num;
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
     * @throws MobiException if the input is not a valid number
     *                       or if saving to file fails
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws MobiException {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert store != null : "Storage should not be null";

        try {
            int num = Integer.parseInt(number);
            Task task = tasks.get(num - 1);
            if (num <= tasks.size() && num > 0) {
                ui.showMessage("Noted. I've removed this task: ");
                ui.showMessage(task.toString());
                tasks.remove(num - 1);
            } else {
                throw new MobiException("Please enter a number from the list :)");
            }
        } catch (NumberFormatException e) {
            throw new MobiException("For deletion, please enter a number :)");
        }

        try {
            store.saveTasks(tasks.getAll());
        } catch (IOException e) {
            throw new MobiException("File save error :/");
        }
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
    }
}
