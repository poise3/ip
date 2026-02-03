package mobi.command;

import mobi.exception.MobiException;
import mobi.storage.Storage;
import mobi.task.Task;
import mobi.task.TaskList;
import mobi.ui.Ui;

import java.io.IOException;

/**
 * Represents the unmark command.
 */
public class UnmarkCommand implements Command {
    private final String number;

    /**
     * Initializes UnmarkCommand object with number of task to unmark
     *
     * @param input the number of the task to unmark, as a string
     */
    public UnmarkCommand(String input) {
        this.number = input;
    }

    /**
     * Executes the unmark command.
     * <p>
     * Marks the specified task as not done, saves the updated task list
     * to storage, and displays a confirmation message to the user.
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
        try {
            int num = Integer.parseInt(number);
            Task task = tasks.get(num - 1);
            task.markNotDone();
            store.saveTasks(tasks.getAll());
            ui.showMessage("OK, I've marked this task as not done yet:");
            ui.showMessage("[" + task.getStatusIcon() + "] " + task.toString());
        } catch (NumberFormatException e) {
            throw new MobiException("For marking, please enter a number :)");
        } catch (IOException e) {
            throw new MobiException("File save error :/");
        }
    }
}
