package com.seejang.product.application.port.in;

import com.seejang.common.SelfValidator;
import com.seejang.product.domain.Category;
import com.seejang.product.domain.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class CreateProductCommand extends SelfValidator<CreateProductCommand> {

    @NotNull
    private final String name;

    @NotNull
    private final Category category;

    @NotNull
    private final String description;

    @NotNull
    private final BigDecimal regularPrice;

    @NotNull
    private final BigDecimal shippingFee;

    @NotNull
    private final Long quantity;

    @NotNull
    private final Status status;

    private final LocalDateTime deadline;

    public CreateProductCommand(String name, Category category, String description, BigDecimal regularPrice, BigDecimal shippingFee, Long quantity, Status status, LocalDateTime deadline) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.regularPrice = regularPrice;
        this.shippingFee = shippingFee;
        this.quantity = quantity;
        this.status = status;
        this.deadline = deadline;

        validateSelf();
    }
}
