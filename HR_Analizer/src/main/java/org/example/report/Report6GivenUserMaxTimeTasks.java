package org.example.report;

import org.example.display.model.Report6GivenUserMaxTimeTasksData;
import org.example.display.model.Report6GivenUserMaxTimeTasksRow;
import org.example.model.DataModel;
import org.example.model.Task;
import org.example.orchestrator.InputLoader;

import java.text.SimpleDateFormat;
import java.util.*;

public class Report6GivenUserMaxTimeTasks extends Report<Report6GivenUserMaxTimeTasksData> {

    private final String userID;
    private final InputLoader inputLoader;

    public Report6GivenUserMaxTimeTasks(DataModel data, InputLoader inputLoader) {
        super(data, inputLoader);
        this.userID = inputLoader.getUser();
        this.inputLoader = inputLoader;
    }

    @Override
    public Report6GivenUserMaxTimeTasksData generate() {
        Map<String, Double> byTask = new HashMap<>();
        for (Task task : tasks) {
            if (task.getUser().equals(userID)) {
                byTask.merge(task.getName(), task.getDuration(), Double::sum);
            }
        }

        List<Report6GivenUserMaxTimeTasksRow> rows = new ArrayList<>();
        int rank = 1;
        for (Map.Entry<String, Double> entry : byTask.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(10)
                .toList()) {
            rows.add(new Report6GivenUserMaxTimeTasksRow(rank++, entry.getKey(), entry.getValue()));
        }

        return new Report6GivenUserMaxTimeTasksData(
                new SimpleDateFormat("yyyy-MM-dd").format(inputLoader.getFrom()),
                new SimpleDateFormat("yyyy-MM-dd").format(inputLoader.getTo()),
                userID,
                rows
        );
    }
}
