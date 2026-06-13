package org.example.report;

import org.example.model.DataModel;
import org.example.model.Task;

import java.util.Map;
import java.util.TreeMap;

public class Report2SumAllProjects implements Report {
    private final DataModel data;

    public Report2SumAllProjects(DataModel data) {
        this.data = data;
    }

    @Override
    public String getTitle() {
        return "Sum of hours per project";
    }

    @Override
    public String generate() {
        Map<String, Double> sumByProject = new TreeMap<>();

        for (Task task : data.getTasks()) {
            sumByProject.merge(task.getProject(), task.getHoursSpent(), Double::sum);
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Double> entry : sumByProject.entrySet()) {
            sb.append(String.format("%-30s %.2f h%n", entry.getKey(), entry.getValue()));
        }
        return sb.toString();
    }
}
