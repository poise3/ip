package mobi.command;

import mobi.exception.MobiException;
import mobi.parser.DateParser;
import mobi.storage.Storage;
import mobi.task.Deadline;
import mobi.task.TaskList;
import mobi.ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents the deadline command.
 */
public class DeadlineCommand implements Command {
    private final String arguments;

    /**
     * Initializes DeadlineCommand object with task description
     *
     * @param arguments command arguments (description, /by date)
     */
    public DeadlineCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the deadline command.
     * <p>
     * Creates a new Deadline task, saves it to a task list, updates
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
            LocalDateTime by = parseDate(parts[1].trim());

            tasks.add(new Deadline(parts[0].trim(), by));
            store.saveTasks(tasks.getAll());
        } catch (IOException e) {
            throw new MobiException("File save error :/");
        }

        ui.showTaskAdded(tasks.get(tasks.size() - 1).toString(), tasks.size());
    }

    /**
     * Parses deadline task arguments.
     * <p>
     * Checks if inputs are valid, and returns arguments
     * split by /from and /to.
     * </p>
     *
     * @param arguments task arguments
     * @throws MobiException if inputs are invalid (missing /by or missing args)
     */
    public String[] parseArguments(String arguments) throws MobiException {
        if (!arguments.contains("/by")) {
            throw new MobiException("Please specify deadline with '/by' :/");
        }

        String[] parts = arguments.trim().split("/by");
        if (parts.length < 2) {
            throw new MobiException("I need you to specify the deadline :/");
        } else if (parts.length > 2) {
            throw new MobiException("Only write '/by' once please! :/");
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
            throw new MobiException("That's not a valid date format! Please use:\n"
                                    + "'yyyy-MM-dd', 'd/M/yyyy' or 'MMM d yyyy'.\n"
                                    + "Optionally, you may specify the time too :)\n"
                                    + " e.g. 2026-02-20, 23:59\n"
                                    + "Might be a bit complicated I know... :/");
        }
    }
}
