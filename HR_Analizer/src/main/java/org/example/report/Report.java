package org.example.report;

import org.example.model.DataModel;
import org.example.model.Task;

import java.util.List;

public abstract class Report {
    protected final List<Task> tasks;

    protected Report(DataModel data) {
        this.tasks = data.getTasks();
    }

    public abstract String getTitle();
    public abstract String generate();
}
