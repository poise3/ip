package mobi.command;

import mobi.exception.MobiException;
import mobi.storage.Storage;
import mobi.task.TaskList;
import mobi.ui.Ui;

/**
 * Represents the list command.
 */
public class ListCommand implements Command {
    /**
     * Executes the list command.
     * <p>
     * Loops through the task list, displaying every
     * task.
     * </p>
     *
     * @param tasks the current {@link TaskList}
     * @param ui the {@link Ui} for displaying messages
     * @param store the {@link Storage} for saving tasks
     * @throws MobiException if task list is empty
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws MobiException {
        if (tasks.isEmpty()) throw new MobiException("You currently have no tasks :)");
        ui.showMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            ui.showMessage((i + 1) + "." + tasks.get(i));
        }
    }
}
