package org.example.orchestrator;


import org.example.model.DataModel;
import org.example.report.*;

import java.util.Scanner;

public class Orchestrator {

    DataModel dataModel;


    Scanner scanner = new Scanner(System.in);

    public Orchestrator(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public void controller(){

        System.out.println("TEST PROGRAMU!!");

//        Displayer displayer = new Displayer();



        while (true) {
            String line = scanner.nextLine();

            switch (line) {
                case "Raport1SumAllUser":
                    Report report = new Report1SumAllUsers(dataModel);
//                    displayer.display(report);

            }

//            switch (line) {
//                case "Report2SumAllProjects":
//                    Report report = new Report2SumAllProjects(dataModel);
//                    displayer.display(report);
//
//            }
//            switch (line) {
//                case "Report2SumAllProjects":
//                    Report report = new Report3UsersAllProjects(dataModel);
//                    displayer.display(report);
//
//            }
//            switch (line) {
//                case "Report2SumAllProjects":
//                    Report report = new Report4Top10Tasks(dataModel);
//                    displayer.display(report);

//            }
//            switch (line) {
//                case "Report2SumAllProjects":
//                    Report report = new Report5UsersMaxTimeLoad(dataModel);
//                    displayer.display(report);
//
//            }

        }
    }

}

