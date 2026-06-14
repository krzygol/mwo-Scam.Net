package org.example.orchestrator;


import lombok.AllArgsConstructor;
import org.example.display.Displayer;
import org.example.model.DataModel;
import org.example.report.*;

import java.util.Scanner;

public class Orchestrator {

    DataModel dataModel;
    InputLoader inputLoader;

String command = inputLoader.getCommand();


    public Orchestrator(DataModel dataModel, InputLoader inputLoader) {
        this.dataModel = dataModel;
        this.inputLoader = inputLoader;
    }

    public void controller(){

        System.out.println("TEST PROGRAMU!!");

        Displayer displayer = new Displayer();





            switch (command) {
                case "Raport1SumAllUser":
                    Report report = new Report1SumAllUsers(dataModel,inputLoader);
                    displayer.display(report);

            }

            switch (command) {
                case "Report2SumAllProjects":
                    Report report = new Report2SumAllProjects(dataModel, inputLoader);
                    displayer.display(report);

            }
            switch (command) {
                case "Report3UsersAllProjects":
                    Report report = new Report3UsersAllProjects(dataModel,inputLoader);
                    displayer.display(report);

            }
            switch (command) {
                case "Report4Top10Tasks":
                    Report report = new Report4Top10Tasks(dataModel,inputLoader);
                    displayer.display(report);

            }
            switch (command) {
                case "Report5UsersMaxTimeLoad":
                    Report report = new Report5UsersMaxTimeLoad(dataModel, inputLoader);
                    displayer.display(report);

            }

        }
    }

}

