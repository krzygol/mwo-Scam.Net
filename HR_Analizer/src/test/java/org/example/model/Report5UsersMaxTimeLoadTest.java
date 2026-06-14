package org.example.report;

import org.example.display.model.Report5UsersMaxTimeLoadData;
import org.example.model.DataModel;
import org.example.orchestrator.InputLoader;
import org.example.reader.ReaderXLSX;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class Report5UsersMaxTimeLoadTest {

    @Test
    void shouldGenerateTop5UsersReport() throws Exception {

        ReaderXLSX reader = new ReaderXLSX();

        DataModel data = reader.importAll(
                Path.of("reporter-dane")
        );

        InputLoader inputLoader = InputLoader.builder()
                .from(new Date(0))
                .to(new Date())
                .build();

        Report5UsersMaxTimeLoad report =
                new Report5UsersMaxTimeLoad(data, inputLoader);

        Report5UsersMaxTimeLoadData result =
                report.generate();

        assertNotNull(result);

        assertNotNull(result.rows());

        assertFalse(result.rows().isEmpty());

        assertTrue(result.rows().size() <= 5);
    }
}