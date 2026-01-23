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

            System.out.println("____________________________________________________________\n");
            userInput = scanner.nextLine();
        }

        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }

    private static void listHandler() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + "." + taskList[i].toString());
        }
    }

    private static void markHandler(String number) {
        int num = Integer.parseInt(number);
        taskList[num - 1].markAsDone();
    }

    private static void unmarkHandler(String number) {
        int num = Integer.parseInt(number);
        taskList[num - 1].markNotDone();
    }

    private static void todoHandler(String task) {
        System.out.println("Got it. I've added this task: ");
        taskList[count] = new Todo(task);
        System.out.println(taskList[count].toString());
        count++;
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    private static void deadlineHandler(String task) {
        String[] parts = task.trim().split("/by");
        System.out.println("Got it. I've added this task: ");
        taskList[count] = new Deadline(parts[0].trim(), parts[1].trim());
        System.out.println(taskList[count].toString());
        count++;
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    private static void eventHandler(String task) {
        String[] parts2 = task.trim().split("(/from|/to)");
        System.out.println("Got it. I've added this task: ");
        taskList[count] = new Event(parts2[0].trim(), parts2[1].trim(), parts2[2].trim());
        System.out.println(taskList[count].toString());
        count++;
        System.out.println("Now you have " + count + " tasks in the list.");
    }
}
