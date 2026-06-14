package org.example.report;

import org.example.display.model.Report4Top10TasksData;
import org.example.display.model.Report4Top10TasksRow;
import org.example.model.DataModel;
import org.example.model.Task;

import java.util.*;

public class Report4Top10Tasks
        extends Report<Report4Top10TasksData> {

    private DataModel data;

    public Report4Top10Tasks(DataModel data) {
        super(data);
    }

    public Report4Top10Tasks(DataModel data, Date dateFrom, Date dateTo) {
        super(data, dateFrom, dateTo);
        this.data = data;
    }

    @Override
    public Report4Top10TasksData generate() {

        Map<String, Double> totalByTask = new HashMap<>();

        for (Task task : tasks) {
            totalByTask.merge(
                    task.getName(),
                    task.getDuration(),
                    Double::sum
            );
        }

        List<Report4Top10TasksRow> rows = new ArrayList<>();

        int rank = 1;

        for (Map.Entry<String, Double> entry : totalByTask.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(10)
                .toList()) {

            rows.add(
                    new Report4Top10TasksRow(
                            rank++,
                            entry.getKey(),
                            entry.getValue()
                    )
            );
        }

        return new Report4Top10TasksData(
                "2026-06-01",     // dateFrom
                "2026-06-30",
                rows
        );
    }
}
