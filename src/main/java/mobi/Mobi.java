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
    private static final String DEFAULT_FILE_PATH = "./data/tasklist.txt";

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
     * Calls default constructor with JavaFX when launching.
     */
    public Mobi() {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        if (input.isBlank()) {
            return "You said something? :/";
        }

        String response = "invalid";
        try {
            Command command = parser.parse(input);
            command.execute(tasks, ui, store);
            response = ui.getResponse();
        } catch (MobiException e) {
            return e.getMessage();
        }
        return response;
    }

}
