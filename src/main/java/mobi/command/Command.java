package mobi.command;

import mobi.exception.MobiException;
import mobi.storage.Storage;
import mobi.task.TaskList;
import mobi.ui.Ui;

public interface Command {
    void execute(TaskList tasks, Ui ui, Storage store) throws MobiException;
}