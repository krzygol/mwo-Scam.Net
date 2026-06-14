package org.example.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Date;

public class DataModel {
    private final List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Date getMaxDate() {
        return tasks.stream()
                .map(Task::getDate)
                .max(Comparator.naturalOrder())
                .orElse(null);
    }

    public Date getMinDate() {
        return tasks.stream()
                .map(Task::getDate)
                .min(Comparator.naturalOrder())
                .orElse(null);
    }

    public long getUserCount() {
        return tasks.stream()
                .map(Task::getUser)
                .distinct()
                .count();
    }

    public long getProjectCount() {
        return tasks.stream()
                .map(Task::getProject)
                .distinct()
                .count();
    }

    public long getTaskCount() {
        return tasks.size();
    }

    public double getTotalDuration() {
        return tasks.stream()
                .mapToDouble(Task::getDuration)
                .sum();
    }

    public double getAvgDurationPerUser() {
        long users = getUserCount();
        return users == 0 ? 0 : getTotalDuration() / users;
    }

    public double getAvgDurationPerProject() {
        long projects = getProjectCount();
        return projects == 0 ? 0 : getTotalDuration() / projects;
    }

    public List<String> getUsers() {
        return tasks.stream()
                .map(Task::getUser)
                .distinct()
                .toList();
    }

    public List<String> getProjects() {
        return tasks.stream()
                .map(Task::getProject)
                .distinct()
                .toList();
    }
}