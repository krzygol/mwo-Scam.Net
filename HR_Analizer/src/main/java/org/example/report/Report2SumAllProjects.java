package org.example.report;

import org.example.model.DataModel;
import org.example.model.Task;

import java.util.*;

public class Report2SumAllProjects extends Report {

    public Report2SumAllProjects(DataModel data) {
        super(data);
    }

    public Report2SumAllProjects(DataModel data, Date dateFrom, Date dateTo) {
        super(data, dateFrom, dateTo);
    }

    @Override
    public String getTitle() {
        return "Total hours by project";
    }

    @Override
    public String generate() {

        Map<String, Double> totalByProject = new HashMap<>();

        for (Task task : tasks) {
            totalByProject.merge(
                    task.getProject(),
                    task.getDuration(),
                    Double::sum
            );
        }

        if (totalByProject.isEmpty()) {
            return "No data available.";
        }

        List<Map.Entry<String, Double>> sorted = totalByProject.entrySet().stream()
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