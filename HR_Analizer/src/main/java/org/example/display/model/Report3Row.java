package org.example.display.model;

import java.math.BigDecimal;

public record Report3Row(
        String product,
        int quantity,
        BigDecimal amount
) {
}