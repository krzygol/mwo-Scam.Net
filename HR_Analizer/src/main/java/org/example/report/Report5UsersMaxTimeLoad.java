package org.example.report;

import org.example.display.model.Report5UsersMaxTimeLoadData;
import org.example.display.model.Report5UsersMaxTimeLoadRow;
import org.example.model.DataModel;
import org.example.model.Task;

import java.util.*;

public class Report5UsersMaxTimeLoad
        extends Report<Report5UsersMaxTimeLoadData> {

    private DataModel data;

    public Report5UsersMaxTimeLoad(DataModel data) {
        super(data);
        this.data = data;
    }

    public Report5UsersMaxTimeLoad(DataModel data, Date dateFrom, Date dateTo) {
        super(data, dateFrom, dateTo);
    }

    @Override
    public Report5UsersMaxTimeLoadData generate() {

        Map<String, Map<String, Double>> byUserAndTask = new HashMap<>();

        for (Task task : tasks) {
            byUserAndTask
                    .computeIfAbsent(task.getUser(), k -> new HashMap<>())
                    .merge(task.getName(),
                            task.getDuration(),
                            Double::sum);
        }

        Map<String, Double> totalByUser = new HashMap<>();

        for (Map.Entry<String, Map<String, Double>> userEntry
                : byUserAndTask.entrySet()) {

            double total = userEntry.getValue()
                    .values()
                    .stream()
                    .mapToDouble(Double::doubleValue)
                    .sum();

            totalByUser.put(userEntry.getKey(), total);
        }

        List<Report5UsersMaxTimeLoadRow> rows = new ArrayList<>();

        int rank = 1;

        for (Map.Entry<String, Double> entry : totalByUser.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(5)
                .toList()) {

            rows.add(
                    new Report5UsersMaxTimeLoadRow(
                            rank++,
                            entry.getKey(),
                            entry.getValue()
                    )
            );
        }

        return new Report5UsersMaxTimeLoadData(
                "2026-06-01",     // dateFrom
                "2026-06-30",
                rows
        );
    }
}