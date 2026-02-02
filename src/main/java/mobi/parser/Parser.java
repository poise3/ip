package mobi.parser;

import mobi.command.*;
import mobi.exception.MobiException;

import java.util.Set;

/**
 * Handles parsing of user inputs.
 * <p>
 * The Parser class validates and parses user input before
 * creating a corresponding {@link Command} object to return to the Mobi Chatbot.
 * Throws a {@link MobiException} if the input is invalid or incomplete.
 * </p>
 */
public class Parser {

    /**
     * Parses raw user input string and returns the corresponding {@link Command}.
     * <p>
     * Splits input by whitespace into command and arguments. Throws {@link MobiException}
     * if the command and arguments are invalid or if they are missing.
     * </p>
     *
     * @param input the raw user input string
     * @return a {@link Command} object corresponding to the user input
     * @throws MobiException if the input is invalid or missing required arguments
     */
    public Command parse(String input) throws MobiException {
        String[] inputs = input.trim().split(" ", 2);
        String command = inputs[0].toLowerCase();;

        if (Set.of("mark", "unmark", "todo", "deadline", "event", "delete").contains(command) && inputs.length < 2) {
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
