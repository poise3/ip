package mobi.ui;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);
    private StringBuilder sb = new StringBuilder();

    public void showWelcome() {
        sb.append("Greetings! I'm Mobi :D\n");
        sb.append("Any commands for me?\n");
    }

    public void showExit() {
        sb.append("Bye. Hope to see you again soon!\n");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        sb.append(message).append("\n");
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

    public String getResponse() {
        String response = sb.toString();
        sb.setLength(0);
        return response;
    }
}
