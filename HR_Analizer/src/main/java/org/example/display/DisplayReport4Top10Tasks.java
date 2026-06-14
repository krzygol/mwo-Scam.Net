package org.example.display;

import org.example.display.model.Report4Top10TasksData;
import org.example.display.model.Report4Top10TasksRow;

public class DisplayReport4Top10Tasks
        implements Displayer<Report4Top10TasksData> {

    @Override
    public void display(Report4Top10TasksData report) {

        System.out.println("===== REPORT 4: TOP 10 TASKS IN PROJECTS =====");

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

        System.out.printf("%-5s %-45s %15s%n",
                "No.",
                "Task",
                "Hours");

        System.out.println("-------------------------------------------------------------------");

        for (Report4Top10TasksRow row : report.rows()) {

            System.out.printf("%-5d %-45s %15.2f%n",
                    row.rank(),
                    row.taskName(),
                    row.workingHours());
        }

        if (report.rows().isEmpty()) {
            System.out.println("No data.");
        }
    }
}