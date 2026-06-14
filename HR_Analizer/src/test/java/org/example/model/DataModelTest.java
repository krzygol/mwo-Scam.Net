package org.example.model;

import org.example.reader.ReaderXLSX;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class DataModelTest {

    @Test
    void shouldAddTask() {

        DataModel model = new DataModel();

        Task task = new Task(
                "Test",
                "CRM",
                2.0,
                "Jan",
                new java.util.Date()
        );

        model.addTask(task);

        assertEquals(1, model.getTasks().size());
        assertEquals(task, model.getTasks().get(0));
    }

}