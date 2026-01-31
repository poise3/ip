package mobi.ui;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm mobi.Mobi\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
    }

    public void showExit() {
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks :(");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showError(String error) {
        System.out.println("Error: " + error);
    }
}
