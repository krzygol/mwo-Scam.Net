package org.example.display.model;

import java.util.List;

public record Report1SumAllUsersData(
        String dateFrom,
        String dateTo,
        List<Report1SumAllUsersRow> rows
) {
}