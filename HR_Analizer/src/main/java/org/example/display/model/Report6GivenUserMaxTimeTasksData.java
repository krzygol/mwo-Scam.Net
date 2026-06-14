package org.example.display.model;

import java.util.List;

public record Report6GivenUserMaxTimeTasksData(
        String dateFrom,
        String dateTo,
        String userName,
        List<Report6GivenUserMaxTimeTasksRow> rows
) {
}