package mobi.command;

import mobi.exception.MobiException;
import mobi.storage.Storage;
import mobi.task.TaskList;
import mobi.ui.Ui;

/**
 * Interface representing a generic Command.
 * <p>
 * Each command is executable.
 * Subclasses should implement their own methods of
 * execution accordingly.
 * </p>
 */
public interface Command {
    void execute(TaskList tasks, Ui ui, Storage store) throws MobiException;
}