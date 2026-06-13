package org.example.report;

import org.example.model.DataModel;
import org.example.model.Task;

import java.util.*;

public class Report5UsersMaxTimeLoad extends Report {

    public Report5UsersMaxTimeLoad(DataModel data) {
        super(data);
    }

    public Report5UsersMaxTimeLoad(DataModel data, Date dateFrom, Date dateTo) {
        super(data, dateFrom, dateTo);
    }

    @Override
    public String getTitle() { // Changed title to reflect the top 5 users
        return "Top 5 users with maximum time load";
    }

    @Override
    public String generate() {
        Map<String, Map<String, Double>> byUserAndTask = new HashMap<>();

        for (Task task : tasks) {
            byUserAndTask
                    .computeIfAbsent(task.getUser(), k -> new HashMap<>())
                    .merge(task.getName(), task.getDuration(), Double::sum);
        }

        if (byUserAndTask.isEmpty()) {
            return "No data available.";
        }

        Map<String, Double> totalByUser = new HashMap<>();
        for (Map.Entry<String, Map<String, Double>> userEntry : byUserAndTask.entrySet()) {
            double total = userEntry.getValue().values().stream()
                    .mapToDouble(Double::doubleValue)
                    .sum();
            totalByUser.put(userEntry.getKey(), total);
        }

        List<Map.Entry<String, Double>> sorted = totalByUser.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(5)
                .toList();

        StringBuilder sb = new StringBuilder();
        sb.append(getTitle());
        for (Map.Entry<String, Double> entry : sorted) {
            sb.append(String.format("%-30s %.2f h%n", entry.getKey(), entry.getValue()));
        }
        return sb.toString();
    }
}
