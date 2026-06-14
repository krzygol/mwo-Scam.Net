package org.example.report;

import org.example.display.model.Report1SumAllUsersData;
import org.example.display.model.Report1SumAllUsersRow;
import org.example.model.DataModel;
import org.example.model.Task;

import java.util.*;

public class Report1SumAllUsers
        extends Report<Report1SumAllUsersData> {

    public Report1SumAllUsers(DataModel data) {
        super(data);
    }

    public Report1SumAllUsers(DataModel data, Date dateFrom, Date dateTo) {
        super(data, dateFrom, dateTo);
    }

    @Override
    public Report1SumAllUsersData generate() {

        Map<String, Double> totalByUser = new HashMap<>();

        for (Task task : tasks) {
            totalByUser.merge(
                    task.getUser(),
                    task.getDuration(),
                    Double::sum
            );
        }

        List<Report1SumAllUsersRow> rows = new ArrayList<>();

        totalByUser.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(entry ->
                        rows.add(
                                new Report1SumAllUsersRow(
                                        entry.getKey(),
                                        entry.getValue().doubleValue()
                                )
                        )
                );

        return new Report1SumAllUsersData(
                "2026-06-01",     // dateFrom
                "2026-06-30",              // dateTo
                rows
        );
    }
}