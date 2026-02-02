package mobi.command;

import mobi.exception.MobiException;
import mobi.storage.Storage;
import mobi.task.TaskList;
import mobi.ui.Ui;

/**
 * Represents the find command.
 */
public class FindCommand implements Command {
    private final String task;

    /**
     * Constructs an UnmarkCommand for the specified task number.
     *
     * @param task the task description
     */
    public FindCommand(String task) {
        this.task = task;
    }

    /**
     * Executes the find command.
     * <p>
     * Loops through the task list and displays every
     * task whose description contains the input string.
     * </p>
     *
     * @param tasks the current {@link TaskList}
     * @param ui the {@link Ui} for displaying messages
     * @param store the {@link Storage} for saving tasks
     * @throws MobiException task list is empty
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws MobiException {
        if (tasks.isEmpty()) {
            throw new MobiException("You currently have no tasks :)");
        }

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDesc().contains(this.task)) {
                ui.showMessage((i + 1) + "." + tasks.get(i));
            }
        }
    }
}