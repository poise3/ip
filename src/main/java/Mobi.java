import java.util.Scanner;

public class Mobi
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Mobi\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");

        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println("____________________________________________________________\n" +
                     userInput + "\n" +
                    "____________________________________________________________\n");
            userInput = scanner.nextLine();
        }
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }
}
