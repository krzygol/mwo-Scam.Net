package org.example.report;

import org.example.model.DataModel;
import org.example.model.Task;

import java.util.Date;
import java.util.List;

public abstract class Report<T> {

    protected final List<Task> tasks;
    protected final Date dateFrom;
    protected final Date dateTo;

    protected Report(DataModel data) {
        this(data, null, null);
    }

    protected Report(DataModel data, Date dateFrom, Date dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.tasks = data.getTasks().stream()
                .filter(t -> dateFrom == null || !t.getDate().before(dateFrom))
                .filter(t -> dateTo == null || !t.getDate().after(dateTo))
                .toList();
    }

    public abstract T generate();
}