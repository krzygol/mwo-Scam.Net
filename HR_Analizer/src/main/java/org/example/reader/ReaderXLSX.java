package org.example.reader;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.model.DataModel;
import org.example.model.Task;

import java.io.FileInputStream;
import java.io.IOException;

public class ReaderXLSX {

    public DataModel read(String filePath) {
        DataModel data = new DataModel();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String user = getCellString(row, 0);
                String client = getCellString(row, 1);
                String project = getCellString(row, 2);
                String taskName = getCellString(row, 3);
                double hours = getCellNumeric(row, 4);

                data.addTask(new Task(user, client, project, taskName, hours));
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath + " — " + e.getMessage());
        }

        return data;
    }

    private String getCellString(Row row, int col) {
        Cell cell = row.getCell(col);
        if (cell == null) return "";
        return cell.getStringCellValue().trim();
    }

    private double getCellNumeric(Row row, int col) {
        Cell cell = row.getCell(col);
        if (cell == null) return 0;
        return cell.getNumericCellValue();
    }
}
