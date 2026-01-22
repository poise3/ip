import java.util.Scanner;

public class Mobi
{
    public static void main(String[] args) {
        String[] mem = new String[100];
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
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + "." + mem[i]);
                }
            } else {
                System.out.println("added: " + userInput);
                mem[count] = userInput;
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
