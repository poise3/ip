package mobi.command;

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
    String task;

    /**
     * Initializes EventCommand object with task description
     *
     * @param task the task description
     */
    public EventCommand(String task) {
        this.task = task;
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
     * @throws MobiException if inputs are invalid (wrong argument/date formats)
     *                       or if saving to file fails
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws MobiException {
        if (!task.contains("/from") || !task.contains("/to")) throw new MobiException("Please specify from/to dates with '/from' and '/to' :)");
        String[] parts = task.trim().split("(/from|/to)");
        if (parts.length < 3) {
            throw new MobiException("You need to specify the start/end dates :)");
        } else if (parts.length > 3) {
            throw new MobiException("Invalid input (only write /from and /end once please!) :)");
        }

        try {
            LocalDateTime start = DateParser.parse(parts[1].trim());
            LocalDateTime end = DateParser.parse(parts[2].trim());
            if (end.isBefore(start)) {
                throw new MobiException("Invalid dates: end date should be after start date >:(");
            }
            tasks.add(new Event(parts[0].trim(), start, end));
            store.saveTasks(tasks.getAll());
        } catch (IOException e) {
            throw new MobiException("File save error :/");
        } catch (DateTimeParseException e) {
            throw new MobiException("You entered the dates in the wrong format! Please follow yyyy-MM-dd or d/M/yyyy :D");
        }
        ui.showMessage("Got it. I've added this task: ");
        ui.showMessage(tasks.get(tasks.size() - 1).toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
    }
}
