package org.example.display.model;

import java.math.BigDecimal;

public record Report4Top10TasksRow(
        int rank,
        String taskName,
        double workingHours
) {
}