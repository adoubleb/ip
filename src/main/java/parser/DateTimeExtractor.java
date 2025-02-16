package parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.InvalidCommandException;

/**
 * Provides functionality to extract DateTime values representing events or deadlines from an input string.
 * The class is designed to parse specific patterns for start and end times for events, as well as
 * a deadline DateTime for tasks. It uses predefined patterns to identify and return these DateTime values
 * in a structured manner.
 *
 * The class expects input strings to conform to a specific format:
 * 1. For events: "start: dd-MM-yyyy HH:mm end: dd-MM-yyyy HH:mm".
 * 2. For deadlines: "by: dd-MM-yyyy HH:mm".
 *
 * Features include:
 * - Parsing event strings with start and end DateTime values.
 * - Parsing deadline strings and extracting the corresponding DateTime.
 */
public class DateTimeExtractor {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm");
    private String input;

    /**
     * Constructs a DateTimeExtractor instance with the provided input string.
     * This input is expected to conform to specific date-time patterns that
     * the methods in this class will parse and extract.
     *
     * @param input The string containing date-time information to be extracted and parsed.
     */
    public DateTimeExtractor(String input) {
        this.input = input;
    }

    /**
     * Parses the input string provided to the instance of the class and extracts start and end
     * date-time values in the format "dd-MM-yyyy HH:mm". The input string should conform to the
     * pattern: "start: dd-MM-yyyy HH:mm end: dd-MM-yyyy HH:mm".
     *
     * If the input string matches the expected pattern, the extracted date-time values are
     * returned as a list of `LocalDateTime` objects. The first element in the list corresponds
     * to the start date-time, and the second element corresponds to the end date-time. If the
     * input string does not match the expected pattern, an empty list is returned.
     *
     * @return a list containing two `LocalDateTime` objects (start and end date-time) if the input
     *         matches the expected pattern; otherwise, an empty list.
     */
    public ArrayList<LocalDateTime> eventDateTime() {
        String pattern = "start:\\s*(\\d{2}-\\d{2}-\\d{4})\\s+(\\d{2}:\\d{2})\\s*end:\\s*(\\d{2}-\\d{2}-\\d{4})\\s"
                + "+(\\d{2}:\\d{2})";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(input);
        ArrayList<LocalDateTime> toReturn = new ArrayList<>();

        if (matcher.find()) {
            LocalDateTime start = LocalDateTime.parse(matcher.group(1) + "T" + matcher.group(2), DATE_TIME_FORMATTER);
            LocalDateTime end = LocalDateTime.parse(matcher.group(3) + "T" + matcher.group(4), DATE_TIME_FORMATTER);
            toReturn.add(start);
            toReturn.add(end);
        } else {
            System.out.println(this.input);
            System.out.println("invalid input");
        }
        return toReturn;
    }

    /**
     * Extracts and parses the deadline date and time from the input string.
     * The input string must match a specific pattern, where the date is in the format "dd-MM-yyyy"
     * and the time is in the format "HH:mm", prefixed by "by:". This method compiles and applies the regex
     * pattern to extract the date and time information and converts it into a list of `LocalDateTime` objects.
     *
     * If the input string does not conform to the required format, this method will throw an
     * {@code InvalidCommandException}.
     *
     * @return a list containing one `LocalDateTime` object representing the deadline date and time.
     * @throws InvalidCommandException if the input string does not match the expected date/time format.
     */
    public ArrayList<LocalDateTime> deadlineDateTime() throws InvalidCommandException {
        String pattern = "by:\\s*(\\d{2}-\\d{2}-\\d{4})\\s+(\\d{2}:\\d{2})";

        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(this.input);
        ArrayList<LocalDateTime> toReturn = new ArrayList<>();

        if (matcher.find()) {
            toReturn.add(LocalDateTime.parse(matcher.group(1) + "T" + matcher.group(2), DATE_TIME_FORMATTER));
        } else {
            throw new InvalidCommandException("Invalid Date/Time Format");
        }
        return toReturn;
    }


}

