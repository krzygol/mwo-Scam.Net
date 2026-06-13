package org.example.report;

import org.example.model.DataModel;
import org.example.model.Task;

import java.util.*;

public class Report1SumAllUsers extends Report {

    public Report1SumAllUsers(DataModel data) {
        super(data);
    }

    @Override
    public String getTitle() {
        return "Total hours by user";
    }

    @Override
    public String generate() {

        Map<String, Double> totalByUser = new HashMap<>();

        for (Task task : tasks) {
            totalByUser.merge(
                    task.getUser(),
                    task.getDuration(),
                    Double::sum
            );
        }

        if (totalByUser.isEmpty()) {
            return "No data available.";
        }

        List<Map.Entry<String, Double>> sorted = totalByUser.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .toList();

        StringBuilder sb = new StringBuilder();
        sb.append(getTitle()).append("\n");

        for (Map.Entry<String, Double> entry : sorted) {
            sb.append(String.format(
                    "%-30s %.2f h%n",
                    entry.getKey(),
                    entry.getValue()
            ));
        }

        return sb.toString();
    }
}