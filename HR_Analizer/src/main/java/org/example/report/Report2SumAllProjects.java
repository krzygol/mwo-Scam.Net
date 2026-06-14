package org.example.report;

import org.example.display.model.Report2SumAllProjectsData;
import org.example.display.model.Report2SumAllProjectsRow;
import org.example.model.DataModel;
import org.example.model.Task;
import org.example.orchestrator.InputLoader;

import java.text.SimpleDateFormat;
import java.util.*;

public class Report2SumAllProjects
        extends Report<Report2SumAllProjectsData> {

    private DataModel data;
    InputLoader inputLoader;

    public Report2SumAllProjects(DataModel data, InputLoader inputLoader) {
        super(data, inputLoader);
        this.inputLoader = inputLoader;
    }

    @Override
    public Report2SumAllProjectsData generate() {

        Map<String, Double> totalByProject = new HashMap<>();

        for (Task task : tasks) {
            totalByProject.merge(
                    task.getProject(),
                    task.getDuration(),
                    Double::sum
            );
        }

        List<Report2SumAllProjectsRow> rows = new ArrayList<>();

        totalByProject.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(entry ->
                        rows.add(
                                new Report2SumAllProjectsRow(
                                        entry.getKey(),
                                        entry.getValue().doubleValue()
                                )
                        )
                );

        return new Report2SumAllProjectsData(
                new SimpleDateFormat("yyyy-mm-dd")
                        .format(inputLoader.getFrom()),
                new SimpleDateFormat("yyyy-mm-dd")
                        .format(inputLoader.getTo()),
                rows
        );
    }
}