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
     * @param userInput the raw user input string
     * @return a {@link Command} object corresponding to the user input
     * @throws MobiException if user input command is invalid (not part of the specified)
     */
    public Command parse(String userInput) throws MobiException {
        assert userInput != null: "Parser input should not be null";
        String[] inputs = splitInput(userInput);
        String command = inputs[0].toLowerCase();

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
            case "bye" -> new ByeCommand();
            case "sort"-> new SortCommand(inputs[1]);
            default -> throw new MobiException("I don't understand your command :/");
        };
    }

    /**
     * Splits input into command and arguments.
     * <p>
     * Checks if inputs are valid, and returns inputs
     * split by white space.
     * </p>
     *
     * @param userInput user input including command and arguments
     * @throws MobiException if user input does not contain arguments
     */
    public String[] splitInput(String userInput) throws MobiException {
        String[] inputs = userInput.trim().split(" ", 2);
        String command = inputs[0].toLowerCase();

        if (Set.of("mark", "unmark", "todo", "deadline", "event", "delete").contains(command) && inputs.length < 2) {
            throw new MobiException("I need a description for the task :/");
        }

        return inputs;
    }

}
