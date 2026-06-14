package org.example.report;

import org.example.display.model.Report2SumAllProjectsData;
import org.example.display.model.Report2SumAllProjectsRow;
import org.example.model.DataModel;
import org.example.model.Task;

import java.util.*;

public class Report2SumAllProjects
        extends Report<Report2SumAllProjectsData> {

    private DataModel data;

    public Report2SumAllProjects(DataModel data) {
        super(data);
        this.data = data;
    }

    public Report2SumAllProjects(DataModel data, Date dateFrom, Date dateTo) {
        super(data, dateFrom, dateTo);
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
                "2026-06-01",     // dateFrom
                "2026-06-30",              // dateTo
                rows
        );
    }
}