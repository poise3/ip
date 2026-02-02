package mobi.command;

import mobi.exception.MobiException;
import mobi.parser.DateParser;
import mobi.storage.Storage;
import mobi.task.Deadline;
import mobi.task.Event;
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
     * Initializes SearchCommand object with task description
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
        if (tasks.isEmpty()) {
            throw new MobiException("You currently have no tasks :)");
        }

        try {
            LocalDate tDate = DateParser.parse(date).toLocalDate();
            ui.showMessage("Here are the tasks on this specific date:");
            for (int i = 0; i < tasks.size(); i++) {
                LocalDate lDate = null;
                if (tasks.get(i) instanceof Deadline d) {
                    lDate = d.getBy().toLocalDate();
                } else if (tasks.get(i) instanceof Event e) {
                    lDate = e.getStart().toLocalDate();
                }
                if (lDate != null && lDate.isEqual(tDate)) {
                    ui.showMessage((i + 1) + "." + tasks.get(i).toString());
                }
            }
        } catch (DateTimeParseException e) {
            throw new MobiException("You entered the date in the wrong format! Please follow yyyy-MM-dd or d/M/yyyy :D");
        }
    }
}
