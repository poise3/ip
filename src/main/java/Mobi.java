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
            System.out.println("____________________________________________________________");
            if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + "." + "[" + taskList[i].getStatusIcon() + "] " + taskList[i].getName());
                }
            } else if (userInput.matches("mark \\d+")) {
                String[] parts = userInput.split(" ");
                int num = Integer.parseInt(parts[1]);
                taskList[num - 1].markAsDone();
            } else if (userInput.matches("unmark \\d+")) {
                String[] parts = userInput.split(" ");
                int num = Integer.parseInt(parts[1]);
                taskList[num - 1].markNotDone();
            } else {
                System.out.println("added: " + userInput);
                taskList[count] = new Task(userInput);
                count++;
            }
            System.out.println("____________________________________________________________\n");
            userInput = scanner.nextLine();
        }
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }
}
