package org.example.export;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.example.display.model.Report3UsersAllProjectsData;
import org.example.display.model.Report3UsersAllProjectsRow;

public class ExcelReport3UsersAllProjects
        implements ExcelExporter<Report3UsersAllProjectsData> {

    @Override
    public void export(Report3UsersAllProjectsData report,
                       Workbook workbook) {

        Sheet sheet = workbook.createSheet("Report 3");

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
         * Styl procentowy
         */
        DataFormat dataFormat = workbook.createDataFormat();

        CellStyle percentStyle = workbook.createCellStyle();
        percentStyle.setDataFormat(dataFormat.getFormat("0.00%"));

        /*
         * Tytuł raportu
         */
        Row row = sheet.createRow(rowNum++);

        Cell cell = row.createCell(0);
        cell.setCellValue("REPORT 3: USER PROJECTS BREAKDOWN");
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
         * Użytkownik
         */
        row = sheet.createRow(rowNum++);

        row.createCell(0).setCellValue("User");
        row.createCell(1).setCellValue(report.userID());

        /*
         * Pusty wiersz
         */
        rowNum++;

        /*
         * Nagłówki
         */
        row = sheet.createRow(rowNum++);

        Cell headerCell = row.createCell(0);
        headerCell.setCellValue("Project");
        headerCell.setCellStyle(headerStyle);

        headerCell = row.createCell(1);
        headerCell.setCellValue("Hours");
        headerCell.setCellStyle(headerStyle);

        headerCell = row.createCell(2);
        headerCell.setCellValue("Share");
        headerCell.setCellStyle(headerStyle);

        /*
         * Dane
         */
        for (Report3UsersAllProjectsRow dataRow : report.rows()) {

            row = sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(dataRow.projectName());

            row.createCell(1)
                    .setCellValue(dataRow.workingHours());

            Cell percentCell = row.createCell(2);

            /*
             * percentageValue() zwraca np. 25.50,
             * a Excel oczekuje 0.255 dla formatu %
             */
            percentCell.setCellValue(
                    dataRow.percentageValue() / 100.0
            );

            percentCell.setCellStyle(percentStyle);
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