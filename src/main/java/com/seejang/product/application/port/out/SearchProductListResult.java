package com.seejang.product.application.port.out;

import com.seejang.product.domain.Category;
import com.seejang.product.domain.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record SearchProductListResult(
        List<ProductResult> products
) {

    public record ProductResult(
            String id,
            String name,
            Category category,
            String description,
            BigDecimal regularPrice,
            BigDecimal shippingFee,
            Long quantity,
            Status status,
            LocalDateTime deadline,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
    }
}
