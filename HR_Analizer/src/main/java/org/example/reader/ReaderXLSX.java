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


//
//    public DataModel read(String filePath) {
//        DataModel data = new DataModel();
//
//        try (FileInputStream fis = new FileInputStream(filePath);
//             Workbook workbook = new XSSFWorkbook(fis)) {
//
//            Sheet sheet = workbook.getSheetAt(0);
//
//            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
//                Row row = sheet.getRow(i);
//                if (row == null) continue;
//
//                String user = getCellString(row, 0);
//                String client = getCellString(row, 1);
//                String project = getCellString(row, 2);
//                String taskName = getCellString(row, 3);
//                double hours = getCellNumeric(row, 4);
//
//                data.addTask(new Task(user, client, project, taskName, hours));
//            }
//
//        } catch (IOException e) {
//            System.err.println("Error reading file: " + filePath + " — " + e.getMessage());
//        }
//
//        return data;
//    }
//
//    private String getCellString(Row row, int col) {
//        Cell cell = row.getCell(col);
//        if (cell == null) return "";
//        return cell.getStringCellValue().trim();
//    }
//
//    private double getCellNumeric(Row row, int col) {
//        Cell cell = row.getCell(col);
//        if (cell == null) return 0;
//        return cell.getNumericCellValue();
//    }
//}
