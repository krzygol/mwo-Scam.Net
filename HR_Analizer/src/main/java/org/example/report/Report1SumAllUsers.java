package org.example.report;

import org.example.model.DataModel;
import org.example.model.Task;

import java.util.Map;
import java.util.TreeMap;

public class Report1SumAllUsers extends Report {

    public Report1SumAllUsers(DataModel data) {
        super(data);
    }

    @Override
    public String getTitle() {
        return "Sum of hours per client";
    }

    @Override
    public String generate() {
        Map<String, Double> sumByClient = new TreeMap<>();

        for (Task task : tasks) {
            sumByClient.merge(task.getClient(), task.getHoursSpent(), Double::sum);
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Double> entry : sumByClient.entrySet()) {
            sb.append(String.format("%-30s %.2f h%n", entry.getKey(), entry.getValue()));
        }
        return sb.toString();
    }
}
