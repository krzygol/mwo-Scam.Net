package org.example;

import org.example.display.PrintReport;
import org.example.display.PrintReport1SumAllUsers;
import org.example.display.PrintReport2SumAllProjects;
import org.example.display.model.Report1SumAllUsersRow;
import org.example.display.model.Report2SumAllProjectsRow;
import org.example.model.DataModel;
import org.example.orchestrator.InputLoader;
import org.example.orchestrator.Orchestrator;
import org.example.reader.ReaderXLSX;

import java.nio.file.Path;

import java.util.List;

public class App {

    public void run(String[] args) throws Exception {

//        Path path = Path.of("reporter-dane");

        InputLoader inputLoader = InputLoader.create(args);

        ReaderXLSX reader = new ReaderXLSX();
        DataModel data = reader.importAll(inputLoader.getPath());



        Orchestrator orchestrator = new Orchestrator(data, inputLoader);
        orchestrator.controller();






//        Displayer displayer = new Displayer();
//
//        displayer.display(new Report1SumAllUsers(data));
//        displayer.display(new Report2SumAllProjects(data));
//        displayer.display(new Report4Top10Tasks(data));
//        displayer.display(new Report3UsersAllProjects(data));
//        displayer.display(new Report5UsersMaxTimeLoad(data));


        List<Report1SumAllUsersRow> report1List = List.of(
                new Report1SumAllUsersRow("Jan Kowalski", 50),
                new Report1SumAllUsersRow("Tomasz Nankaniec", 120)
        );

        PrintReport<Report1SumAllUsersRow> report1 = new PrintReport1SumAllUsers();

        report1.print(report1List);


        List<Report2SumAllProjectsRow> report2List = List.of(
                new Report2SumAllProjectsRow("Projekt pierwszy", 50),
                new Report2SumAllProjectsRow("Projekt drugi", 120)
        );

        PrintReport<Report2SumAllProjectsRow> report2 = new PrintReport2SumAllProjects();

        report2.print(report2List);


    }
}
