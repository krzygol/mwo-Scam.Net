package org.example.export;

import org.apache.poi.ss.usermodel.Workbook;

public interface ExcelExporter<T> {
    void export(T report, Workbook workbook);
}
