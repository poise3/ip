import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DeadlineCommand implements Command {
    String task;

    public DeadlineCommand(String task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws MobiException {
        if (!task.contains("/by")) throw new MobiException("Please specify deadline with '/by' :)");
        String[] parts = task.trim().split("/by");
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
