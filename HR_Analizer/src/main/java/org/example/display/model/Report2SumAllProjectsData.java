package org.example.display.model;

import java.util.List;

public record Report2SumAllProjectsData(
    String dateFrom,
    String dateTo,
    List<Report2SumAllProjectsRow> rows
) {
}
