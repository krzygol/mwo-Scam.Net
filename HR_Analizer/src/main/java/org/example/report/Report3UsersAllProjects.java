package org.example.report;

import org.example.display.model.Report3UsersAllProjectsData;
import org.example.display.model.Report3UsersAllProjectsRow;
import org.example.model.DataModel;
import org.example.model.Task;
import org.example.orchestrator.InputLoader;

import java.text.SimpleDateFormat;
import java.util.*;

public class Report3UsersAllProjects
        extends Report<Report3UsersAllProjectsData> {

    private DataModel data;
    private final String userID;
    InputLoader inputLoader;

//    public Report3UsersAllProjects(DataModel data, String userID) {
//        super(data);
//        this.data = data;
//        this.userID = userID;
//    }

    public Report3UsersAllProjects(DataModel data, InputLoader inputLoader) {
        super(data, inputLoader);
        this.userID = inputLoader.getUser();
        this.inputLoader = inputLoader;
    }

    @Override
    public Report3UsersAllProjectsData generate() {

        Map<String, Double> taskMap = new HashMap<>();

        for (Task task : tasks) {
            if (task.getUser().equals(userID)) {
                taskMap.merge(
                        task.getProject(),
                        task.getDuration(),
                        Double::sum
                );
            }
        }

        double totalHours = taskMap.values()
                .stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        List<Report3UsersAllProjectsRow> rows = new ArrayList<>();

        taskMap.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(entry -> {

                    double  hours = entry.getValue();

                    double percentage = totalHours == 0
                            ? 0
                            : hours * 100.0 / totalHours;

                    rows.add(
                            new Report3UsersAllProjectsRow(
                                    entry.getKey(),
                                    hours,
                                    percentage
                            )
                    );
                });

        return new Report3UsersAllProjectsData(
                new SimpleDateFormat("yyyy-mm-dd")
                        .format(inputLoader.getFrom()),
                new SimpleDateFormat("yyyy-mm-dd")
                        .format(inputLoader.getTo()),       // dateTo
                userID,
                rows
        );
    }
}