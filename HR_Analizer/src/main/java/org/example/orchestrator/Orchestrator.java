package org.example.orchestrator;


import lombok.AllArgsConstructor;
import org.example.display.DisplayReport1SumAllUsers;
import org.example.display.DisplayReport2SumAllProjects;
import org.example.display.DisplayReport3UsersAllProjects;
import org.example.display.Displayer;
import org.example.display.model.Report1SumAllUsersData;
import org.example.display.model.Report2SumAllProjectsData;
import org.example.display.model.Report3UsersAllProjectsData;
import org.example.model.DataModel;
import org.example.report.*;

import java.util.Scanner;

public class Orchestrator {

    DataModel dataModel;
    InputLoader inputLoader;


    public Orchestrator(DataModel dataModel, InputLoader inputLoader) {
        this.dataModel = dataModel;
        this.inputLoader = inputLoader;
    }


    public void controller() {

        String command = inputLoader.getCommand();
        Report report;

        System.out.println("TEST PROGRAMU!!");


        switch (command) {
            case "Raport1SumAllUser":
                Report1SumAllUsersData reportData1 = new Report1SumAllUsers(dataModel, inputLoader).generate();
                Displayer<Report1SumAllUsersData> printer1 = new DisplayReport1SumAllUsers();
                printer1.display(reportData1);
                break;

            case "Report2SumAllProjects":
                Report2SumAllProjectsData reportData = new Report2SumAllProjects(dataModel, inputLoader).generate();
                Displayer<Report2SumAllProjectsData> printer2 = new DisplayReport2SumAllProjects();
                printer2.display(reportData);
                break;


            case "Report3UsersAllProjects":
               Report3UsersAllProjectsData reportData3 = new Report3UsersAllProjects(dataModel, inputLoader).generate();
               Displayer<Report3UsersAllProjectsData> printer3 = new DisplayReport3UsersAllProjects();
               printer3.display(reportData3);




            case "Report4Top10Tasks":
                report = new Report4Top10Tasks(dataModel, inputLoader);
                break;


            case "Report5UsersMaxTimeLoad":
                report = new Report5UsersMaxTimeLoad(dataModel, inputLoader);
                break;


        }
    }

}

