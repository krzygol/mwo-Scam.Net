package org.example.export;

import org.example.display.*;
import org.example.display.model.*;
import org.example.model.DataModel;
import org.example.orchestrator.InputLoader;
import org.example.orchestrator.ReportType;
import org.example.report.*;
import org.example.export.ExcelReport6GivenUserMaxTimeTasks;


public class ReportExecutor {

    private final ExcelFileWriter excelWriter = new ExcelFileWriter();

    public void execute(ReportType type,
                        DataModel data,
                        InputLoader inputLoader) {

        switch (type) {

            case REPORT_1 -> {

                Report1SumAllUsersData report =
                        new Report1SumAllUsers(data, inputLoader).generate();

                new DisplayReport1SumAllUsers().display(report);

                if(inputLoader.getPrint()) {
                    excelWriter.write(
                            "report1.xlsx",
                            report,
                            new ExcelReport1SumAllUsers()
                    );
                }
            }

            case REPORT_2 -> {

                Report2SumAllProjectsData report =
                        new Report2SumAllProjects(data, inputLoader).generate();

                new DisplayReport2SumAllProjects().display(report);

                if(inputLoader.getPrint()) {
                    excelWriter.write(
                            "report2.xlsx",
                            report,
                            new ExcelReport2SumAllProjects()
                    );
                }
            }

            case REPORT_3 -> {

                Report3UsersAllProjectsData report =
                        new Report3UsersAllProjects(data, inputLoader).generate();

                new DisplayReport3UsersAllProjects().display(report);

                if(inputLoader.getPrint()) {
                    excelWriter.write(
                            "report3.xlsx",
                            report,
                            new ExcelReport3UsersAllProjects()
                    );
                }
            }

            case REPORT_4 -> {

                Report4Top10TasksData report =
                        new Report4Top10Tasks(data, inputLoader).generate();

                new DisplayReport4Top10Tasks().display(report);

                if(inputLoader.getPrint()) {
                    excelWriter.write(
                            "report4.xlsx",
                            report,
                            new ExcelReport4Top10Tasks()
                    );
                }
            }

            case REPORT_5 -> {

                Report5UsersMaxTimeLoadData report =
                        new Report5UsersMaxTimeLoad(data, inputLoader).generate();

                new DisplayReport5UsersMaxTimeLoad().display(report);

                if(inputLoader.getPrint()) {
                    excelWriter.write(
                            "report5.xlsx",
                            report,
                            new ExcelReport5UsersMaxTimeLoad()
                    );
                }
            }

            case REPORT_6 -> {

                Report6GivenUserMaxTimeTasksData report =
                        new Report6GivenUserMaxTimeTasks(data, inputLoader).generate();

                new DisplayReport6GivenUserMaxTimeTasks().display(report);

                excelWriter.write(
                        "report6.xlsx",
                        report,
                        new ExcelReport6GivenUserMaxTimeTasks()
                );
            }
        }
    }
}
