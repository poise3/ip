package mobi.command;

import javafx.application.Platform;
import mobi.exception.MobiException;
import mobi.storage.Storage;
import mobi.task.TaskList;
import mobi.ui.Ui;

/**
 * Represents the bye command.
 */
public class ByeCommand implements Command {
    /**
     * Executes the bye command.
     * <p>
     * Displays goodbye message, and exits GUI
     * </p>
     *
     * @param tasks the current {@link TaskList}
     * @param ui the {@link Ui} for displaying messages
     * @param store the {@link Storage} for saving tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert store != null : "Storage should not be null";

        ui.showMessage("Bye!");
        Platform.exit();
    }
}
