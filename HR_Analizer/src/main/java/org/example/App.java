package org.example;

import org.example.display.*;
//import org.example.display.PrintReport2SumAllProjects;
import org.example.display.model.*;
import org.example.model.DataModel;
import org.example.reader.ReaderXLSX;
import org.example.report.*;

import java.nio.file.Path;

public class App {

    public void run(String[] args) throws Exception {

        Path path = Path.of("reporter-dane");


        ReaderXLSX reader = new ReaderXLSX();
        DataModel data = reader.importAll(path);

//        Orchestrator orchestrator = new Orchestrator(data);
//        orchestrator.controller();

//        Displayer displayer = new Displayer();
//
//        displayer.display(new Report1SumAllUsers(data));           // ok
//        displayer.display(new Report2SumAllProjects(data));
//        displayer.display(new Report4Top10Tasks(data));
//        displayer.display(new Report3UsersAllProjects(data));
//        displayer.display(new Report5UsersMaxTimeLoad(data));

        /* Report 1 */
        System.out.println();
        Report1SumAllUsersData reportData1 = new Report1SumAllUsers(data).generate();
        Displayer<Report1SumAllUsersData> printer1 = new DisplayReport1SumAllUsers();
        printer1.display(reportData1);

        /* Report 2 */
        System.out.println();
        Report2SumAllProjectsData reportData2 = new Report2SumAllProjects(data).generate();
        Displayer<Report2SumAllProjectsData> printer2 = new DisplayReport2SumAllProjects();
        printer2.display(reportData2);

        /*Report 3 */
        System.out.println();
        Report3UsersAllProjectsData reportData3 = new Report3UsersAllProjects(data, "Kowalski_Jan.xlsx").generate();
        Displayer<Report3UsersAllProjectsData> printer3 = new DisplayReport3UsersAllProjects();
        printer3.display(reportData3);

        /*Report 4 */
        System.out.println();
        Report4Top10TasksData reportData4 = new Report4Top10Tasks(data).generate();

        Displayer<Report4Top10TasksData> printer4 = new DisplayReport4Top10Tasks();
        printer4.display(reportData4);

        /*Report 5 */
        System.out.println();
        Report5UsersMaxTimeLoadData reportData5 = new Report5UsersMaxTimeLoad(data).generate();
        Displayer<Report5UsersMaxTimeLoadData> printer5 = new DisplayReport5UsersMaxTimeLoad();
        printer5.display(reportData5);
    }
}
