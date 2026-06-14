package org.example.export;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.example.display.model.Report1SumAllUsersData;
import org.example.display.model.Report1SumAllUsersRow;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelReport1SumAllUsers
        implements ExcelExporter<Report1SumAllUsersData> {

    @Override
    public void export(Report1SumAllUsersData report,
                       Workbook workbook) {

        Sheet sheet = workbook.createSheet("Report 1");

        int rowNum = 0;

        /*
         * Styl tytułu
         */
        Font titleFont = workbook.createFont();
        titleFont.setBold(true);

        CellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);

        /*
         * Styl nagłówków
         */
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(headerFont);

        /*
         * Styl liczbowy dla godzin
         */
        DataFormat dataFormat = workbook.createDataFormat();

        CellStyle hoursStyle = workbook.createCellStyle();
        hoursStyle.setDataFormat(dataFormat.getFormat("0.00"));

        /*
         * Tytuł raportu
         */
        Row row = sheet.createRow(rowNum++);

        Cell cell = row.createCell(0);
        cell.setCellValue("REPORT 1: WORK SUMMARY ALL EMPLOYEES");
        cell.setCellStyle(titleStyle);

        // Scal komórki A1:B1
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
         * Nagłówki tabeli
         */
        row = sheet.createRow(rowNum++);

        cell = row.createCell(0);
        cell.setCellValue("Full Name");
        cell.setCellStyle(headerStyle);

        cell = row.createCell(1);
        cell.setCellValue("Hours");
        cell.setCellStyle(headerStyle);

        /*
         * Dane
         */
        for (Report1SumAllUsersRow dataRow : report.rows()) {

            row = sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(
                            dataRow.userName().replaceFirst("\\.xlsx$", "")
                    );

            Cell hoursCell = row.createCell(1);
            hoursCell.setCellValue(dataRow.workingHours());
            hoursCell.setCellStyle(hoursStyle);
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