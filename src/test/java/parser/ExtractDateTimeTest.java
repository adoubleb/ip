package parser;

import exceptions.InvalidCommandException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ExtractDateTimeTest {

    @Test
    void testEventDateTimeWithValidInput() {
        String validInput = "start: 12-10-2023 14:30 end: 13-10-2023 16:30";
        ExtractDateTime extractDateTime = new ExtractDateTime(validInput);

        ArrayList<LocalDateTime> result = extractDateTime.eventDateTime();

        assertEquals(2, result.size());
        assertEquals(LocalDateTime.of(2023, 10, 12, 14, 30), result.get(0)); // Start date/time
        assertEquals(LocalDateTime.of(2023, 10, 13, 16, 30), result.get(1)); // End date/time
    }

    @Test
    void testEventDateTimeWithInvalidInput() {
        String invalidInput = "invalid date string";
        ExtractDateTime extractDateTime = new ExtractDateTime(invalidInput);

        ArrayList<LocalDateTime> result = extractDateTime.eventDateTime();

        assertTrue(result.isEmpty()); // Expecting an empty list for invalid input
    }

    @Test
    void testDeadlineDateTimeWithValidInput() throws InvalidCommandException {
        String validInput = "by: 15-10-2023 09:00";
        ExtractDateTime extractDateTime = new ExtractDateTime(validInput);

        ArrayList<LocalDateTime> result = extractDateTime.deadlineDateTime();

        assertEquals(1, result.size());
        assertEquals(LocalDateTime.of(2023, 10, 15, 9, 0), result.get(0)); // Deadline date/time
    }

    @Test
    void testDeadlineDateTimeWithInvalidInput() {
        String invalidInput = "invalid deadline string";
        ExtractDateTime extractDateTime = new ExtractDateTime(invalidInput);

        InvalidCommandException exception = assertThrows(InvalidCommandException.class, extractDateTime::deadlineDateTime);

        assertEquals("Invalid Date/Time Format", exception.getMessage());
    }
}

