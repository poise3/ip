package mobi;
import mobi.command.Command;
import mobi.exception.MobiException;
import mobi.parser.Parser;
import mobi.storage.Storage;
import mobi.task.TaskList;
import mobi.ui.Ui;
import java.io.IOException;

/**
 * Mobi is a task management ChatBot application that allows users to add, remove
 * and manage their tasks through a text-based interface.
 */
public class Mobi
{
    private final Ui ui;
    private TaskList tasks;
    private final Storage store;
    private final Parser parser;

    /**
     * Initializes Mobi instance.
     * Attempts to load tasks from file path.
     * If loading fails, initializes empty task list.
     * @param filePath the path to the file where tasks are stored
     */
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

    /**
     * Starts the main loop of the application, reading
     * user inputs, executing them and showing output to user.
     */
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

    /**
     * When application runs, creates Mobi instance with
     * specified file path and runs it.
     */
    public static void main(String[] args) {
        new Mobi("./data/tasklist.txt").run();
    }

}
