package org.example.report;

import org.example.model.DataModel;
import org.example.model.Task;

import java.util.*;

public class ReportUserNlProject implements Report {
    private final DataModel data;

    public ReportUserNlProject(DataModel data) {
        this.data = data;
    }

    @Override
    public String getTitle() {
        return "Hours per user per project";
    }

    @Override
    public String generate() {
        Map<String, Map<String, Double>> userProjectHours = new TreeMap<>();

        for (Task task : data.getTasks()) {
            userProjectHours
                    .computeIfAbsent(task.getUser(), k -> new TreeMap<>())
                    .merge(task.getProject(), task.getHoursSpent(), Double::sum);
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Map<String, Double>> userEntry : userProjectHours.entrySet()) {
            sb.append(userEntry.getKey()).append(":\n");
            for (Map.Entry<String, Double> projectEntry : userEntry.getValue().entrySet()) {
                sb.append(String.format("  %-28s %.2f h%n", projectEntry.getKey(), projectEntry.getValue()));
            }
        }
        return sb.toString();
    }
}
