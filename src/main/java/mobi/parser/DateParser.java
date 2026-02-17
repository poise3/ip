package mobi.parser;

import mobi.command.Command;
import mobi.exception.MobiException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

/**
 * Handles parsing of dates.
 * <p>
 * DateParser is a utility class that provides a flexible DateTimeFormatter
 * used in the Mobi ChatBot application to parse dates in certain formats.
 * </p>
 */
public class DateParser {
    /** Formatter that supports multiple date/time patterns. */
    private static final DateTimeFormatter FORMATTER =
            new DateTimeFormatterBuilder()
                    .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm"))
                    .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd, HHmm"))
                    .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy, HH:mm"))
                    .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy, HHmm"))
                    .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy"))
                    .appendOptional(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"))
                    .appendOptional(DateTimeFormatter.ofPattern("MMM d yyyy, HHmm"))
                    .appendOptional(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .toFormatter(Locale.ENGLISH);

    /**
     * Parses a date/time string into a {@link LocalDateTime} object.
     * <p>
     * If the time is not provided, HHmm defaults to 0000
     * </p>
     *
     * @param input the date/time string to parse
     * @return a {@link LocalDateTime} representing the parsed date and time
     * @throws java.time.format.DateTimeParseException if the input is invalid
     */
    public static LocalDateTime parse(String input) {
        return LocalDateTime.parse(input.trim(), FORMATTER);
    }

}
