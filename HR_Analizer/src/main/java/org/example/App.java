package org.example;

import org.example.display.Displayer;
import org.example.display.model.Report1SumAllUsersRow;
import org.example.model.DataModel;
import org.example.reader.ReaderXLSX;
import org.example.report.*;

import java.nio.file.Path;

import java.math.BigDecimal;
import java.util.List;
import org.example.display.Report;

public class App {

    public void run(String[] args) throws Exception {

        Path path = Path.of("reporter-dane");

        ReaderXLSX reader = new ReaderXLSX();
        DataModel data = reader.importAll(path);




//        Displayer displayer = new Displayer();
//
//        displayer.display(new Report1SumAllUsers(data));
//        displayer.display(new Report2SumAllProjects(data));
//        displayer.display(new Report4Top10Tasks(data));
//        displayer.display(new Report3UsersAllProjects(data));
//        displayer.display(new Report5UsersMaxTimeLoad(data));


        List<Report1SumAllUsersRow> sales = List.of(
                new Report1SumAllUsersRow("Jan Kowalski", 50),
                new Report1SumAllUsersRow("Tomasz Nankaniec", 120)
        );

        org.example.display.Report<Report1SumAllUsersRow> report = new org.example.display.Report1SumAllUsers();

        report.print(sales);
    }
}
