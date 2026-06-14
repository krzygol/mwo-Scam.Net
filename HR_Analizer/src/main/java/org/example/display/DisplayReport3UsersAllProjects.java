package org.example.display;

import org.example.display.model.Report3UsersAllProjectsData;
import org.example.display.model.Report3UsersAllProjectsRow;

public class DisplayReport3UsersAllProjects
        implements Displayer<Report3UsersAllProjectsData> {

    @Override
    public void display(Report3UsersAllProjectsData report) {

        System.out.println("===== REPORT 3: USER PROJECTS BREAKDOWN =====");

        StringBuilder period = new StringBuilder("Report covers");

        if (report.dateFrom() != null && !report.dateFrom().isBlank()) {
            period.append(" from: ").append(report.dateFrom());
        }

        if (report.dateTo() != null && !report.dateTo().isBlank()) {
            period.append(" to: ").append(report.dateTo());
        }

        if (!period.toString().equals("Report covers")) {
            System.out.println(period);
        }

        System.out.println("User: " + report.userID());
        System.out.println();

        System.out.printf("%-20s %15s %15s%n",
                "Project",
                "Hours",
                "Share");

        System.out.println("----------------------------------------------------");

        for (Report3UsersAllProjectsRow row : report.rows()) {

            System.out.printf("%-20s %15.2f %14.2f%%%n",
                    row.projectName(),
                    row.workingHours(),
                    row.percentageValue());
        }

        if (report.rows().isEmpty()) {
            System.out.println("No data.");
        }
    }
}