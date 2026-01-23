import java.util.Scanner;

public class Mobi
{
    public static void main(String[] args) {
        Task[] taskList = new Task[100];
        int count = 0;
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
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < count; i++) {
                        System.out.println((i + 1) + "." + taskList[i].toString());
                    }
                    break;
                case "mark":
                    int num = Integer.parseInt(inputs[1]);
                    taskList[num - 1].markAsDone();
                    break;
                case "unmark":
                    int num2 = Integer.parseInt(inputs[1]);
                    taskList[num2 - 1].markNotDone();
                    break;
                case "todo":
                    System.out.println("Got it. I've added this task: ");
                    taskList[count] = new Todo(inputs[1]);
                    System.out.println(taskList[count].toString());
                    count++;
                    System.out.println("Now you have " + count + " tasks in the list.");
                    break;
                case "deadline":
                    String[] parts = inputs[1].trim().split("/by");
                    System.out.println("Got it. I've added this task: ");
                    taskList[count] = new Deadline(parts[0].trim(), parts[1].trim());
                    System.out.println(taskList[count].toString());
                    count++;
                    System.out.println("Now you have " + count + " tasks in the list.");
                    break;
                case "event":
                    String[] parts2 = inputs[1].trim().split("(/from|/to)");
                    System.out.println("Got it. I've added this task: ");
                    taskList[count] = new Event(parts2[0].trim(), parts2[1].trim(), parts2[2].trim());
                    System.out.println(taskList[count].toString());
                    count++;
                    System.out.println("Now you have " + count + " tasks in the list.");
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
}
