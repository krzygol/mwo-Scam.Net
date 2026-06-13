package org.example.display.model;

import java.math.BigDecimal;

public record Report2Row(
        String product,
        int quantity,
        BigDecimal amount
) {
}