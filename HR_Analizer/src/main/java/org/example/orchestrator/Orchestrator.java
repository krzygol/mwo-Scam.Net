package org.example.orchestrator;


import lombok.AllArgsConstructor;
import org.example.display.Displayer;
import org.example.model.DataModel;
import org.example.report.Report;
import org.example.report.Report1SumAllUsers;
import org.example.report.Report2SumAllProjects;
import org.example.report.Report5UsersMaxTimeLoad;

import java.util.Scanner;

public class Orchestrator {

    DataModel dataModel;


    Scanner scanner = new Scanner(System.in);

    public Orchestrator(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public void controller(){

        System.out.println("TEST PROGRAMU!!");

        Displayer displayer = new Displayer();



        while (true) {
            String line = scanner.nextLine();

            switch (line) {
                case "Raport1SumAllUser":
                    Report report = new Report1SumAllUsers(dataModel);
                    displayer.display(report);

            }

            switch (line) {
                case "Report2SumAllProjects":
                    Report report = new Report2SumAllProjects(dataModel);
                    displayer.display(report);

            }
            switch (line) {
                case "Report2SumAllProjects":
                    Report report = new Report3UsersAllProjects(dataModel);
                    displayer.display(report);

            }
            switch (line) {
                case "Report2SumAllProjects":
                    Report report = new Report4TopTasks(dataModel);
                    displayer.display(report);

            }
            switch (line) {
                case "Report2SumAllProjects":
                    Report report = new Report5UsersMaxTimeLoad(dataModel);
                    displayer.display(report);

            }

        }
    }

}

