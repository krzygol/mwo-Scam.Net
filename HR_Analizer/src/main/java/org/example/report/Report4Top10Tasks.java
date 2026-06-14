package org.example.report;

import org.example.display.model.Report4Top10TasksData;
import org.example.display.model.Report4Top10TasksRow;
import org.example.model.DataModel;
import org.example.model.Task;
import org.example.orchestrator.InputLoader;

import java.text.SimpleDateFormat;
import java.util.*;

public class Report4Top10Tasks
        extends Report<Report4Top10TasksData> {

    private DataModel data;
    InputLoader inputLoader;

//    public Report4Top10Tasks(DataModel data) {
//        super(data);
//    }

    public Report4Top10Tasks(DataModel data, InputLoader inputLoader) {
        super(data, inputLoader);
        this.data = data;
        this.inputLoader = inputLoader;
    }

    @Override
    public Report4Top10TasksData generate() {

        Map<String, Double> totalByTask = new HashMap<>();

        for (Task task : tasks) {
            totalByTask.merge(
                    task.getName(),
                    task.getDuration(),
                    Double::sum
            );
        }

        List<Report4Top10TasksRow> rows = new ArrayList<>();

        int rank = 1;

        for (Map.Entry<String, Double> entry : totalByTask.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(10)
                .toList()) {

            rows.add(
                    new Report4Top10TasksRow(
                            rank++,
                            entry.getKey(),
                            entry.getValue()
                    )
            );
        }

        return new Report4Top10TasksData(
                new SimpleDateFormat("yyyy-mm-dd")
                        .format(inputLoader.getFrom()),
                new SimpleDateFormat("yyyy-mm-dd")
                        .format(inputLoader.getTo()),
                rows
        );
    }
}
