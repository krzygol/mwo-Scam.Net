package org.example.export;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelFileWriter {

    public <T> void write(String fileName,
                          T reportData,
                          ExcelExporter<T> exporter) {

        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fos = new FileOutputStream(fileName)) {

            exporter.export(reportData, workbook);

            workbook.write(fos);

            System.out.println("\nExport to file: " + fileName);

        } catch (IOException e) {
            System.err.println("Błąd podczas zapisu pliku: " + fileName);
            e.printStackTrace();
        }
    }
}
