package org.example.export;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.example.display.model.Report1SumAllUsersData;
import org.example.display.model.Report1SumAllUsersRow;

public class ExcelReport1SumAllUsers
        implements ExcelExporter<Report1SumAllUsersData> {

    @Override
    public void export(Report1SumAllUsersData report,
                       Workbook workbook) {

        Sheet sheet = workbook.createSheet("Report 1");

        int rowNum = 0;

        Row row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue("REPORT 1: WORK SUMMARY ALL EMPLOYEES");

        row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue("Report covers");

        if (report.dateFrom() != null) {
            row.createCell(1).setCellValue(report.dateFrom());
        }

        if (report.dateTo() != null) {
            row.createCell(2).setCellValue(report.dateTo());
        }

        row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue("Full Name");
        row.createCell(1).setCellValue("Hours");

        for (Report1SumAllUsersRow dataRow : report.rows()) {

            row = sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(dataRow.userName());

            row.createCell(1)
                    .setCellValue(dataRow.workingHours());
        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
    }
}
