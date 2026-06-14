package org.example.display.model;

public record Report0MetadataData(
        String dateFrom,
        String dateTo,
        String minDate,
        String maxDate,
        long userCount,
        long projectCount,
        long taskCount,
        double totalDuration,
        double avgDurationPerUser,
        double avgDurationPerProject
) {
}