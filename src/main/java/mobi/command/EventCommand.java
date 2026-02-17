package mobi.command;

import mobi.Mobi;
import mobi.exception.MobiException;
import mobi.parser.DateParser;
import mobi.storage.Storage;
import mobi.task.Deadline;
import mobi.task.Event;
import mobi.task.TaskList;
import mobi.ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents the event command.
 */
public class EventCommand implements Command {
    private final String arguments;

    /**
     * Initializes EventCommand object with task description
     *
     * @param arguments command arguments (description, /from date, /to date)
     */
    public EventCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the event command.
     * <p>
     * Creates a new Event task, saves it to a task list, updates
     * storage and displays confirmation message to user.
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

        String[] parts = parseArguments(arguments);

        try {
            LocalDateTime start = parseDate(parts[1].trim());
            LocalDateTime end = parseDate(parts[2].trim());
            if (end.isBefore(start)) {
                throw new MobiException("Invalid dates: end date should be after start date :/");
            }

            tasks.add(new Event(parts[0].trim(), start, end));
            store.saveTasks(tasks.getAll());
        } catch (IOException e) {
            throw new MobiException("File save error :/");
        }

        ui.showTaskAdded(tasks.get(tasks.size() - 1).toString(), tasks.size());
    }

    /**
     * Parses event task arguments.
     * <p>
     * Checks if inputs are valid, and returns arguments
     * split by /from and /to.
     * </p>
     *
     * @param arguments task arguments
     * @throws MobiException if inputs are invalid (missing from/to or missing args)
     */
    public String[] parseArguments(String arguments) throws MobiException {
        if (!arguments.contains("/from") || !arguments.contains("/to")) {
            throw new MobiException("Please specify from/to dates with '/from' and '/to' :/");
        }

        String[] parts = arguments.trim().split("(/from|/to)");
        if (parts.length < 3) {
            throw new MobiException("You need to specify the from/to dates :/");
        } else if (parts.length > 3) {
            throw new MobiException("Invalid input (only write /from and /to once please!) :/");
        }

        return parts;
    }

    /**
     * Parses date.
     * <p>
     * Checks if input date is in the correct format,
     * before returning the parsed date.
     * </p>
     *
     * @param date task date
     * @throws MobiException if inputs are invalid (wrong date formats)
     */
    public LocalDateTime parseDate(String date) throws MobiException {
        try {
            return DateParser.parse(date);
        } catch (DateTimeParseException e) {
            throw new MobiException("You entered the dates in the wrong format! Please follow yyyy-MM-dd or d/M/yyyy :/");
        }
    }
}
