package mobi.command;

import mobi.exception.MobiException;
import mobi.storage.Storage;
import mobi.task.TaskList;
import mobi.ui.Ui;

import java.io.IOException;

public class UnmarkCommand implements Command {
    private final String number;

    public UnmarkCommand(String input) {
        this.number = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws MobiException {
        try {
            int num = Integer.parseInt(number);
            tasks.get(num - 1).markNotDone();
            store.saveTasks(tasks.getAll());
            ui.showMessage("OK, I've marked this task as not done yet:");
            ui.showMessage("[" + tasks.get(num - 1).getStatusIcon() + "] " + tasks.get(num - 1).toString());
        } catch (NumberFormatException e) {
            throw new MobiException("For marking, please enter a number :)");
        } catch (IOException e) {
            throw new MobiException("File save error :/");
        }
    }
}
