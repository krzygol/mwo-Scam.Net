package org.example.report;

import org.example.display.model.Report1SumAllUsersData;
import org.example.display.model.Report1SumAllUsersRow;
import org.example.model.DataModel;
import org.example.model.Task;
import org.example.orchestrator.InputLoader;

import java.text.SimpleDateFormat;
import java.util.*;

public class Report1SumAllUsers
        extends Report<Report1SumAllUsersData> {

    InputLoader inputLoader;

    public Report1SumAllUsers(DataModel data, InputLoader inputLoader) {
        super(data, inputLoader);
        this.inputLoader = inputLoader;
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
                new SimpleDateFormat("yyyy-MM-dd")
                        .format(inputLoader.getFrom()),
                new SimpleDateFormat("yyyy-MM-dd")
                        .format(inputLoader.getTo()),
                rows
        );
    }
}