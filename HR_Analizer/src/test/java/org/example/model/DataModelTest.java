package org.example.model;

import org.example.reader.ReaderXLSX;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class DataModelTest {

    @Test
    void addTask() throws Exception {
        Path path = Path.of("reporter-dane");

        ReaderXLSX reader = new ReaderXLSX();
        DataModel data = reader.importAll(path);

        for (Task task : data.getTasks()) {
            System.out.println(task);
        }
    }

}