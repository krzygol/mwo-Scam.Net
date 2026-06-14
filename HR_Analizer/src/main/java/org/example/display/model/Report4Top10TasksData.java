package org.example.display.model;

import java.util.List;

public record Report4Top10TasksData(
        String dateFrom,
        String dateTo,
        String userID,
        List<Report4Top10TasksRow> rows
) {
}