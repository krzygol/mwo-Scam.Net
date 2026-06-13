package org.example.display;

import org.example.display.model.Report1SumAllUsersRow;

import java.util.List;

public class PrintReport1SumAllUsers implements PrintReport<Report1SumAllUsersRow> {

    @Override
    public void print(List<Report1SumAllUsersRow> rows) {

        System.out.println("===== SUM ALL USERS REPORT =====");

        System.out.printf("%-20s %20s",
                "Imię i Nazwisko",
                "Liczba godzin\n");

        System.out.println("----------------------------------------");

        for (Report1SumAllUsersRow row : rows) {
            System.out.printf("%-20s %20d\n",
                    row.userName(),
                    row.workingHours());
        }
    }
}