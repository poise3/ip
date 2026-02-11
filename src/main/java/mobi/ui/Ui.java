package mobi.ui;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);
    private final StringBuilder sb = new StringBuilder();
    private static final String WELCOME_MESSAGE = "Greetings! I'm Mobi :D\nAny commands for me?\n";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!\n";

    public void showWelcome() {
        sb.append(WELCOME_MESSAGE);
    }

    public void showExit() {
        sb.append(EXIT_MESSAGE);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        sb.append(message).append("\n");
    }

    public void showLoadingError() {
        sb.append("Error loading tasks :(").append("\n");
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

    public void showTaskAdded(String task, int newSize) {
        this.showMessage("Got it. I've added this task: ");
        this.showMessage(task);
        this.showMessage("Now you have " + newSize + " tasks in the list.");
    }
}
