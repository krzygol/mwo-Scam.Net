package org.example.model;

import org.example.display.model.Report1SumAllUsersData;
import org.example.orchestrator.InputLoader;
import org.example.reader.ReaderXLSX;
import org.example.report.Report1SumAllUsers;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class Report1SumAllUsersTest {

    @Test
    void shouldGenerateUserSummary() throws Exception {

        ReaderXLSX reader = new ReaderXLSX();

        DataModel data = reader.importAll(
                Path.of("reporter-dane")
        );

        InputLoader inputLoader = InputLoader.builder()
                .from(new Date(0))
                .to(new Date())
                .build();

        Report1SumAllUsers report =
                new Report1SumAllUsers(data, inputLoader);

        Report1SumAllUsersData result =
                report.generate();

        assertNotNull(result);

        assertNotNull(result.rows());

        assertFalse(result.rows().isEmpty());
    }
}