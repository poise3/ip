package mobi;
import mobi.command.Command;
import mobi.exception.MobiException;
import mobi.parser.Parser;
import mobi.storage.Storage;
import mobi.task.TaskList;
import mobi.ui.Ui;
import java.io.IOException;

public class Mobi
{
    private final Ui ui;
    private TaskList tasks;
    private final Storage store;
    private final Parser parser;

    public Mobi(String filePath) {
        ui = new Ui();
        store = new Storage(filePath);
        parser = new Parser();

        try {
            tasks = new TaskList(store.loadTasks());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        String userInput = ui.readCommand();
        while (!userInput.equals("bye")) {
            ui.showLine();
            try {
                Command command = parser.parse(userInput);
                command.execute(tasks, ui, store);
            } catch (MobiException e) {
                ui.showError(e.getMessage());
            }
            ui.showLine();
            userInput = ui.readCommand();
        }
        ui.showExit();
    }

    public static void main(String[] args) {
        new Mobi("./data/tasklist.txt").run();
    }

}
