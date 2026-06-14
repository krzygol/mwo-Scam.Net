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

String command = inputLoader.getCommand();


    public Orchestrator(DataModel dataModel, InputLoader inputLoader) {
        this.dataModel = dataModel;
        this.inputLoader = inputLoader;
    }


    public void controller(){

        System.out.println("TEST PROGRAMU!!");






            switch (command) {
                case "Raport1SumAllUser":
                    Report1SumAllUsers report1 = new Report1SumAllUsers(dataModel,inputLoader);




                case "Report2SumAllProjects":
                    Report report2 = new Report2SumAllProjects(dataModel, inputLoader);



                case "Report3UsersAllProjects":
                    Report report3 = new Report3UsersAllProjects(dataModel,inputLoader);



                case "Report4Top10Tasks":
                    Report report4 = new Report4Top10Tasks(dataModel,inputLoader);



                case "Report5UsersMaxTimeLoad":
                    Report report5 = new Report5UsersMaxTimeLoad(dataModel, inputLoader);



        }
    }

}

