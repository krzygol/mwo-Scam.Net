package org.example.report;

import org.example.model.DataModel;
import org.example.model.Task;

import java.util.Comparator;
import java.util.List;

public class ReportTop10Task implements Report {
    private final DataModel data;

    public ReportTop10Task(DataModel data) {
        this.data = data;
    }

    @Override
    public String getTitle() {
        return "Top 10 tasks by hours spent";
    }

    @Override
    public String generate() {
        List<Task> top10 = data.getTasks().stream()
                .sorted(Comparator.comparingDouble(Task::getHoursSpent).reversed())
                .limit(10)
                .toList();

        StringBuilder sb = new StringBuilder();
        int rank = 1;
        for (Task task : top10) {
            sb.append(String.format("%2d. %-30s %-20s %.2f h%n",
                    rank++, task.getTaskName(), task.getUser(), task.getHoursSpent()));
        }
        return sb.toString();
    }
}
