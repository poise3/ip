package mobi.storage;

import mobi.task.*;
import mobi.parser.DateParser;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles loading and saving tasks to a file.
 * <p>
 * The Storage class facilitates hard disk storage for the Mobi ChatBot.
 * It can load tasks from a file into a list, and save a list of tasks back to the file.
 * </p>
 */
public class Storage {
    private final Path filePath;

    /**
     * Initializes Storage with the specified file path.
     *
     * @param filePath the path to the hard disk file where tasks are stored
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Loads tasks from the hard disk file into an ArrayList.
     * <p>
     * If the file does not exist, it will be created along the specified path
     * Each line in the file is parsed into a Task accordingly and saved to a task list.
     * </p>
     *
     * @return an ArrayList of parsed tasks loaded from the file
     * @throws IOException if there is an error reading the file or creating it
     */
    public ArrayList<Task> loadTasks() throws IOException {

        ArrayList<Task> tasks = new ArrayList<>();
        if (!Files.exists(filePath)) {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
            return tasks;
        }

        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                String[] parts = line.split(" \\||- ");
                String taskType = parts[0].trim();
                Task task;
                switch (taskType) {
                    case "T" -> task = new Todo(parts[2].trim());
                    case "D" -> task = new Deadline(parts[2].trim(), DateParser.parse(parts[3].trim()));
                    case "E" -> task = new Event(parts[2].trim(), DateParser.parse(parts[3].trim()),
                                    DateParser.parse(parts[4].trim()));
                    default -> {
                        continue;
                    }
                }
                if (parts[1].trim().equals("1")) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("IO error: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves a list of tasks to the storage file.
     * <p>
     * Each task is converted to its file string representation before writing
     * </p>
     *
     * @param tasks the list of tasks to save
     * @throws IOException if there is an error writing to the file
     */
    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        List<String> fileStrings = new ArrayList<>();
        for (Task task : tasks) {
            fileStrings.add(task.toFileString());
        }

        try {
            Files.write(filePath, fileStrings);
        } catch (IOException e) {
            System.out.println("IO error: " + e.getMessage());
        }
    }
}
