package org.example.display;

import org.example.display.model.Report2SumAllProjectsData;
import org.example.display.model.Report2SumAllProjectsRow;

public class DisplayReport2SumAllProjects
        implements Displayer<Report2SumAllProjectsData> {

    @Override
    public void display(Report2SumAllProjectsData report) {

        System.out.println("===== RAPORT WSZYSTKICH PROJEKTÓW =====");

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

        System.out.printf("%-20s %20s%n",
                "Nazwa projektu",
                "Liczba godzin");

        System.out.println("-----------------------------------------");

        for (Report2SumAllProjectsRow row : report.rows()) {
            System.out.printf("%-20s %20.2f%n",
                    row.projectName(),
                    row.workingHours());
        }

        if (report.rows().isEmpty()) {
            System.out.println("Brak danych.");
        }
    }
}