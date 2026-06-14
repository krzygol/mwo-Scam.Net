package org.example.export;

import org.example.display.DisplayReport1SumAllUsers;
import org.example.display.DisplayReport2SumAllProjects;
import org.example.display.DisplayReport3UsersAllProjects;
import org.example.display.DisplayReport4Top10Tasks;
import org.example.display.model.Report1SumAllUsersData;
import org.example.display.model.Report2SumAllProjectsData;
import org.example.display.model.Report3UsersAllProjectsData;
import org.example.display.model.Report4Top10TasksData;
import org.example.model.DataModel;
import org.example.orchestrator.InputLoader;
import org.example.orchestrator.ReportType;
import org.example.report.Report1SumAllUsers;
import org.example.report.Report2SumAllProjects;
import org.example.report.Report3UsersAllProjects;
import org.example.report.Report4Top10Tasks;


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

                excelWriter.write(
                        "report1.xlsx",
                        report,
                        new ExcelReport1SumAllUsers()
                );
            }

            case REPORT_2 -> {

                Report2SumAllProjectsData report =
                        new Report2SumAllProjects(data, inputLoader).generate();

                new DisplayReport2SumAllProjects().display(report);

                excelWriter.write(
                        "report2.xlsx",
                        report,
                        new ExcelReport2SumAllProjects()
                );
            }

            case REPORT_3 -> {

                Report3UsersAllProjectsData report =
                        new Report3UsersAllProjects(data, inputLoader).generate();

                new DisplayReport3UsersAllProjects().display(report);

                excelWriter.write(
                        "report3.xlsx",
                        report,
                        new ExcelReport3UsersAllProjects()
                );
            }

            case REPORT_4 -> {

                Report4Top10TasksData report =
                        new Report4Top10Tasks(data, inputLoader).generate();

                new DisplayReport4Top10Tasks().display(report);

                excelWriter.write(
                        "report4.xlsx",
                        report,
                        new ExcelReport4Top10Tasks()
                );
            }
//
//            case REPORT_5 -> {
//
//                Report5UsersMaxTimeLoadData report =
//                        new Report5UsersMaxTimeLoad(data).generate();
//
//                new PrintReport5UsersMaxTimeLoad().print(report);
//
//                excelWriter.write(
//                        "report5.xlsx",
//                        report,
//                        new ExcelReport5UsersMaxTimeLoad()
//                );
//            }
        }
    }
}
