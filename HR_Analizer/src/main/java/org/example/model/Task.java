package org.example.model;

public class Task {
    private String user;
    private String client;
    private String project;
    private String taskName;
    private double hoursSpent;

    public Task(String user, String client, String project, String taskName, double hoursSpent) {
        this.user = user;
        this.client = client;
        this.project = project;
        this.taskName = taskName;
        this.hoursSpent = hoursSpent;
    }

    public String getUser() { return user; }
    public String getClient() { return client; }
    public String getProject() { return project; }
    public String getTaskName() { return taskName; }
    public double getHoursSpent() { return hoursSpent; }
}
