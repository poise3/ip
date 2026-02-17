package mobi.command;

import mobi.exception.MobiException;
import mobi.storage.Storage;
import mobi.task.Sorter;
import mobi.task.Task;
import mobi.task.TaskList;
import mobi.ui.Ui;

import java.io.IOException;

/**
 * Represents the sort command.
 */
public class SortCommand implements Command {
    private final String sortCriteria;

    /**
     * Initializes SortCommand object with sorting criteria
     *
     * @param criteria the criteria to sort the task list off
     */
    public SortCommand(String criteria) {
        this.sortCriteria = criteria;
    }

    /**
     * Executes the sort command.
     * <p>
     * Sorts task list based off user input, and saves
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

        Sorter sorter = new Sorter(tasks.getAll());
        sorter.sortBy(sortCriteria);

        try {
            store.saveTasks(tasks.getAll());
        } catch (IOException e) {
            throw new MobiException("File save error :/");
        }
        ui.showMessage("I have sorted your tasks by " + sortCriteria + " :D");
    }
}
