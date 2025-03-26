package com.seejang.product.application.port.out;

import com.seejang.product.domain.Category;
import com.seejang.product.domain.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateProductResult(
        Long id,
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
