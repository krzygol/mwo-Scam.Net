package org.example.orchestrator;


import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.display.*;
import org.example.display.model.*;
import org.example.export.ExcelReport1SumAllUsers;
import org.example.export.ReportExecutor;
import org.example.model.DataModel;
import org.example.report.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import static org.example.orchestrator.ReportType.REPORT_1;

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
                new ReportExecutor().execute(
                        REPORT_1,
                        dataModel,
                        inputLoader
                );
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
                break;

            case "Report4Top10Tasks":
                Report4Top10TasksData reportData4 = new Report4Top10Tasks(dataModel, inputLoader).generate();
                Displayer<Report4Top10TasksData> printer4 = new DisplayReport4Top10Tasks();
                printer4.display(reportData4);
                break;

            case "Report5UsersMaxTimeLoad":
                Report5UsersMaxTimeLoadData reportData5 = new Report5UsersMaxTimeLoad(dataModel, inputLoader).generate();
                Displayer<Report5UsersMaxTimeLoadData> printer5 = new DisplayReport5UsersMaxTimeLoad();
                printer5.display(reportData5);
                break;
        }
    }

}

