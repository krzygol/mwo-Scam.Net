package org.example.export;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.example.display.model.Report4Top10TasksData;
import org.example.display.model.Report4Top10TasksRow;

public class ExcelReport4Top10Tasks
        implements ExcelExporter<Report4Top10TasksData> {

    @Override
    public void export(Report4Top10TasksData report,
                       Workbook workbook) {

        Sheet sheet = workbook.createSheet("Report 4");

        int rowNum = 0;

        /*
         * Style
         */
        CellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER);

        Font titleFont = workbook.createFont();
        titleFont.setBold(true);
        titleStyle.setFont(titleFont);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setAlignment(HorizontalAlignment.CENTER);

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);

        /*
         * Tytuł raportu
         */
        Row row = sheet.createRow(rowNum++);

        Cell cell = row.createCell(0);
        cell.setCellValue("REPORT 4: TOP 10 TASKS BY USER");
        cell.setCellStyle(titleStyle);

        // Scal A1:C1
        sheet.addMergedRegion(new CellRangeAddress(
                row.getRowNum(),
                row.getRowNum(),
                0,
                2
        ));

        /*
         * Zakres raportu
         */
        row = sheet.createRow(rowNum++);

        row.createCell(0).setCellValue("Report covers");

        if (report.dateFrom() != null && !report.dateFrom().isBlank()) {
            row.createCell(1).setCellValue(report.dateFrom());
        }

        if (report.dateTo() != null && !report.dateTo().isBlank()) {
            row.createCell(2).setCellValue(report.dateTo());
        }

        /*
         * Pusty wiersz
         */
        rowNum++;

        /*
         * Nagłówki tabeli
         */
        row = sheet.createRow(rowNum++);

        Cell headerCell = row.createCell(0);
        headerCell.setCellValue("No.");
        headerCell.setCellStyle(headerStyle);

        headerCell = row.createCell(1);
        headerCell.setCellValue("Task");
        headerCell.setCellStyle(headerStyle);

        headerCell = row.createCell(2);
        headerCell.setCellValue("Hours");
        headerCell.setCellStyle(headerStyle);

        /*
         * Dane
         */
        for (Report4Top10TasksRow dataRow : report.rows()) {

            row = sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(dataRow.rank());

            row.createCell(1)
                    .setCellValue(dataRow.taskName());

            row.createCell(2)
                    .setCellValue(dataRow.workingHours());
        }

        /*
         * Brak danych
         */
        if (report.rows().isEmpty()) {

            row = sheet.createRow(rowNum);

            row.createCell(0)
                    .setCellValue("No data.");
        }

        /*
         * Dopasowanie szerokości kolumn
         */
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
    }
}