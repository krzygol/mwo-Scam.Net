package org.example.display;

import org.example.display.model.Report0MetadataData;

public class DisplayReport0Metadata implements Displayer<Report0MetadataData> {

    @Override
    public void display(Report0MetadataData report) {
        System.out.println("===== REPORT 0: DATA METADATA =====");

        if (report.dateFrom() != null && !report.dateFrom().isBlank()) {
            System.out.println("From:                  " + report.dateFrom());
        }
        if (report.dateTo() != null && !report.dateTo().isBlank()) {
            System.out.println("To:                    " + report.dateTo());
        }

        if (report.minDate() != null) {
            System.out.println("Earliest date:         " + report.minDate());
        }
        if (report.maxDate() != null) {
            System.out.println("Latest date:           " + report.maxDate());
        }

        System.out.println("---------------------------");
        System.out.printf("Number of employees:   %d%n", report.userCount());
        System.out.printf("Number of projects:    %d%n", report.projectCount());
        System.out.printf("Number of tasks:       %d%n", report.taskCount());
        System.out.printf("Total hours:           %.2f%n", report.totalDuration());
        System.out.printf("Avg hours/employee:    %.2f%n", report.avgDurationPerUser());
        System.out.printf("Avg hours/project:     %.2f%n", report.avgDurationPerProject());
    }
}