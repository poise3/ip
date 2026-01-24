import java.util.Scanner;
import java.util.ArrayList;
import java.util.Set;

public class Mobi
{
    private static final ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Mobi\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");

        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            String[] inputs = userInput.trim().split(" ", 2);
            String command = inputs[0];
            System.out.println("____________________________________________________________");

            if (Set.of("mark","unmark","todo","deadline","event","delete").contains(command) && inputs.length < 2) {
                System.out.println("You need to add a description for your task :)");
                userInput = scanner.nextLine();
                continue;
            }

            try {
                switch (command) {
                    case "list":
                        listHandler();
                        break;
                    case "mark":
                        markHandler(inputs[1]);
                        break;
                    case "unmark":
                        unmarkHandler(inputs[1]);
                        break;
                    case "todo":
                        todoHandler(inputs[1]);
                        break;
                    case "deadline":
                        deadlineHandler(inputs[1]);
                        break;
                    case "event":
                        eventHandler(inputs[1]);
                        break;
                    case "delete":
                        deleteHandler(inputs[1]);
                        break;
                    default:
                        System.out.println("please enter valid command");
                }
            } catch (MobiException e) {
                System.out.println(e.getMessage());
            }


            System.out.println("____________________________________________________________\n");
            userInput = scanner.nextLine();
        }

        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }

    private static void listHandler() throws MobiException {
        if (taskList.isEmpty()) throw new MobiException("You currently have no tasks :)");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i).toString());
        }
    }

    private static void markHandler(String number) throws MobiException {
        try {
            int num = Integer.parseInt(number);
            taskList.get(num - 1).markAsDone();
        } catch (NumberFormatException e) {
            throw new MobiException("For marking, please enter a number :)");
        }
    }

    private static void unmarkHandler(String number) throws MobiException {
        try {
            int num = Integer.parseInt(number);
            taskList.get(num - 1).markNotDone();
        } catch (NumberFormatException e) {
            throw new MobiException("For unmarking, please enter a number :)");
        }
    }

    private static void todoHandler(String task) throws MobiException {
        System.out.println("Got it. I've added this task: ");
        taskList.add(new Todo(task));
        System.out.println(taskList.get(taskList.size() - 1).toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private static void deadlineHandler(String task) throws MobiException {
        if (!task.contains("/by")) throw new MobiException("Please specify deadline with '/by' :)");
        String[] parts = task.trim().split("/by");
        if (parts.length < 2) {
            throw new MobiException("You need to specify the deadline :)");
        } else if (parts.length > 2) {
            throw new MobiException("Invalid input (only write /by deadline once please!) :)");
        }

        taskList.add(new Deadline(parts[0].trim(), parts[1].trim()));

        System.out.println("Got it. I've added this task: ");
        System.out.println(taskList.get(taskList.size() - 1).toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private static void eventHandler(String task) throws MobiException {
        if (!task.contains("/from") || !task.contains("/to")) throw new MobiException("Please specify from/to dates with '/from' and '/to' :)");
        String[] parts = task.trim().split("(/from|/to)");
        if (parts.length < 3) {
            throw new MobiException("You need to specify the start/end dates :)");
        } else if (parts.length > 3) {
            throw new MobiException("Invalid input (only write /from and /end once please!) :)");
        }

        taskList.add(new Event(parts[0].trim(), parts[1].trim(), parts[2].trim()));

        System.out.println("Got it. I've added this task: ");
        System.out.println(taskList.get(taskList.size() - 1).toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private static void deleteHandler(String number) throws MobiException {
        try {
            int num = Integer.parseInt(number);
            if (num <= taskList.size() && num > 0) {
                System.out.println("Noted. I've removed this task:: ");
                System.out.println(taskList.get(num - 1).toString());
                taskList.remove(num - 1);
            } else {
                throw new MobiException("Please enter a number from the list :)");
            }
        } catch (NumberFormatException e) {
            throw new MobiException("For deletion, please enter a number :)");
        }
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}
