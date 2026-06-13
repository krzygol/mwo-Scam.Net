package org.example.report;

import org.example.model.DataModel;
import org.example.model.Task;

import java.util.*;

public class Report4Top10Tasks extends Report {

    public Report4Top10Tasks(DataModel data) {
        super(data);
    }

    @Override
    public String getTitle() {
        return "Top 10 tasks by total time";
    }

    @Override
    public String generate() {

        Map<String, Double> totalByTask = new HashMap<>();

        for (Task task : tasks) {
            totalByTask.merge(
                    task.getName(),
                    task.getDuration(),
                    Double::sum
            );
        }

        if (totalByTask.isEmpty()) {
            return "No data available.";
        }

        List<Map.Entry<String, Double>> sorted = totalByTask.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(10)
                .toList();

        StringBuilder sb = new StringBuilder();
        sb.append(getTitle()).append("\n");

        int rank = 1;

        for (Map.Entry<String, Double> entry : sorted) {
            sb.append(String.format(
                    "%2d. %-40s %.2f h%n",
                    rank++,
                    entry.getKey(),
                    entry.getValue()
            ));
        }

        return sb.toString();
    }
}