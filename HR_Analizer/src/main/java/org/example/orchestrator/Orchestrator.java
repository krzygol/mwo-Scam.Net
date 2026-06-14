package org.example.orchestrator;


import lombok.AllArgsConstructor;
import org.example.display.DisplayReport1SumAllUsers;
import org.example.display.Displayer;
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
                report = new Report1SumAllUsers(dataModel, inputLoader);
                break;


            case "Report2SumAllProjects":
                report = new Report2SumAllProjects(dataModel, inputLoader);
                break;


            case "Report3UsersAllProjects":
                report = new Report3UsersAllProjects(dataModel, inputLoader);
                break;


            case "Report4Top10Tasks":
                report = new Report4Top10Tasks(dataModel, inputLoader);
                break;


            case "Report5UsersMaxTimeLoad":
                report = new Report5UsersMaxTimeLoad(dataModel, inputLoader);
                break;


        }
    }

}

