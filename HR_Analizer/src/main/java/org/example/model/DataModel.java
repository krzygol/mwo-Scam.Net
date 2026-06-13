package org.example.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class DataModel {
    private final List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }


}
