package org.example.report;

import org.example.display.model.Report2SumAllProjectsData;
import org.example.model.DataModel;
import org.example.orchestrator.InputLoader;
import org.example.reader.ReaderXLSX;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class Report2SumAllProjectsTest {

    @Test
    void shouldGenerateProjectsSummary() throws Exception {

        ReaderXLSX reader = new ReaderXLSX();

        DataModel data = reader.importAll(
                Path.of("reporter-dane")
        );

        InputLoader inputLoader = InputLoader.builder()
                .from(new Date(0))
                .to(new Date())
                .build();

        Report2SumAllProjects report =
                new Report2SumAllProjects(data, inputLoader);

        Report2SumAllProjectsData result =
                report.generate();

        assertNotNull(result);

        assertNotNull(result.rows());

        assertFalse(result.rows().isEmpty());
    }
}