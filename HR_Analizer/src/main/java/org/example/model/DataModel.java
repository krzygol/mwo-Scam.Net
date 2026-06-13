package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class DataModel {
    private final List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
