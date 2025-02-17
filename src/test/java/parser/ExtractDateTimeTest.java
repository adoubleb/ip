package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exceptions.InvalidCommandException;

//unitBeingTested_descriptionOfTestInputs_expectedOutcome

public class ExtractDateTimeTest {

    @Test
    void eventDateTime_validInput_successfulExtraction() throws InvalidCommandException {
        String validInput = "start: 12-10-2023 14:30 end: 13-10-2023 16:30";
        DateTimeExtractor extractDateTime = new DateTimeExtractor(validInput);

        ArrayList<LocalDateTime> result = extractDateTime.eventDateTime();


        assertEquals(2, result.size());
        assertEquals(LocalDateTime.of(2023, 10, 12, 14, 30), result.get(0));
        assertEquals(LocalDateTime.of(2023, 10, 13, 16, 30), result.get(1));
    }

    @Test
    void eventDateTime_invalidInput_exceptionThrown() {
        String invalidInput = "invalid date string";
        DateTimeExtractor extractDateTime = new DateTimeExtractor(invalidInput);

        try {
            extractDateTime.eventDateTime();
        } catch (InvalidCommandException e) {
            assertEquals("Invalid Date/Time Format", e.getMessage());
        }
    }

    @Test
    void deadlineDateTime_validInput_successfulExtraction() throws InvalidCommandException {
        String validInput = "by: 15-10-2023 09:00";
        DateTimeExtractor extractDateTime = new DateTimeExtractor(validInput);

        ArrayList<LocalDateTime> result = extractDateTime.deadlineDateTime();

        assertEquals(1, result.size());
        assertEquals(LocalDateTime.of(2023, 10, 15, 9, 0), result.get(0));
    }

    @Test
    void deadlineDateTime_validInput_exceptionThrown() {
        String invalidInput = "invalid deadline string";
        DateTimeExtractor extractDateTime = new DateTimeExtractor(invalidInput);

        InvalidCommandException exception = assertThrows(InvalidCommandException.class,
                extractDateTime::deadlineDateTime);

        assertEquals("Invalid Date/Time Format", exception.getMessage());
    }
}

