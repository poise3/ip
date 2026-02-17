package mobi.task;

import mobi.Mobi;
import mobi.exception.MobiException;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Represents a task list sorter
 */
public class Sorter {
    private final ArrayList<Task> tasks;

    /**
     * Initializes a Sorter with a task list.
     *
     * @param tasks the list of tasks
     */
    public Sorter(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Sorts task list based off user input criteria
     *
     * @param criteria sorting criteria
     */
    public void sortBy(String criteria) throws MobiException {
        switch (criteria.toLowerCase()) {
            case "date" -> sortByDate();
            case "name" -> sortByName();
            case "type" -> sortByType();
            default -> throw new MobiException("Invalid sort criteria (please enter date, name or type) :)");
        }
    }

    /**
     * Sorts task list based off date, ordering tasks with no dates last
     */
    public void sortByDate() {
        tasks.sort(Comparator.comparing(task -> {
            if (task instanceof Deadline) {
                return ((Deadline) task).getBy();
            } else if (task instanceof Event) {
                return ((Event) task).getStart();
            } else {
                return null;
            }
        }, Comparator.nullsLast(Comparator.naturalOrder())));
    }

    /**
     * Sorts task list based off name (a - z)
     */
    public void sortByName() {
        tasks.sort(Comparator.comparing(Task::getDesc));
    }

    /**
     * Sorts task list based off type (todo, deadline, then event)
     */
    public void sortByType() {
        tasks.sort(Comparator.comparingInt(task -> {
            if (task instanceof Todo) {
                return 1;
            } else if (task instanceof Deadline) {
                return 2;
            } else if (task instanceof Event) {
                return 3;
            } else {
                return 4;
            }
        }));
    }
}
