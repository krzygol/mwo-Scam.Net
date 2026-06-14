package org.example.display.model;

import java.util.List;

public record Report5UsersMaxTimeLoadData(
        String dateFrom,
        String dateTo,
        List<Report5UsersMaxTimeLoadRow> rows
) {
}