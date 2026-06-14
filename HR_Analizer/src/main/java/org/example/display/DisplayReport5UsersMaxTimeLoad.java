package org.example.display;

import org.example.display.model.Report5UsersMaxTimeLoadData;
import org.example.display.model.Report5UsersMaxTimeLoadRow;

public class DisplayReport5UsersMaxTimeLoad
        implements Displayer<Report5UsersMaxTimeLoadData> {

    @Override
    public void display(Report5UsersMaxTimeLoadData report) {

        System.out.println("===== REPORT 5: TOP 5 EMPLOYEES IN PROJECTS =====");

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

        System.out.printf("%-5s %-30s %15s%n",
                "No.",
                "Employee",
                "Hours");

        System.out.println("----------------------------------------------------");

        for (Report5UsersMaxTimeLoadRow row : report.rows()) {

            System.out.printf("%-5d %-30s %15.2f%n",
                    row.rank(),
                    row.userName(),
                    row.workingHours());
        }

        if (report.rows().isEmpty()) {
            System.out.println("No data.");
        }
    }
}
