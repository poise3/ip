package mobi.parser;

import mobi.command.Command;
import mobi.exception.MobiException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parse_NoDesc_throwsException(){
        Parser p = new Parser();

        try {
            Command command = p.parse("mark");
        } catch (MobiException e) {
            assertEquals("You need to add a description for your task :)", e.getMessage());
        }
    }

    @Test
    public void parse_InvalidCommand_throwsException(){
        Parser p = new Parser();

        try {
            Command command = p.parse("a");
        } catch (MobiException e) {
            assertEquals("please enter valid command", e.getMessage());
        }
    }
}
