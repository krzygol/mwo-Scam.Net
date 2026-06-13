package org.example;

import org.example.display.Displayer;
import org.example.model.DataModel;
import org.example.orchestrator.Orchestrator;
import org.example.reader.ReaderXLSX;
import org.example.report.*;

import java.nio.file.Path;

public class App {

    public void run(String[] args) throws Exception {

        Path path = Path.of("reporter-dane");


        ReaderXLSX reader = new ReaderXLSX();
        DataModel data = reader.importAll(path);



        Orchestrator orchestrator = new Orchestrator(data);
        orchestrator.controller();






//        Displayer displayer = new Displayer();
//
//        displayer.display(new Report1SumAllUsers(data));
//        displayer.display(new Report2SumAllProjects(data));
//        displayer.display(new Report4Top10Tasks(data));
//        displayer.display(new Report3UsersAllProjects(data));
//        displayer.display(new Report5UsersMaxTimeLoad(data));
    }
}
