package org.example.display;

import org.example.display.model.Report4Top10TasksData;
import org.example.display.model.Report4Top10TasksRow;

public class DisplayReport4Top10Tasks
        implements Displayer<Report4Top10TasksData> {

    @Override
    public void display(Report4Top10TasksData report) {

        System.out.println("===== TOP 10 ZADAŃ W PROJEKTACH =====");

        StringBuilder period = new StringBuilder("Raport obejmuje");

        if (report.dateFrom() != null && !report.dateFrom().isBlank()) {
            period.append(" od: ").append(report.dateFrom());
        }

        if (report.dateTo() != null && !report.dateTo().isBlank()) {
            period.append(" do: ").append(report.dateTo());
        }

        if (!period.toString().equals("Raport obejmuje")) {
            System.out.println(period);
        }

        System.out.println();

        System.out.printf("%-5s %-45s %15s%n",
                "Lp.",
                "Zadanie",
                "Godziny");

        System.out.println("-------------------------------------------------------------------");

        for (Report4Top10TasksRow row : report.rows()) {

            System.out.printf("%-5d %-45s %15.2f%n",
                    row.rank(),
                    row.taskName(),
                    row.workingHours());
        }

        if (report.rows().isEmpty()) {
            System.out.println("Brak danych.");
        }
    }
}