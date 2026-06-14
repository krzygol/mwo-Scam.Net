package org.example.display.model;

import java.util.List;

public record Report3UsersAllProjectsData(
        String dateFrom,
        String dateTo,
        String userID,
        List<Report3UsersAllProjectsRow> rows
) {
}
