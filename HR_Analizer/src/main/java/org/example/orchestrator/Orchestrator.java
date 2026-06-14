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

import static org.example.orchestrator.ReportType.*;

public class Orchestrator {

    DataModel dataModel;
    InputLoader inputLoader;


    public Orchestrator(DataModel dataModel, InputLoader inputLoader) {
        this.dataModel = dataModel;
        this.inputLoader = inputLoader;
    }


    public void controller() {

        String command = inputLoader.getCommand();
        


        switch (command) {
            case "Raport1SumAllUser":
                new ReportExecutor().execute(
                        REPORT_1,
                        dataModel,
                        inputLoader
                );
                break;

            case "Report2SumAllProjects":
                new ReportExecutor().execute(
                        REPORT_2,
                        dataModel,
                        inputLoader
                );
                break;

            case "Report3UsersAllProjects":
                new ReportExecutor().execute(
                        REPORT_3,
                        dataModel,
                        inputLoader
                );
                break;

            case "Report4Top10Tasks":
                new ReportExecutor().execute(
                        REPORT_4,
                        dataModel,
                        inputLoader
                );
                break;

            case "Report5UsersMaxTimeLoad":
                new ReportExecutor().execute(
                        REPORT_5,
                        dataModel,
                        inputLoader
                );
                break;
        }
    }

}

