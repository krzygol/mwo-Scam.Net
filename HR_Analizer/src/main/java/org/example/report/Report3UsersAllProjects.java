package org.example.report;

import org.example.model.DataModel;
import org.example.model.Task;

import java.util.*;

public class Report3UsersAllProjects extends Report {

    private final String userID;

    public Report3UsersAllProjects(DataModel data, String userID) {
        super(data);
        this.userID = userID;
    }

    @Override
    public String getTitle() {
        return "Report 3: All projects for user: " + userID;
    }

    @Override
    public String generate() {
        Map<String, Double> taskMap = new HashMap<>();

        for (Task task : tasks) {
            if (task.getUser().equals(userID)) {
                taskMap.merge(task.getName(), task.getDuration(), Double::sum);
            }
        }

        if (taskMap.isEmpty()) {
            return "No data available for user: " + userID;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(userID).append(":\n");
        for (Map.Entry<String, Double> entry : taskMap.entrySet()) {
            sb.append(String.format("  %-28s %.2f h%n", entry.getKey(), entry.getValue()));
        }
        return sb.toString();
    }
}