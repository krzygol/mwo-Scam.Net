package org.example;

import org.example.display.Displayer;
import org.example.model.DataModel;
import org.example.reader.ReaderXLSX;
import org.example.report.Report1SumAllUsers;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws Exception {

        ReaderXLSX reader = new ReaderXLSX();

        DataModel data = reader.importAll(
                Paths.get("reporter-dane")
        );

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFrom = sdf.parse("2012-01-01");
        Date dateTo   = sdf.parse("2012-01-15");

        Report1SumAllUsers report =
                new Report1SumAllUsers(data, dateFrom, dateTo);

//        new Displayer().display(report);
        App app= new App();
        app.run(args);
    }
}