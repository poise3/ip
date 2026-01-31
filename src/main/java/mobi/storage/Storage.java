package mobi.storage;

import mobi.task.*;
import mobi.parser.DateParser;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;


public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

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
