package org.example.report;

import org.example.display.model.Report3UsersAllProjectsData;
import org.example.model.DataModel;
import org.example.orchestrator.InputLoader;
import org.example.reader.ReaderXLSX;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class Report3UsersAllProjectsTest {

    @Test
    void shouldGenerateUserProjectsSummary() throws Exception {

        ReaderXLSX reader = new ReaderXLSX();

        DataModel data = reader.importAll(
                Path.of("reporter-dane")
        );

        String user = data.getTasks()
                .get(0)
                .getUser();

        InputLoader inputLoader = InputLoader.builder()
                .user(user)
                .from(new Date(0))
                .to(new Date())
                .build();

        Report3UsersAllProjects report =
                new Report3UsersAllProjects(data, inputLoader);

        Report3UsersAllProjectsData result =
                report.generate();

        assertNotNull(result);

        assertNotNull(result.rows());

        assertFalse(result.rows().isEmpty());
    }
}