package org.example.export;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.example.display.model.Report2SumAllProjectsData;
import org.example.display.model.Report2SumAllProjectsRow;

public class ExcelReport2SumAllProjects
        implements ExcelExporter<Report2SumAllProjectsData> {

    @Override
    public void export(Report2SumAllProjectsData report,
                       Workbook workbook) {

        Sheet sheet = workbook.createSheet("Report 2");

        int rowNum = 0;

        /*
         * Styl tytułu
         */
        CellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER);

        Font titleFont = workbook.createFont();
        titleFont.setBold(true);
        titleStyle.setFont(titleFont);

        /*
         * Tytuł raportu
         */
        Row row = sheet.createRow(rowNum++);

        Cell cell = row.createCell(0);
        cell.setCellValue("REPORT 2: ALL PROJECTS SUMMARY");
        cell.setCellStyle(titleStyle);

        // Scal A1:B1
        sheet.addMergedRegion(new CellRangeAddress(
                row.getRowNum(),
                row.getRowNum(),
                0,
                1
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
         * Nagłówki kolumn
         */
        CellStyle headerStyle = workbook.createCellStyle();

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);

        headerStyle.setFont(headerFont);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);

        row = sheet.createRow(rowNum++);

        Cell headerCell = row.createCell(0);
        headerCell.setCellValue("Project Name");
        headerCell.setCellStyle(headerStyle);

        headerCell = row.createCell(1);
        headerCell.setCellValue("Hours");
        headerCell.setCellStyle(headerStyle);

        /*
         * Dane
         */
        for (Report2SumAllProjectsRow dataRow : report.rows()) {

            row = sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(dataRow.projectName());

            row.createCell(1)
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
