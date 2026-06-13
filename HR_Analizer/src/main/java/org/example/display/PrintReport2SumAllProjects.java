package org.example.display;

import org.example.display.model.Report2SumAllProjectsRow;

import java.util.List;

public class PrintReport2SumAllProjects implements PrintReport<Report2SumAllProjectsRow> {

    @Override
    public void print(List<Report2SumAllProjectsRow> rows) {

        System.out.println("===== SUM ALL PROJECTS REPORT =====");

        System.out.printf("%-20s %20s",
                "Nazwa projektu",
                "Liczba godzin\n");

        System.out.println("----------------------------------------");

        for (Report2SumAllProjectsRow row : rows) {
            System.out.printf("%-20s %20d\n",
                    row.projectName(),
                    row.quantity());
        }
    }
}