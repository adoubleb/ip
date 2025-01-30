package parser;

import exceptions.InvalidCommandException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.*;


public class ExtractDateTime {
    private String input;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm");

    public ExtractDateTime(String input) {
        this.input = input;
    }
    public ArrayList<LocalDateTime> eventDateTime()  {
        String pattern = "start:\\s(\\d{2}-\\d{2}-\\d{4})\\s(\\d{2}:\\d{2})\\s+end:\\s(\\d{2}-\\d{2}-\\d{4})\\s(\\d{2}:\\d{2})";

        // Compile the pattern
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(input);
        ArrayList<LocalDateTime> toReturn = new ArrayList<>();

        if (matcher.find()) {
            // Extract the captured dates and times from groups
            LocalDateTime start = LocalDateTime.parse(matcher.group(1) + "T" + matcher.group(2), DATE_TIME_FORMATTER);
            LocalDateTime end = LocalDateTime.parse(matcher.group(3) + "T" + matcher.group(4), DATE_TIME_FORMATTER);
            toReturn.add(start);
            toReturn.add(end);
        } else {
            System.out.println("invalid input");
        }
        return toReturn;
    }

    public ArrayList<LocalDateTime> deadlineDateTime() throws InvalidCommandException {
        String pattern = "by:\\s(\\d{2}-\\d{2}-\\d{4})\\s(\\d{2}:\\d{2})";

        // Compile the pattern
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(this.input);
        ArrayList<LocalDateTime> toReturn = new ArrayList<>();

        if (matcher.find()) {
            // Extract the captured dates and times from groups
            toReturn.add(LocalDateTime.parse(matcher.group(1) + "T" + matcher.group(2), DATE_TIME_FORMATTER));
        } else {
            throw new InvalidCommandException("Invalid Date/Time Format");
        }
        return toReturn;
    }


}

