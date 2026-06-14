package org.example.export;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.example.display.model.Report6GivenUserMaxTimeTasksData;
import org.example.display.model.Report6GivenUserMaxTimeTasksRow;

public class ExcelReport6GivenUserMaxTimeTasks
        implements ExcelExporter<Report6GivenUserMaxTimeTasksData> {

    @Override
    public void export(Report6GivenUserMaxTimeTasksData report,
                       Workbook workbook) {

        Sheet sheet = workbook.createSheet("Report 6");

        int rowNum = 0;

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

        Row row = sheet.createRow(rowNum++);
        Cell cell = row.createCell(0);
        cell.setCellValue("REPORT 6: TOP 10 MOST TIME-CONSUMING TASKS FOR USER");
        cell.setCellStyle(titleStyle);
        sheet.addMergedRegion(new CellRangeAddress(
                row.getRowNum(), row.getRowNum(), 0, 2));

        row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue("Report covers");
        if (report.dateFrom() != null && !report.dateFrom().isBlank()) {
            row.createCell(1).setCellValue(report.dateFrom());
        }
        if (report.dateTo() != null && !report.dateTo().isBlank()) {
            row.createCell(2).setCellValue(report.dateTo());
        }

        if (report.userName() != null) {
            row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue("User:");
            row.createCell(1).setCellValue(report.userName().replaceFirst("\\.xlsx$", ""));
        }

        rowNum++;

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

        for (Report6GivenUserMaxTimeTasksRow dataRow : report.rows()) {
            row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(dataRow.rank());
            row.createCell(1).setCellValue(dataRow.taskName());
            row.createCell(2).setCellValue(dataRow.workingHours());
        }

        if (report.rows().isEmpty()) {
            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("No data.");
        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
    }
}
