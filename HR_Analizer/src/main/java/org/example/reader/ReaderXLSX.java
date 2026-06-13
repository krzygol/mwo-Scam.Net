package org.example.reader;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.model.DataModel;
import org.example.model.Task;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class ReaderXLSX {


    public DataModel importAll(Path root) throws Exception {

        DataModel model = new DataModel();

        try (Stream<Path> paths = Files.walk(root)) {

            paths.filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".xlsx"))
                    .forEach(path -> {
                        try {
                            DataModel partial = read(path);

                            partial.getTasks()
                                    .forEach(model::addTask);

                        } catch (Exception e) {
                            throw new RuntimeException("Błąd w pliku: " + path, e);
                        }
                    });
        }

        return model;
    }



    public DataModel read(Path filePath) throws Exception {

        DataModel model = new DataModel();

        String owner = filePath.getFileName().toString();

        DataFormatter formatter = new DataFormatter();


        try (InputStream is = Files.newInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(is)) {

            for (Sheet sheet : workbook) {

                String project = sheet.getSheetName();

                boolean header = true;

                for (Row row : sheet) {


                    if (header) {
                        header = false;
                        continue;
                    }

                    if (row == null) continue;

                    Cell dateCell = row.getCell(0);
                    Cell nameCell = row.getCell(1);
                    Cell durationCell = row.getCell(2);

                    if (dateCell == null || nameCell == null || durationCell == null) {
                        continue;
                    }

                    Date date = dateCell.getDateCellValue();
                    String name = nameCell.getStringCellValue();
                    double duration = durationCell.getNumericCellValue();

                    Task task = new Task(
                            name,
                            project,
                            duration,
                            owner,
                            date
                    );

                    model.addTask(task);
                }
            }
        }

        return model;
    }
}


