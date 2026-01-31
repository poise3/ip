package mobi.command;

import mobi.exception.MobiException;
import mobi.storage.Storage;
import mobi.task.TaskList;
import mobi.ui.Ui;
import java.io.IOException;

public class MarkCommand implements Command {
    String number;

    public MarkCommand(String input) {
        this.number = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws MobiException {
        try {
            int num = Integer.parseInt(number);
            tasks.get(num - 1).markAsDone();
            store.saveTasks(tasks.getAll());
            ui.showMessage("Nice! I've marked this task as done:");
            ui.showMessage("[" + tasks.get(num - 1).getStatusIcon() + "] " + tasks.get(num - 1).toString());
        } catch (NumberFormatException e) {
            throw new MobiException("For marking, please enter a number :)");
        } catch (IOException e) {
            throw new MobiException("File save error :/");
        }
    }
}
