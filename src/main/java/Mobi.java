import java.util.Scanner;

public class Mobi
{
    private static final Task[] taskList = new Task[100];
    private static int count = 0;

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
                    default:
                        System.out.println("please enter valid command");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("You need to add a description for your task :)");
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
        if (count == 0) throw new MobiException("You currently have no tasks :)");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + "." + taskList[i].toString());
        }
    }

    private static void markHandler(String number) throws MobiException {
        try {
            int num = Integer.parseInt(number);
            taskList[num - 1].markAsDone();
        } catch (NumberFormatException e) {
            throw new MobiException("For marking, please enter a number :)");
        }
    }

    private static void unmarkHandler(String number) throws MobiException {
        try {
            int num = Integer.parseInt(number);
            taskList[num - 1].markNotDone();
        } catch (NumberFormatException e) {
            throw new MobiException("For unmarking, please enter a number :)");
        }
    }

    private static void todoHandler(String task) throws MobiException {
        System.out.println("Got it. I've added this task: ");
        taskList[count] = new Todo(task);
        System.out.println(taskList[count].toString());
        count++;
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    private static void deadlineHandler(String task) throws MobiException {
        if (!task.contains("/by")) throw new MobiException("Please specify deadline with '/by' :)");
        String[] parts = task.trim().split("/by");
        try {
            taskList[count] = new Deadline(parts[0].trim(), parts[1].trim());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MobiException("You need to specify the deadline :)");
        }
        System.out.println("Got it. I've added this task: ");
        System.out.println(taskList[count].toString());
        count++;
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    private static void eventHandler(String task) throws MobiException {
        if (!task.contains("/from") || !task.contains("/to")) throw new MobiException("Please specify from/to dates with '/from' and '/to' :)");
        String[] parts = task.trim().split("(/from|/to)");
        try {
            taskList[count] = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MobiException("You need to specify the start/end dates :)");
        }
        System.out.println("Got it. I've added this task: ");
        System.out.println(taskList[count].toString());
        count++;
        System.out.println("Now you have " + count + " tasks in the list.");
    }
}
