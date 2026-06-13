package org.example;

import org.example.display.Displayer;
import org.example.model.DataModel;
import org.example.reader.ReaderXLSX;
import org.example.report.*;

public class App {

    public void run(String[] args) {
        String filePath = args.length > 0 ? args[0] : "data.xlsx";

        ReaderXLSX reader = new ReaderXLSX();
        DataModel data = reader.read(filePath);

        Displayer displayer = new Displayer();

        displayer.display(new ReportSumAllClients(data));
        displayer.display(new ReportSumAllProject(data));
        displayer.display(new ReportTop10Task(data));
        displayer.display(new ReportUserNlProject(data));
        displayer.display(new ReportUserMaxTimeLoad(data));
    }
}
