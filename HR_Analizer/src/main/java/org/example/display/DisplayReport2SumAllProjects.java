package org.example.display;

import org.example.display.model.Report2SumAllProjectsData;
import org.example.display.model.Report2SumAllProjectsRow;

public class DisplayReport2SumAllProjects
        implements Displayer<Report2SumAllProjectsData> {

    @Override
    public void display(Report2SumAllProjectsData report) {

        System.out.println("===== REPORT 2: ALL PROJECTS SUMMARY =====");

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

        System.out.println();

        System.out.printf("%-20s %20s%n",
                "Project Name",
                "Hours");

        System.out.println("-----------------------------------------");

        for (Report2SumAllProjectsRow row : report.rows()) {
            System.out.printf("%-20s %20.2f%n",
                    row.projectName(),
                    row.workingHours());
        }

        if (report.rows().isEmpty()) {
            System.out.println("No data.");
        }
    }
}