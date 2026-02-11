package mobi.command;

import mobi.exception.MobiException;
import mobi.parser.DateParser;
import mobi.storage.Storage;
import mobi.task.Deadline;
import mobi.task.Event;
import mobi.task.Task;
import mobi.task.TaskList;
import mobi.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents the search command.
 */
public class SearchCommand implements Command {
    private final String date;

    /**
     * Initializes SearchCommand object with date to find
     *
     * @param date the date to search
     */
    public SearchCommand(String date) {
        this.date = date;
    }

    /**
     * Executes the search command.
     * <p>
     * Loops through the task list, displaying every
     * task that matches the input date.
     * </p>
     *
     * @param tasks the current {@link TaskList}
     * @param ui the {@link Ui} for displaying messages
     * @param store the {@link Storage} for saving tasks
     * @throws MobiException if the input is an invalid date
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws MobiException {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert store != null : "Storage should not be null";

        if (tasks.isEmpty()) {
            throw new MobiException("You currently have no tasks :)");
        }

        try {
            LocalDate findDate = DateParser.parse(date).toLocalDate();
            ui.showMessage("Here are the tasks on this specific date:");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                LocalDate taskDate = null;
                if (task instanceof Deadline d) {
                    taskDate = d.getBy().toLocalDate();
                } else if (task instanceof Event e) {
                    taskDate = e.getStart().toLocalDate();
                }
                if (taskDate != null && taskDate.isEqual(findDate)) {
                    ui.showMessage((i + 1) + "." + task.toString());
                }
            }
        } catch (DateTimeParseException e) {
            throw new MobiException("You entered the date in the wrong format! Please follow yyyy-MM-dd or d/M/yyyy :D");
        }
    }
}
