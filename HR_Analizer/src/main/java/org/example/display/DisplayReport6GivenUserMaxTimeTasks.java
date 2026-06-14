package org.example.display;

import org.example.display.model.Report6GivenUserMaxTimeTasksData;
import org.example.display.model.Report6GivenUserMaxTimeTasksRow;

public class DisplayReport6GivenUserMaxTimeTasks
        implements Displayer<Report6GivenUserMaxTimeTasksData> {

    @Override
    public void display(Report6GivenUserMaxTimeTasksData report) {

        System.out.println("===== REPORT 6: TOP 10 MOST TIME-CONSUMING TASKS FOR USER =====");

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

        if (report.userName() != null) {
            System.out.println("User: " + report.userName());
        }

        System.out.println();

        System.out.printf("%-5s %-40s %15s%n",
                "No.",
                "Task",
                "Hours");

        System.out.println("------------------------------------------------------------");

        for (Report6GivenUserMaxTimeTasksRow row : report.rows()) {
            System.out.printf("%-5d %-40s %15.2f%n",
                    row.rank(),
                    row.taskName(),
                    row.workingHours());
        }

        if (report.rows().isEmpty()) {
            System.out.println("No data.");
        }
    }
}
