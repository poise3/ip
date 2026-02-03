package mobi.command;

import mobi.exception.MobiException;
import mobi.storage.Storage;
import mobi.task.Task;
import mobi.task.TaskList;
import mobi.ui.Ui;

/**
 * Represents the find command.
 */
public class FindCommand implements Command {
    private final String description;

    /**
     * Constructs an UnmarkCommand for the specified task number.
     *
     * @param description the task description
     */
    public FindCommand(String description) {
        this.description = description;
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
            Task task = tasks.get(i);
            if (task.getDesc().contains(this.description)) {
                ui.showMessage((i + 1) + "." + task);
            }
        }
    }
}