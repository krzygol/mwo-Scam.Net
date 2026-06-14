package org.example.report;

import org.example.model.DataModel;
import org.example.model.Task;
import org.example.orchestrator.InputLoader;

import java.util.Date;
import java.util.List;

public abstract class Report<T> {

    protected List<Task> tasks;
    protected Date dateFrom;
    protected Date dateTo;

//    protected Report(DataModel data) {
//        this(data, null, null);
//    }

    protected Report(DataModel data, InputLoader inputLoader) {
        this.dateFrom = inputLoader.getFrom();
        this.dateTo = inputLoader.getTo();
        this.tasks = data.getTasks().stream()
                .filter(t -> dateFrom == null || !t.getDate().before(dateFrom))
                .filter(t -> dateTo == null || !t.getDate().after(dateTo))
                .toList();
    }

    public abstract T generate();
}