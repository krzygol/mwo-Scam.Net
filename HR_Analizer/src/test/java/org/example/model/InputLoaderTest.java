package org.example.orchestrator;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class InputLoaderTest {

    @Test
    void shouldCreateInputLoaderFromArguments() throws Exception {

        String[] args = {
                "report1",
                "-f=2025-01-01",
                "-t=2025-12-31",
                "-u=Jan",
                "-p=reporter-dane"
        };

        InputLoader input = InputLoader.create(args);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        assertEquals("report1", input.getCommand());
        assertEquals("Jan.xlsx", input.getUser());
        assertEquals(Path.of("reporter-dane"), input.getPath());

        assertEquals(
                sdf.parse("2025-01-01"),
                input.getFrom()
        );

        assertEquals(
                sdf.parse("2025-12-31"),
                input.getTo()
        );
    }

    @Test
    void shouldThrowExceptionWhenNoArgumentsProvided() {

        assertThrows(
                IllegalArgumentException.class,
                () -> InputLoader.create(new String[]{})
        );
    }

    @Test
    void shouldThrowExceptionForUnknownFlag() {

        String[] args = {
                "report1",
                "-z=test"
        };

        assertThrows(
                IllegalArgumentException.class,
                () -> InputLoader.create(args)
        );
    }

    @Test
    void shouldThrowExceptionForInvalidDate() {

        String[] args = {
                "report1",
                "-f=2025-99-99"
        };

        assertThrows(
                IllegalArgumentException.class,
                () -> InputLoader.create(args)
        );
    }
}