package mobi.parser;

import mobi.command.*;
import mobi.exception.MobiException;

import java.util.Set;

public class Parser {

    public Command parse(String input) throws MobiException {
        String[] inputs = input.trim().split(" ", 2);
        String command = inputs[0];

        if (Set.of("mark","unmark","todo","deadline","event","delete").contains(command) && inputs.length < 2) {
            throw new MobiException("You need to add a description for your task :)");
        }

        return switch (command) {
            case "list" -> new ListCommand();
            case "mark" -> new MarkCommand(inputs[1]);
            case "unmark" -> new UnmarkCommand(inputs[1]);
            case "todo" -> new TodoCommand(inputs[1]);
            case "deadline" -> new DeadlineCommand(inputs[1]);
            case "event" -> new EventCommand(inputs[1]);
            case "delete" -> new DeleteCommand(inputs[1]);
            case "search" -> new SearchCommand(inputs[1]);
            case "find" -> new FindCommand(inputs[1]);
            default -> throw new MobiException("please enter valid command");
        };
    }

}
