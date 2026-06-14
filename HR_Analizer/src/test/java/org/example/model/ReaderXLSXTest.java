package org.example.reader;

import org.example.model.DataModel;
import org.example.model.Task;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ReaderXLSXTest {

    @Test
    void shouldImportTasksFromExcel() throws Exception {

        ReaderXLSX reader = new ReaderXLSX();

        DataModel data = reader.importAll(
                Path.of("reporter-dane")
        );

        assertNotNull(data);
        assertFalse(data.getTasks().isEmpty());

        Task firstTask = data.getTasks().get(0);

        assertNotNull(firstTask.getName());
        assertNotNull(firstTask.getProject());
        assertNotNull(firstTask.getUser());

        assertTrue(firstTask.getDuration() > 0);
    }
}