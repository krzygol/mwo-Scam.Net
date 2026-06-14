package org.example.report;

import org.example.display.model.Report4Top10TasksData;
import org.example.model.DataModel;
import org.example.orchestrator.InputLoader;
import org.example.reader.ReaderXLSX;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class Report4Top10TasksTest {

    @Test
    void shouldGenerateTop10TasksReport() throws Exception {

        ReaderXLSX reader = new ReaderXLSX();

        DataModel data = reader.importAll(
                Path.of("reporter-dane")
        );

        assertFalse(data.getTasks().isEmpty());

        String project = data.getTasks()
                .get(0)
                .getProject();

        InputLoader inputLoader = InputLoader.builder()
                .project(project)
                .from(new Date(0))
                .to(new Date())
                .build();

        Report4Top10Tasks report =
                new Report4Top10Tasks(data, inputLoader);

        Report4Top10TasksData result =
                report.generate();

        assertNotNull(result);

        assertNotNull(result.rows());

        assertFalse(result.rows().isEmpty());

        assertTrue(result.rows().size() <= 10);
    }
}