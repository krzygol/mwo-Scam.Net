package org.example.display;

import org.example.display.model.Report1SumAllUsersData;
import org.example.display.model.Report1SumAllUsersRow;

public class DisplayReport1SumAllUsers
        implements Displayer<Report1SumAllUsersData> {

    @Override
    public void display(Report1SumAllUsersData report) {

        System.out.println("===== RAPORT PRACY WSZYSTKICH PRACOWNIKÓW =====");

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
                "Imię i Nazwisko",
                "Liczba godzin");

        System.out.println("-----------------------------------------");

        for (Report1SumAllUsersRow row : report.rows()) {
            System.out.printf("%-20s %20.2f%n",
                    row.userName(),
                    row.workingHours());
        }

        if (report.rows().isEmpty()) {
            System.out.println("Brak danych.");
        }
    }
}