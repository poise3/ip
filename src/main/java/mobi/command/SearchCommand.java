package mobi.command;

import mobi.exception.MobiException;
import mobi.parser.DateParser;
import mobi.storage.Storage;
import mobi.task.Deadline;
import mobi.task.Event;
import mobi.task.TaskList;
import mobi.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class SearchCommand implements Command {
    String date;

    public SearchCommand(String date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws MobiException {
        if (tasks.isEmpty()) throw new MobiException("You currently have no tasks :)");

        try {
            LocalDate tDate = DateParser.parse(date).toLocalDate();
            ui.showMessage("Here are the tasks on this specific date:");
            for (int i = 0; i < tasks.size(); i++) {
                LocalDate lDate = null;
                if (tasks.get(i) instanceof Deadline d) {
                    lDate = d.getBy().toLocalDate();
                } else if (tasks.get(i) instanceof Event e) {
                    lDate = e.getStart().toLocalDate();
                }
                if (lDate != null && lDate.isEqual(tDate)) {
                    ui.showMessage((i + 1) + "." + tasks.get(i).toString());
                }
            }
        } catch (DateTimeParseException e) {
            throw new MobiException("You entered the date in the wrong format! Please follow yyyy-MM-dd or d/M/yyyy :D");
        }
    }
}
