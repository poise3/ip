import java.io.IOException;

public class DeleteCommand implements Command {
    String number;

    public DeleteCommand(String num) {
        this.number = num;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws MobiException {
        try {
            int num = Integer.parseInt(number);
            if (num <= tasks.size() && num > 0) {
                System.out.println("Noted. I've removed this task:: ");
                System.out.println(tasks.get(num - 1).toString());
                tasks.remove(num - 1);
            } else {
                throw new MobiException("Please enter a number from the list :)");
            }
        } catch (NumberFormatException e) {
            throw new MobiException("For deletion, please enter a number :)");
        }

        try {
            store.saveTasks(tasks.getAll());
        } catch (IOException e) {
            throw new MobiException("File save error :/");
        }
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
    }
}
