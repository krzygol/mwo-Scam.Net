package org.example.display;

import org.example.display.model.Report5UsersMaxTimeLoadData;
import org.example.display.model.Report5UsersMaxTimeLoadRow;

public class DisplayReport5UsersMaxTimeLoad
        implements Displayer<Report5UsersMaxTimeLoadData> {

    @Override
    public void display(Report5UsersMaxTimeLoadData report) {

        System.out.println("===== TOP 5 UŻYTKOWNIKÓW W PROJEKTACH =====");

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

        System.out.printf("%-5s %-30s %15s%n",
                "Lp.",
                "Użytkownik",
                "Godziny");

        System.out.println("----------------------------------------------------");

        for (Report5UsersMaxTimeLoadRow row : report.rows()) {

            System.out.printf("%-5d %-30s %15.2f%n",
                    row.rank(),
                    row.userName(),
                    row.workingHours());
        }

        if (report.rows().isEmpty()) {
            System.out.println("Brak danych.");
        }
    }
}
