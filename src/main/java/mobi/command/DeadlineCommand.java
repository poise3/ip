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
     * @throws MobiException if inputs are invalid (wrong argument/date formats)
     *                       or if saving to file fails
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws MobiException {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert store != null : "Storage should not be null";

        if (!arguments.contains("/by")) {
            throw new MobiException("Please specify deadline with '/by' :)");
        }
        String[] parts = arguments.trim().split("/by");
        if (parts.length < 2) {
            throw new MobiException("You need to specify the deadline :)");
        } else if (parts.length > 2) {
            throw new MobiException("Invalid input (only write /by deadline once please!) :)");
        }

        try {
            LocalDateTime by = DateParser.parse(parts[1].trim());
            tasks.add(new Deadline(parts[0].trim(), by));
            store.saveTasks(tasks.getAll());
        } catch (IOException e) {
            throw new MobiException("File save error :/");
        } catch (DateTimeParseException e) {
            throw new MobiException("You entered the date in the wrong format! Please follow yyyy-MM-dd or d/M/yyyy :D");
        }
        ui.showMessage("Got it. I've added this task: ");
        ui.showMessage(tasks.get(tasks.size() - 1).toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
    }
}
