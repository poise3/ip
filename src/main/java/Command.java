public interface Command {
    void execute(TaskList tasks, Ui ui, Storage store) throws MobiException;
}