package org.example.reader;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.model.DataModel;
import org.example.model.Task;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class ReaderXLSX {


    public DataModel importAll(Path root) throws Exception {
        if (root == null) {
            throw new IllegalArgumentException("Directory path cannot be null. Use the -p flag.");
        }
        if (!Files.exists(root)) {
            throw new IllegalArgumentException("Path does not exist: " + root);
        }
        if (!Files.isDirectory(root)) {
            throw new IllegalArgumentException("Path is not a directory: " + root);
        }

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

                    int rowNum = row.getRowNum() + 1;

                    if (!DateUtil.isCellDateFormatted(dateCell)) {
                        throw new IllegalStateException(
                            "Column 'date' (A) in sheet '" + project + "', row " + rowNum + " does not contain a date. Value: " + formatter.formatCellValue(dateCell));
                    }
                    if (nameCell.getCellType() != CellType.STRING) {
                        throw new IllegalStateException(
                            "Column 'name' (B) in sheet '" + project + "', row " + rowNum + " does not contain text. Value: " + formatter.formatCellValue(nameCell));
                    }
                    if (durationCell.getCellType() != CellType.NUMERIC) {
                        throw new IllegalStateException(
                            "Column 'duration' (C) in sheet '" + project + "', row " + rowNum + " does not contain a number. Value: " + formatter.formatCellValue(durationCell));
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


